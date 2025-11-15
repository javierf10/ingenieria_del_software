package es.unizar.eina.M117_quads.database;

import android.app.Application;


import androidx.lifecycle.LiveData;

import java.util.List;

public class QuadRepository {
    private final QuadDao quadDao;
    private final LiveData<List<Quad>> allQuads;

    public QuadRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        quadDao = db.quadDao();
        allQuads = quadDao.getOrderedQuads();
    }

    public LiveData<List<Quad>> getAllQuads() {
        return allQuads;
    }

    public void insert(Quad quad) {
        AppDatabase.databaseWriteExecutor.execute(() -> quadDao.insert(quad));
    }

    public void delete(Quad quad) {
        AppDatabase.databaseWriteExecutor.execute(() -> quadDao.delete(quad));
    }

}
