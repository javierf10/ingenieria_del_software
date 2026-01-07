package es.unizar.eina.M117_quads.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.ui.quads.QuadsActivity;
import es.unizar.eina.M117_quads.ui.reservas.ReservasActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import es.unizar.eina.M117_quads.database.QuadRepository;
import es.unizar.eina.M117_quads.database.ReservaRepository;

import es.unizar.eina.M117_quads.testing.VolumeTestHelper;
import es.unizar.eina.M117_quads.testing.UnitTests;
import es.unizar.eina.M117_quads.testing.ReservaUnitTests;
import es.unizar.eina.M117_quads.testing.StressTests;


/**
 * Actividad principal de la aplicación.
 * <p>
 * Muestra botones para navegar a las diferentes secciones de la aplicación,
 * como la gestión de quads y reservas.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa la interfaz de usuario y configura los botones para
     * navegar a otras actividades.
     *
     * @param savedInstanceState Contiene el estado previo de la actividad, si existía
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botón para navegar a la actividad de quads
        Button btnQuads = findViewById(R.id.btnQuads);
        // Botón para navegar a la actividad de reservas
        Button btnReservas = findViewById(R.id.btnReservas);

        // Acción al pulsar el botón de quads
        btnQuads.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, QuadsActivity.class);
            startActivity(i);
        });

        // Acción al pulsar el botón de reservas
        btnReservas.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ReservasActivity.class);
            startActivity(i);
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tests, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        QuadRepository quadRepo = new QuadRepository(getApplication());
        ReservaRepository reservaRepo = new ReservaRepository(getApplication());

        if (item.getItemId() == R.id.menu_volume_tests) {

            VolumeTestHelper.insertManyQuads(quadRepo, 100);
            VolumeTestHelper.insertManyReservas(reservaRepo, 20000);

            Toast.makeText(this, "Prueba de volumen ejecutada", Toast.LENGTH_LONG).show();
            return true;
        }

        if (item.getItemId() == R.id.menu_cleanup_tests) {

            quadRepo.deleteTestQuads();
            reservaRepo.deleteTestReservas();

            Toast.makeText(this, "Datos de prueba volumen eliminados", Toast.LENGTH_LONG).show();
            return true;
        }

        if (item.getItemId() == R.id.menu_unit_equivalence_tests_quads) {

            UnitTests unitTests = new UnitTests(quadRepo);
            unitTests.runAllTests();

            Toast.makeText(this, "Datos de prueba unitarios (Quads) ejecutados", Toast.LENGTH_LONG).show();
            return true;
        }

        if (item.getItemId() == R.id.menu_unit_equivalence_tests_reservas) {

            ReservaUnitTests reservaUnitTests = new ReservaUnitTests(reservaRepo);
            reservaUnitTests.runAllTests();

            Toast.makeText(this, "Datos de prueba unitarios (Reservas) ejecutados", Toast.LENGTH_LONG).show();
            return true;
        }

        if (item.getItemId() == R.id.menu_stress_test) {
            mostrarConfirmacionStressTest();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mostrarConfirmacionStressTest() {
        new AlertDialog.Builder(this)
                .setTitle("Prueba de estrés")
                .setMessage(
                        "Esta prueba puede provocar fallos graves en la aplicación " +
                                "y requerir borrar datos o reinstalarla.\n\n" +
                                "No se recomienda ejecutar en un dispositivo real.\n\n" +
                                "¿Deseas continuar?"
                )
                .setPositiveButton("Ejecutar", (dialog, which) -> ejecutarStressTest())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void ejecutarStressTest() {
        QuadRepository repository =
                new QuadRepository(getApplication());

        StressTests stressTests = new StressTests(repository);
        stressTests.testDescripcionMaxima();
    }


}
