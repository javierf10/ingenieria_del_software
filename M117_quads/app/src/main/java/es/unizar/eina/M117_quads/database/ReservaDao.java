package es.unizar.eina.M117_quads.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object (DAO) para los reservas */
@Dao
public interface ReservaDao {

    /** Inserta un nuevo reserva en la base de datos */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Reserva reserva);

    /** Actualiza un reserva existente */
    @Update
    int update(Reserva reserva);

    /** Elimina un reserva existente */
    @Delete
    int delete(Reserva reserva);

    /** Devuelve todos los reservas ordenados por nombre ascendente */
    @Query("SELECT * FROM Reservas ORDER BY nombre ASC")
    LiveData<List<Reserva>> getOrderedReservasByNombre();

    /** Devuelve todos los reservas ordenados por número ascendente */
    @Query("SELECT * FROM Reservas ORDER BY numeroTelef ASC")
    LiveData<List<Reserva>> getOrderedReservasByNumero();

    /** Devuelve todos los reservas ordenados por fecha de recogida ascendente */
    @Query("SELECT * FROM Reservas ORDER BY fechaRecogida ASC")
    LiveData<List<Reserva>> getOrderedReservasByFechaRecogida();

    /** Devuelve todos los reservas ordenados por fecha de devolución ascendente */
    @Query("SELECT * FROM Reservas ORDER BY fechaDevolucion ASC")
    LiveData<List<Reserva>> getOrderedReservasByFechaDevolucion();

    /** Devuelve un reserva concreto por su identificador */
    @Query("SELECT * FROM Reservas WHERE reserva_id = :id LIMIT 1")
    LiveData<Reserva> getReservaById(int id);
}
