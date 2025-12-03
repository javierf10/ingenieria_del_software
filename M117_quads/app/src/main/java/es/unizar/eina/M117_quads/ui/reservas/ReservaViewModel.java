//package es.unizar.eina.M117_quads.ui.reservas;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import java.util.List;
//
//import es.unizar.eina.M117_quads.database.Reserva;
//import es.unizar.eina.M117_quads.database.ReservaRepository;
//
///**
// * ViewModel que gestiona los datos de los reservas y proporciona métodos
// * para insertar, actualizar, eliminar y consultar reservas desde la base de datos.
// * <p>
// * Extiende {@link AndroidViewModel} para poder usar el contexto de la aplicación.
// */
//public class ReservaViewModel extends AndroidViewModel {
//
//    /** Repositorio que gestiona el acceso a los datos de los reservas. */
//    private final ReservaRepository repository;
//
//    /** Lista observable de todos los reservas. */
//    private final LiveData<List<Reserva>> allReservas;
//
//    /**
//     * Constructor del ViewModel.
//     *
//     * @param application Contexto de la aplicación
//     */
//    public ReservaViewModel(@NonNull Application application) {
//        super(application);
//        repository = new ReservaRepository(application);
//        allReservas = repository.getAllReservas();
//    }
//
//    /**
//     * Obtiene la lista observable de todos los reservas.
//     *
//     * @return {@link LiveData} con la lista de reservas
//     */
//    public LiveData<List<Reserva>> getAllReservas() {
//        return allReservas;
//    }
//
//    /**
//     * Inserta un nuevo reserva en la base de datos.
//     *
//     * @param reserva Reserva a insertar
//     */
//    public void insert(Reserva reserva) {
//        repository.insert(reserva);
//    }
//
//    /**
//     * Obtiene un reserva específico según su ID.
//     *
//     * @param id Identificador del reserva
//     * @return {@link LiveData} con el reserva correspondiente al ID
//     */
//    public LiveData<Reserva> getReservaById(int id) {
//        return repository.getReservaById(id);
//    }
//
//    /**
//     * Actualiza un reserva existente en la base de datos.
//     *
//     * @param reserva Reserva a actualizar
//     */
//    public void update(Reserva reserva) {
//        repository.update(reserva);
//    }
//
//    /**
//     * Elimina un reserva de la base de datos.
//     *
//     * @param reserva Reserva a eliminar
//     */
//    public void delete(Reserva reserva) {
//        repository.delete(reserva);
//    }
//}
//