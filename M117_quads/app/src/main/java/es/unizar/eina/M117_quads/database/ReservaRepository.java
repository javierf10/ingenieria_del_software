package es.unizar.eina.M117_quads.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repositorio para la gestión de objetos {@link Reserva}.
 * <p>
 * Esta clase proporciona una capa de abstracción sobre la base de datos,
 * permitiendo acceder y modificar los datos de los reservas de manera asíncrona
 * usando {@link LiveData} y un ejecutor de base de datos.
 */
public class ReservaRepository {

    /** DAO de acceso a los reservas en la base de datos. */
    private final ReservaDao reservaDao;

    /**
     * Constructor del repositorio.
     *
     * @param application Contexto de la aplicación usado para obtener la base de datos.
     */
    public ReservaRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        reservaDao = db.reservaDao();
    }

    /**
     * Devuelve todos los reservas de la base de datos ordenados por nombre.
     *
     * @return LiveData que contiene la lista de reservas ordenados.
     */
    public LiveData<List<Reserva>> getReservasOrderedByNombre() {
        return reservaDao.getOrderedReservasByNombre();
    }

    /**
     * Devuelve todos los reservas de la base de datos ordenados por número de teléfono.
     *
     * @return LiveData que contiene la lista de reservas ordenados.
     */
    public LiveData<List<Reserva>> getReservasOrderedByNumero() {
        return reservaDao.getOrderedReservasByNumero();
    }

    /**
     * Devuelve todos los reservas de la base de datos ordenados por fecha de recogida.
     *
     * @return LiveData que contiene la lista de reservas ordenados.
     */
    public LiveData<List<Reserva>> getReservasOrderedByFechaRecogida() {
        return reservaDao.getOrderedReservasByFechaRecogida();
    }

    /**
     * Devuelve todos los reservas de la base de datos ordenados por fecha de devolución.
     *
     * @return LiveData que contiene la lista de reservas ordenados.
     */
    public LiveData<List<Reserva>> getReservasOrderedByFechaDevolucion() {
        return reservaDao.getOrderedReservasByFechaDevolucion();
    }

    /**
     * Inserta un nuevo reserva en la base de datos de manera asíncrona.
     *
     * @param reserva Reserva a insertar.
     */
    public void insert(Reserva reserva) {
        AppDatabase.databaseWriteExecutor.execute(() -> reservaDao.insert(reserva));
    }

    /**
     * Actualiza un reserva existente en la base de datos de manera asíncrona.
     *
     * @param reserva Reserva a actualizar.
     */
    public void update(Reserva reserva) {
        AppDatabase.databaseWriteExecutor.execute(() -> reservaDao.update(reserva));
    }

    /**
     * Obtiene un reserva por su identificador.
     *
     * @param id Identificador de la reserva.
     * @return LiveData que contiene el reserva correspondiente, o {@code null} si no existe.
     */
    public LiveData<Reserva> getReservaById(int id) {
        return reservaDao.getReservaById(id);
    }

    /**
     * Elimina un reserva de la base de datos de manera asíncrona.
     *
     * @param reserva Reserva a eliminar.
     */
    public void delete(Reserva reserva) {
        AppDatabase.databaseWriteExecutor.execute(() -> reservaDao.delete(reserva));
    }

}
