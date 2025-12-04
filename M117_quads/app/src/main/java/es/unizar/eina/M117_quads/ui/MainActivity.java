package es.unizar.eina.M117_quads.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.ui.quads.QuadsActivity;
import es.unizar.eina.M117_quads.ui.reservas.ReservasActivity;

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
}
