package es.unizar.eina.M117_quads.ui.quads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class CrearQuadsActivity extends AppCompatActivity {

    private QuadViewModel quadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_quad);

        // Instancia propia de este ViewModel
        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        EditText txtMatricula = findViewById(R.id.txtMatricula);
        EditText txtTipo = findViewById(R.id.txtTipo);
        EditText txtPrecio = findViewById(R.id.txtPrecio);
        EditText txtDescripcion = findViewById(R.id.txtDescripcion);

        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String matricula = txtMatricula.getText().toString().trim();
            String tipoStr = txtTipo.getText().toString().trim();
            String precioStr = txtPrecio.getText().toString().trim();
            String descripcion = txtDescripcion.getText().toString().trim();

            if (matricula.isEmpty() || tipoStr.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Faltan datos obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            int precio = Integer.parseInt(precioStr);
            int tipo = Integer.parseInt(tipoStr);

            Quad quad = new Quad(matricula, tipo, precio, descripcion);
            quadViewModel.insert(quad);

            Toast.makeText(this, "Quad creado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
