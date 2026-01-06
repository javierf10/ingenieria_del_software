package es.unizar.eina.M117_quads.database;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Tests instrumentados para la clase {@link QuadRepository}.
 * <p>
 * Se prueban los métodos de persistencia usando una base de datos Room
 * en memoria, aplicando la técnica de particiones de equivalencia.
 */
@RunWith(AndroidJUnit4.class)
public class QuadRepositoryTest {

    /** Regla necesaria para que LiveData funcione correctamente en tests */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    private AppDatabase database;
    private QuadRepository repository;

    /**
     * Inicializa una base de datos Room en memoria antes de cada test.
     */
    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();

        database = Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase.class
        ).allowMainThreadQueries().build();

        repository = new QuadRepositoryForTest(database);
    }

    /**
     * Cierra la base de datos tras cada test.
     */
    @After
    public void tearDown() {
        database.close();
    }

/* ===========================
   CASOS DE PRUEBA – CLASES VÁLIDAS
   =========================== */

    /**
     * Caso válido:
     * matrícula correcta, tipo = 1, precio > 0
     */
    @Test
    public void insert_quad_valido_tipo1() throws Exception {
        Quad quad = new Quad("1234ABC", 1, 10, "Quad válido");

        repository.insert(quad);

        List<Quad> quads = LiveDataTestUtil.getValue(repository.getAllQuads());
        assertEquals(1, quads.size());
    }

    /**
     * Caso válido:
     * matrícula correcta, tipo = 2, precio > 0
     */
    @Test
    public void insert_quad_valido_tipo2() throws Exception {
        Quad quad = new Quad("5678XYZ", 2, 20, "Otro quad válido");

        repository.insert(quad);

        List<Quad> quads = LiveDataTestUtil.getValue(repository.getAllQuads());
        assertEquals(1, quads.size());
    }

    /* ===========================
       CASOS DE PRUEBA – CLASES NO VÁLIDAS
       =========================== */

    /**
     * Caso no válido:
     * matrícula no cumple formato NNNNLLL
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_matricula_formatoIncorrecto() {
        Quad quad = new Quad("1234567", 1, 10, "Matrícula incorrecta");
        repository.insert(quad);
    }

    /**
     * Caso no válido:
     * longitud matrícula < 7
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_matricula_corta() {
        Quad quad = new Quad("1234AB", 1, 10, "Matrícula corta");
        repository.insert(quad);
    }


    /**
     * Caso no válido:
     * longitud matrícula > 7
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_matricula_larga() {
        Quad quad = new Quad("1234ABCD", 1, 10, "Matrícula larga");
        repository.insert(quad);
    }


    /**
     * Caso no válido:
     * tipo < 1
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_tipo_menor_que_1() {
        Quad quad = new Quad("1234ABC", 0, 10, "Tipo inválido");
        repository.insert(quad);
    }

    /**
     * Caso no válido:
     * tipo > 2
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_tipo_mayor_que_2() {
        Quad quad = new Quad("1234ABC", 3, 10, "Tipo inválido");
        repository.insert(quad);
    }


    /**
     * Caso no válido:
     * precio <= 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_quad_precio_no_positivo() {
        Quad quad = new Quad("1234ABC", 1, 0, "Precio inválido");
        repository.insert(quad);
    }

}
