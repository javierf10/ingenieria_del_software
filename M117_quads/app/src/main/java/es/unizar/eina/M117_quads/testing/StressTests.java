package es.unizar.eina.M117_quads.testing;

import android.util.Log;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.QuadRepository;

public class StressTests {

    private static final String TAG = "STRESS_TEST";
    private final QuadRepository repository;

    public StressTests(QuadRepository repository) {
        this.repository = repository;
    }

    /**
     * Prueba de estrés:
     * Inserta quads con descripciones cada vez más largas
     * hasta que falle la inserción.
     */
    public void testDescripcionMaxima() {

        int length = 100;          // tamaño inicial
        int step = 500;            // incremento (no de 1 en 1)
        boolean running = true;

        while (running) {
            try {
                String descripcion = generarTexto(length);

                Quad quad = new Quad(
                        "9999AAA",    // matrícula válida fija
                        1,
                        10,
                        descripcion
                );

                repository.insert(quad);

                Log.d(TAG, "Inserción correcta con longitud descripción = " + length);

                length += step;

            } catch (Throwable t) {
                Log.d(TAG,
                        "FALLO al insertar con longitud descripción = " + length +
                                " | Excepción: " + t.getClass().getSimpleName() +
                                " | " + t.getMessage()
                );
                running = false;
            }
        }
    }

    private String generarTexto(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append('A');
        }
        return sb.toString();
    }
}
