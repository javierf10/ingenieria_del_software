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
 * Actividad para modificar los datos de un quad existente.
 * <p>
 * Permite al usuario editar la matrícula, tipo, precio y descripción de un quad
 * previamente creado y guardar los cambios en la base de datos a través del {@link QuadViewModel}.
 */
public class ModificarQuadsActivity extends AppCompatActivity {

    /** ViewModel que gestiona los quads y la comunicación con la base de datos. */
    private QuadViewModel quadViewModel;

    /** Campos de la interfaz de usuario para editar los datos del quad. */
    private EditText txtMatricula, txtTipo, txtPrecio, txtDescripcion;

    /** Identificador del quad que se va a modificar. */
    private int quadId;

    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa la interfaz de usuario, obtiene las referencias a los elementos de la vista,
     * carga los datos del quad a modificar y configura el listener del botón de confirmar
     * para actualizar el quad.
     *
     * @param savedInstanceState Estado previo de la actividad, si lo hubiera.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_quad);

        // Instancia del ViewModel
        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        // Referencias a los elementos de la interfaz
        txtMatricula = findViewById(R.id.txtMatricula);
        txtTipo = findViewById(R.id.txtTipo);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        // Obtener el id del quad a modificar desde el Intent
        quadId = getIntent().getIntExtra("id", -1);

        if (quadId == -1) {
            Toast.makeText(this, "Error: no se encontró el quad", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar los datos del quad en los campos de la interfaz
        quadViewModel.getQuadById(quadId).observe(this, quad -> {
            if (quad != null) {
                txtMatricula.setText(quad.getMatricula());
                txtTipo.setText(String.valueOf(quad.getTipo()));
                txtPrecio.setText(String.valueOf(quad.getPrecio()));
                txtDescripcion.setText(quad.getDescripcion());
            }
        });

        // Configurar el botón para confirmar los cambios
        btnConfirmar.setOnClickListener(v -> {
            String matricula = txtMatricula.getText().toString().trim();
            String tipoStr = txtTipo.getText().toString().trim();
            String precioStr = txtPrecio.getText().toString().trim();
            String descripcion = txtDescripcion.getText().toString().trim();

            // Validación de campos obligatorios
            if (matricula.isEmpty() || tipoStr.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Conversión de Strings a enteros
            int precio = Integer.parseInt(precioStr);
            int tipo = Integer.parseInt(tipoStr);

            // Crear un objeto Quad actualizado y establecer su id
            Quad quadActualizado = new Quad(matricula, tipo, precio, descripcion);
            quadActualizado.setId(quadId);

            // Actualizar el quad en la base de datos
            quadViewModel.update(quadActualizado);

            Toast.makeText(this, "Quad modificado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
