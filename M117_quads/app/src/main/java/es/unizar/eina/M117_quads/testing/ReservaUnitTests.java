package es.unizar.eina.M117_quads.testing;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.database.ReservaRepository;

/**
 * Clase que ejecuta automáticamente los casos de prueba
 * basados en clases de equivalencia para ReservaRepository.
 */
public class ReservaUnitTests {

    private static final String TAG = "RESERVA_UNIT_TESTS";

    private final ReservaRepository repository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    public ReservaUnitTests(ReservaRepository repository) {
        this.repository = repository;
        // Permite que el formateador sea estricto (e.g., no convierte "32/05/2024")
        this.dateFormat.setLenient(false);
    }
    /**
     * Convierte un String a un objeto Date.
     * Devuelve null si el formato es inválido.
     */
    private Date toDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Si el formato es incorrecto, la conversión falla y devolvemos null
            return null;
        }
    }

    /** Ejecuta todos los casos de prueba */
    public void runAllTests() {
        Log.d(TAG, "===== INICIO PRUEBAS DE INSERCION DE RESERVAS =====");

        test("T1_Reserva_valida",
                new Reserva("Juan", "600123123", toDate("10/05/2024"), toDate("12/05/2024")));

        test("T2_Reserva_nombre_nulo",
                new Reserva(null, "600123123", toDate("10/05/2024"), toDate("12/05/2024")));

        test("T3_Reserva_nombre_vacio",
                new Reserva("", "600123123", toDate("10/05/2024"), toDate("12/05/2024")));

        test("T4_Reserva_fechaRecogida_mayor_fechaDevolucion",
                new Reserva("Juan", "600123123", toDate("15/06/2024"), toDate("14/06/2024")));

        // Los tests de formato inválido ahora se prueban pasando 'null' al constructor,
        // ya que la conversión de String a Date fallará y devolverá null.
        test("T5_Reserva_fechaRecogida_formato_incorrecto",
                new Reserva("Juan", "600123123", toDate("10-05-2024"), toDate("12/05/2024")));

        test("T6_Reserva_fechaRecogida_invalida",
                new Reserva("Juan", "600123123", toDate("32/05/2024"), toDate("12/05/2024")));

        test("T7_Reserva_fechaDevolucion_invalida",
                new Reserva("Juan", "600123123", toDate("10/05/2024"), toDate("31/02/2024")));

        test("T8_Reserva_telefono_nulo",
                new Reserva("Juan", null, toDate("10/05/2024"), toDate("12/05/2024")));

        test("T9_Reserva_telefono_vacio",
                new Reserva("Juan", "", toDate("10/05/2024"), toDate("12/05/2024")));

        Log.d(TAG, "===== FIN PRUEBAS DE INSERCION DE RESERVAS =====");
    }

    /** Ejecuta un caso de prueba individual */
    private void test(String testId, Reserva reserva) {
        try {
            repository.insert(reserva);
            Log.d(TAG, testId + " -> OK (insert realizado)");
        } catch (Throwable t) {
            Log.d(TAG, testId + " -> ERROR: " + t.getClass().getSimpleName()
                    + " : " + t.getMessage());
        }
    }
}
