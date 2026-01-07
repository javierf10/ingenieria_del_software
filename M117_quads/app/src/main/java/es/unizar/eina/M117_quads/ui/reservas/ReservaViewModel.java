package es.unizar.eina.M117_quads.ui.reservas;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.Objects;

import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.database.ReservaRepository;

/**
 * ViewModel simplificado que gestiona los datos de las reservas.
 */
public class ReservaViewModel extends AndroidViewModel {

    private final ReservaRepository repository;
    private final MutableLiveData<String> mSortBy = new MutableLiveData<>();
    private final LiveData<List<Reserva>> allReservas;


    public ReservaViewModel(@NonNull Application application) {
        super(application);
        repository = new ReservaRepository(application);
        allReservas = Transformations.switchMap(mSortBy, sortBy -> {
            if (Objects.equals(sortBy, "numero")) {
                return repository.getReservasOrderedByNumero();
            } else if (Objects.equals(sortBy, "fechaRecogida")) {
                return repository.getReservasOrderedByFechaRecogida();
            } else if (Objects.equals(sortBy, "fechaDevolucion")) {
                return repository.getReservasOrderedByFechaDevolucion();
            } else {
                return repository.getReservasOrderedByNombre();
            }
        });
        setSortBy("nombre");
    }

    public LiveData<List<Reserva>> getAllReservas() {
        return allReservas;
    }

    public void setSortBy(String sortBy) {
        mSortBy.setValue(sortBy);
    }

    public void insert(Reserva reserva, List<Integer> quadIds) {
        repository.insert(reserva, quadIds);
    }

    public void insert(Reserva reserva) {
        repository.insert(reserva);
    }

    public LiveData<Reserva> getReservaById(int id) {
        return repository.getReservaById(id);
    }

    public void update(Reserva reserva, List<Integer> quadIds) {
        repository.update(reserva, quadIds);
    }

    public void update(Reserva reserva) {
        repository.update(reserva);
    }

    public void delete(Reserva reserva) {
        repository.delete(reserva);
    }

    public LiveData<List<Integer>> getQuadIdsForReserva(int reservaId) {
        return repository.getQuadIdsForReserva(reservaId);
    }
}
