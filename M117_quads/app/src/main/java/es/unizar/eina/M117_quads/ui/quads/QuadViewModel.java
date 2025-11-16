package es.unizar.eina.M117_quads.ui.quads;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;

/**
 * ViewModel que gestiona los datos de los quads y proporciona métodos
 * para insertar, actualizar, eliminar y consultar quads desde la base de datos.
 * <p>
 * Extiende {@link AndroidViewModel} para poder usar el contexto de la aplicación.
 */
public class QuadViewModel extends AndroidViewModel {

    /** Repositorio que gestiona el acceso a los datos de los quads. */
    private final QuadRepository repository;

    /** Lista observable de todos los quads. */
    private final LiveData<List<Quad>> allQuads;

    /**
     * Constructor del ViewModel.
     *
     * @param application Contexto de la aplicación
     */
    public QuadViewModel(@NonNull Application application) {
        super(application);
        repository = new QuadRepository(application);
        allQuads = repository.getAllQuads();
    }

    /**
     * Obtiene la lista observable de todos los quads.
     *
     * @return {@link LiveData} con la lista de quads
     */
    public LiveData<List<Quad>> getAllQuads() {
        return allQuads;
    }

    /**
     * Inserta un nuevo quad en la base de datos.
     *
     * @param quad Quad a insertar
     */
    public void insert(Quad quad) {
        repository.insert(quad);
    }

    /**
     * Obtiene un quad específico según su ID.
     *
     * @param id Identificador del quad
     * @return {@link LiveData} con el quad correspondiente al ID
     */
    public LiveData<Quad> getQuadById(int id) {
        return repository.getQuadById(id);
    }

    /**
     * Actualiza un quad existente en la base de datos.
     *
     * @param quad Quad a actualizar
     */
    public void update(Quad quad) {
        repository.update(quad);
    }

    /**
     * Elimina un quad de la base de datos.
     *
     * @param quad Quad a eliminar
     */
    public void delete(Quad quad) {
        repository.delete(quad);
    }
}
