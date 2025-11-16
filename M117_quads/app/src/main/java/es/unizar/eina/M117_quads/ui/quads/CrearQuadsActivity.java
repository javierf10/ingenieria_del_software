package es.unizar.eina.M117_quads.ui.quads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

/**
 * Actividad para crear nuevos quads en la aplicación.
 * <p>
 * Permite al usuario introducir los datos de un quad (matrícula, tipo, precio y descripción)
 * y almacenarlos en la base de datos mediante el {@link QuadViewModel}.
 */
public class CrearQuadsActivity extends AppCompatActivity {

    /** ViewModel que gestiona los quads y la comunicación con la base de datos. */
    private QuadViewModel quadViewModel;

    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa la interfaz de usuario, obtiene las referencias a los elementos de la vista,
     * y configura el listener del botón de guardar para insertar un nuevo quad.
     *
     * @param savedInstanceState Estado previo de la actividad, si lo hubiera.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_quad);

        // Instancia propia del ViewModel
        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        // Referencias a los elementos de la interfaz
        EditText txtMatricula = findViewById(R.id.txtMatricula);
        EditText txtTipo = findViewById(R.id.txtTipo);
        EditText txtPrecio = findViewById(R.id.txtPrecio);
        EditText txtDescripcion = findViewById(R.id.txtDescripcion);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        // Listener para el botón de guardar
        btnGuardar.setOnClickListener(v -> {
            String matricula = txtMatricula.getText().toString().trim();
            String tipoStr = txtTipo.getText().toString().trim();
            String precioStr = txtPrecio.getText().toString().trim();
            String descripcion = txtDescripcion.getText().toString().trim();

            // Validación de campos obligatorios
            if (matricula.isEmpty() || tipoStr.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Faltan datos obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            // Conversión de Strings a enteros
            int precio = Integer.parseInt(precioStr);
            int tipo = Integer.parseInt(tipoStr);

            // Creación del nuevo quad y guardado en la base de datos
            Quad quad = new Quad(matricula, tipo, precio, descripcion);
            quadViewModel.insert(quad);

            Toast.makeText(this, "Quad creado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
