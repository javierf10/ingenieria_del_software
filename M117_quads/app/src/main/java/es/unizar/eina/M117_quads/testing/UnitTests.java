package es.unizar.eina.M117_quads.testing;

import android.util.Log;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;

/**
 * Clase que ejecuta automáticamente los casos de prueba
 * basados en clases de equivalencia para QuadRepository.
 */
public class UnitTests {

    private static final String TAG = "UNIT_TESTS";

    private final QuadRepository repository;

    public UnitTests(QuadRepository repository) {
        this.repository = repository;
    }

    /** Ejecuta todos los casos de prueba */
    public void runAllTests() {
        Log.d(TAG, "===== INICIO PRUEBAS DE INSERCION DE QUADS =====");

        test("T1_Quad_valido_tipo1",
                new Quad("1234ABC", 1, 10, "Valido"));

        test("T2_Quad_valido_tipo2",
                new Quad("5678XYZ", 2, 20, "Valido"));

        test("T3_Matricula_formato_incorrecto",
                new Quad("1234567", 1, 10, "Invalido"));

        test("T4_Matricula_corta",
                new Quad("1234AB", 1, 10, "Invalido"));

        test("T5_Matricula_larga",
                new Quad("1234ABCD", 1, 10, "Invalido"));

        test("T6_Tipo_menor_que_1",
                new Quad("1234ABC", 0, 10, "Invalido"));

        test("T7_Tipo_mayor_que_2",
                new Quad("1234ABC", 3, 10, "Invalido"));

        test("T8_Precio_no_positivo",
                new Quad("1234ABC", 1, 0, "Invalido"));

        Log.d(TAG, "===== FIN PRUEBAS DE INSERCION DE QUADS =====");
    }

    /** Ejecuta un caso de prueba individual */
    private void test(String testId, Quad quad) {
        try {
            repository.insert(quad);
            Log.d(TAG, testId + " -> OK (insert realizado)");
        } catch (Throwable t) {
            Log.d(TAG, testId + " -> ERROR: " + t.getClass().getSimpleName()
                    + " : " + t.getMessage());
        }
    }
}
