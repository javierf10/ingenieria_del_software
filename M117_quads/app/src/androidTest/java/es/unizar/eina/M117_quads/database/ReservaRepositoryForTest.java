package es.unizar.eina.M117_quads.database;

/**
 * Repositorio específico para tests instrumentados.
 * Permite inyectar una base de datos Room en memoria.
 */
public class ReservaRepositoryForTest extends ReservaRepository {

    public ReservaRepositoryForTest(AppDatabase db) {
        super(db);
    }
}