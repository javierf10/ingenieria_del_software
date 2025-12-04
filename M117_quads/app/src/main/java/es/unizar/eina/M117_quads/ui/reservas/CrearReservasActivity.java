package es.unizar.eina.M117_quads.ui.reservas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.ui.quads.ListaQuadsActivity;

public class CrearReservasActivity extends AppCompatActivity {

    private EditText mEditNombreView;
    private EditText mEditNumeroView;
    private EditText mEditFechaRecogidaView;
    private EditText mEditFechaDevolucionView;
    private ReservaViewModel mReservaViewModel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private ArrayList<Quad> quadsSeleccionados = new ArrayList<>();

    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        ArrayList<Quad> resultList = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            resultList = data.getParcelableArrayListExtra(ListaQuadsActivity.EXTRA_REPLY, Quad.class);
                        } else {
                            @SuppressWarnings("deprecation")
                            ArrayList<Parcelable> parcelableList = data.getParcelableArrayListExtra(ListaQuadsActivity.EXTRA_REPLY);
                            if (parcelableList != null) {
                                resultList = new ArrayList<>();
                                for (Parcelable p : parcelableList) {
                                    if (p instanceof Quad) {
                                        resultList.add((Quad) p);
                                    }
                                }
                            }
                        }

                        if (resultList != null) {
                            quadsSeleccionados = resultList;
                        }

                        Toast.makeText(this, "Quads seleccionados: " + quadsSeleccionados.size(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reservas);

        mEditNombreView = findViewById(R.id.edit_nombre);
        mEditNumeroView = findViewById(R.id.edit_numero);
        mEditFechaRecogidaView = findViewById(R.id.edit_fecha_recogida);
        mEditFechaDevolucionView = findViewById(R.id.edit_fecha_devolucion);

        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        final Button btnAnadirQuad = findViewById(R.id.btnAnadirQuad);
        btnAnadirQuad.setOnClickListener(v -> {
            Intent intent = new Intent(CrearReservasActivity.this, ListaQuadsActivity.class);
            mGetContent.launch(intent);
        });

        final Button button = findViewById(R.id.button_confirmar);
        button.setOnClickListener(view -> {
            String nombre = mEditNombreView.getText().toString();
            String numero = mEditNumeroView.getText().toString();
            String fechaRecogidaStr = mEditFechaRecogidaView.getText().toString();
            String fechaDevolucionStr = mEditFechaDevolucionView.getText().toString();

            if (nombre.isEmpty() || numero.isEmpty() || fechaRecogidaStr.isEmpty() || fechaDevolucionStr.isEmpty()) {
                Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
            } else {
                try {
                    Date fechaRecogida = sdf.parse(fechaRecogidaStr);
                    Date fechaDevolucion = sdf.parse(fechaDevolucionStr);

                    Reserva nuevaReserva = new Reserva(nombre, numero, fechaRecogida, fechaDevolucion);
                    mReservaViewModel.insert(nuevaReserva);

                    // TODO: Asociar los quads seleccionados a la nueva reserva

                    setResult(RESULT_OK, new Intent());
                    finish();
                } catch (ParseException e) {
                    Toast.makeText(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
