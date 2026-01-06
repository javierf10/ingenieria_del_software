package es.unizar.eina.M117_quads.database;

/**
 * Repositorio específico para tests instrumentados.
 * Permite inyectar una base de datos Room en memoria.
 */
public class QuadRepositoryForTest extends QuadRepository {

    public QuadRepositoryForTest(AppDatabase db) {
        super(db);
    }
}