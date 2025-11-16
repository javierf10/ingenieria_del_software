package es.unizar.eina.M117_quads.ui.quads;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;

public class QuadViewModel extends AndroidViewModel {

    private final QuadRepository repository;
    private final LiveData<List<Quad>> allQuads;

    public QuadViewModel(@NonNull Application application) {
        super(application);
        repository = new QuadRepository(application);
        allQuads = repository.getAllQuads();
    }

    public LiveData<List<Quad>> getAllQuads() {
        return allQuads;
    }

    public void insert(Quad quad) {
        repository.insert(quad);
    }

    public LiveData<Quad> getQuadById(int id) {
        return repository.getQuadById(id);
    }

    public void update(Quad quad) {
        repository.update(quad);
    }

    public void delete(Quad quad) {
        repository.delete(quad);
    }
}
