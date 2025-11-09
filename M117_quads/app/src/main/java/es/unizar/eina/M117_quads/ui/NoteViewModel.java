package es.unizar.eina.M117_quads.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;

public class NoteViewModel extends AndroidViewModel {

    private QuadRepository mQuadRepository;

    private final LiveData<List<Quad>> mAllNotes;

    public NoteViewModel(Application application) {
        super(application);
        mQuadRepository = new QuadRepository(application);
        mAllNotes = mQuadRepository.getAllQuads();
    }

    LiveData<List<Quad>> getAllNotes() { return mAllNotes; }

    public void insert(Quad quad) { mQuadRepository.insert(quad); }

}
