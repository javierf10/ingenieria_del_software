package es.unizar.eina.M117_quads.ui.quads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class ModificarQuadsActivity extends AppCompatActivity {

    private QuadViewModel quadViewModel;

    private EditText txtMatricula, txtTipo, txtPrecio, txtDescripcion;
    private int quadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_quad);

        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        txtMatricula = findViewById(R.id.txtMatricula);
        txtTipo = findViewById(R.id.txtTipo);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);

        // Recibir el id del quad
        quadId = getIntent().getIntExtra("id", -1);

        if (quadId == -1) {
            Toast.makeText(this, "Error: no se encontró el quad", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Cargar los datos del quad
        quadViewModel.getQuadById(quadId).observe(this, quad -> {
            if (quad != null) {
                txtMatricula.setText(quad.getMatricula());
                txtTipo.setText(quad.getTipo());
                txtPrecio.setText(String.valueOf(quad.getPrecio()));
                txtDescripcion.setText(quad.getDescripcion());
            }
        });

        // Botón confirmar
        btnConfirmar.setOnClickListener(v -> {
            String matricula = txtMatricula.getText().toString().trim();
            String tipoStr = txtTipo.getText().toString().trim();
            String precioStr = txtPrecio.getText().toString().trim();
            String descripcion = txtDescripcion.getText().toString().trim();

            if (matricula.isEmpty() || tipoStr.isEmpty() || precioStr.isEmpty()) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show();
                return;
            }

            int precio = Integer.parseInt(precioStr);
            int tipo = Integer.parseInt(tipoStr);

            Quad quadActualizado = new Quad(matricula, tipo, precio, descripcion);
            quadActualizado.setId(quadId);

            quadViewModel.update(quadActualizado);

            Toast.makeText(this, "Quad modificado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
