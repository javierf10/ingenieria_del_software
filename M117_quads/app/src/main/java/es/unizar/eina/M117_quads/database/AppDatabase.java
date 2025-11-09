package es.unizar.eina.M117_quads.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que representa la base de datos Room de la aplicación.
 * Contiene las entidades y los DAOs correspondientes.
 */
@Database(entities = {Quad.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /** Métodos abstractos que devuelven los DAOs */
    public abstract QuadDao quadDao();
    // Cuando crees la entidad Reserva, aquí añadirías:
    // public abstract ReservaDao reservaDao();

    /** Instancia singleton de la base de datos */
    private static volatile AppDatabase INSTANCE;

    /** Pool de hilos para operaciones en background */
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Devuelve la instancia única de la base de datos.
     * Si no existe, la crea usando el contexto de aplicación.
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
