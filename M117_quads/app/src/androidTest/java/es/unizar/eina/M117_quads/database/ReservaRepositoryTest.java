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
 * Tests instrumentados para la clase {@link ReservaRepository}.
 * <p>
 * Se prueban los métodos de persistencia usando una base de datos Room
 * en memoria, aplicando la técnica de particiones de equivalencia.
 */
@RunWith(AndroidJUnit4.class)
public class ReservaRepositoryTest {

    /** Regla necesaria para que LiveData funcione correctamente en tests */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    private AppDatabase database;
    private ReservaRepository repository;

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

        repository = new ReservaRepositoryForTest(database);
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
     * nombre no nulo y no vacío, fechas correctas, telefono no nulo y no vacío
     */
    @Test
    public void insert_reserva_valida() throws Exception {
        Reserva reserva = new Reserva("Juan", "600123123", "10/05/2024", "12/05/2024");

        repository.insert(reserva);

        List<Reserva> reservas = LiveDataTestUtil.getValue(repository.getReservasOrderedByNombre());
        assertEquals(1, reservas.size());
    }

    /* ===========================
       CASOS DE PRUEBA – CLASES NO VÁLIDAS
       =========================== */

    /**
     * Caso no válido:
     * nombre es nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_nombre_nulo() {
        Reserva reserva = new Reserva(null, "600123123", "10/05/2024", "12/05/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * nombre está vacío
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_nombre_vacio() {
        Reserva reserva = new Reserva("", "600123123", "10/05/2024", "12/05/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * fechaRecogida > fechaDevolucion
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_fechaRecogida_mayor_fechaDevolucion() {
        Reserva reserva = new Reserva("Juan", "600123123", "15/06/2024", "14/06/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * fechaRecogida no cumple el formato DD/MM/YYYY
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_fechaRecogida_formato_incorrecto() {
        Reserva reserva = new Reserva("Juan", "600123123", "10-05-2024", "12/05/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * fechaRecogida no es una fecha válida
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_fechaRecogida_invalida() {
        Reserva reserva = new Reserva("Juan", "600123123", "32/05/2024", "12/05/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * fechaDevolucion no es una fecha válida
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_fechaDevolucion_invalida() {
        Reserva reserva = new Reserva("Juan", "600123123", "10/05/2024", "31/02/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * telefono es nulo
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_telefono_nulo() {
        Reserva reserva = new Reserva("Juan", null, "10/05/2024", "12/05/2024");
        repository.insert(reserva);
    }

    /**
     * Caso no válido:
     * telefono está vacío
     */
    @Test(expected = IllegalArgumentException.class)
    public void insert_reserva_telefono_vacio() {
        Reserva reserva = new Reserva("Juan", "", "10/05/2024", "12/05/2024");
        repository.insert(reserva);
    }

}
