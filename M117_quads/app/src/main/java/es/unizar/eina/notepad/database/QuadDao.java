package es.unizar.eina.notepad.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/** Definición de un Data Access Object (DAO) para los quads */
@Dao
public interface QuadDao {

    /** Inserta un nuevo quad en la base de datos */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Quad quad);

    /** Actualiza un quad existente */
    @Update
    int update(Quad quad);

    /** Elimina un quad existente */
    @Delete
    int delete(Quad quad);

    /** Elimina todos los quads de la tabla */
    @Query("DELETE FROM Quads")
    void deleteAll();

    /** Devuelve todos los quads ordenados por matrícula ascendente */
    @Query("SELECT * FROM Quads ORDER BY matricula ASC")
    LiveData<List<Quad>> getOrderedQuads();

    /** Devuelve un quad concreto por su identificador */
    @Query("SELECT * FROM Quads WHERE quad_id = :id LIMIT 1")
    LiveData<Quad> getQuadById(int id);
}
