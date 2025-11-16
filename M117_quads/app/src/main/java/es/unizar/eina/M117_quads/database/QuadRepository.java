package es.unizar.eina.M117_quads.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repositorio para la gestión de objetos {@link Quad}.
 * <p>
 * Esta clase proporciona una capa de abstracción sobre la base de datos,
 * permitiendo acceder y modificar los datos de los quads de manera asíncrona
 * usando {@link LiveData} y un ejecutor de base de datos.
 */
public class QuadRepository {

    /** DAO de acceso a los quads en la base de datos. */
    private final QuadDao quadDao;

    /** Lista de todos los quads ordenados, envuelta en LiveData para observar cambios. */
    private final LiveData<List<Quad>> allQuads;

    /**
     * Constructor del repositorio.
     *
     * @param application Contexto de la aplicación usado para obtener la base de datos.
     */
    public QuadRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        quadDao = db.quadDao();
        allQuads = quadDao.getOrderedQuads();
    }

    /**
     * Devuelve todos los quads de la base de datos.
     *
     * @return LiveData que contiene la lista de quads ordenados.
     */
    public LiveData<List<Quad>> getAllQuads() {
        return allQuads;
    }

    /**
     * Inserta un nuevo quad en la base de datos de manera asíncrona.
     *
     * @param quad Quad a insertar.
     */
    public void insert(Quad quad) {
        AppDatabase.databaseWriteExecutor.execute(() -> quadDao.insert(quad));
    }

    /**
     * Actualiza un quad existente en la base de datos de manera asíncrona.
     *
     * @param quad Quad a actualizar.
     */
    public void update(Quad quad) {
        AppDatabase.databaseWriteExecutor.execute(() -> quadDao.update(quad));
    }

    /**
     * Obtiene un quad por su identificador.
     *
     * @param id Identificador del quad.
     * @return LiveData que contiene el quad correspondiente, o {@code null} si no existe.
     */
    public LiveData<Quad> getQuadById(int id) {
        return quadDao.getQuadById(id);
    }

    /**
     * Elimina un quad de la base de datos de manera asíncrona.
     *
     * @param quad Quad a eliminar.
     */
    public void delete(Quad quad) {
        AppDatabase.databaseWriteExecutor.execute(() -> quadDao.delete(quad));
    }

}
