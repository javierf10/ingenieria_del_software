package es.unizar.eina.M117_quads.ui.reservas;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.database.ReservaRepository;

/**
 * ViewModel simplificado que gestiona los datos de las reservas.
 */
public class ReservaViewModel extends AndroidViewModel {

    private final ReservaRepository repository;
    private final LiveData<List<Reserva>> allReservas;

    public ReservaViewModel(@NonNull Application application) {
        super(application);
        repository = new ReservaRepository(application);
        // Obtenemos las reservas directamente, ordenadas por nombre por defecto.
        allReservas = repository.getReservasOrderedByNombre();
    }

    public LiveData<List<Reserva>> getAllReservas() {
        return allReservas;
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
