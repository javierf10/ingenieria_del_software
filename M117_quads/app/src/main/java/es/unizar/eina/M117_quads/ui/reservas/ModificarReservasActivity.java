package es.unizar.eina.M117_quads.ui.reservas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

/**
 * Actividad para modificar los datos de una reserva existente.
 * <p>
 * Permite al usuario editar el nombre, número, fecha de recogida y fecha de devolución de una reserva
 * previamente creada y guardar los cambios en la base de datos a través del {@link ReservaViewModel}.
 */
public class ModificarReservasActivity extends AppCompatActivity {

    /** Campo de texto para el nombre de la reserva */
    private EditText mEditNombreView;
    /** Campo de texto para el número de la reserva */
    private EditText mEditNumeroView;
    /** Campo de texto para la fecha de recogida de la reserva */
    private EditText mEditFechaRecogidaView;
    /** Campo de texto para la fecha de devolución de la reserva */
    private EditText mEditFechaDevolucionView;

    /** ViewModel para interactuar con los datos de las reservas */
    private ReservaViewModel mReservaViewModel;

    /** ID de la reserva que se está modificando */
    private int id;

    /** Formateador de fechas */
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    /** Lista de quads seleccionados para la reserva */
    private ArrayList<Quad> quadsSeleccionados = new ArrayList<>();

    /**
     * Launcher para iniciar la actividad de selección de quads y recibir el resultado.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);

        mEditNombreView = findViewById(R.id.editNombre);
        mEditNumeroView = findViewById(R.id.editNumero);
        mEditFechaRecogidaView = findViewById(R.id.editFechaRecogida);
        mEditFechaDevolucionView = findViewById(R.id.editFechaDevolucion);

        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getInt("id", -1);
            if (id != -1) {
                mReservaViewModel.getReservaById(id).observe(this, reserva -> {
                    if (reserva != null) {
                        mEditNombreView.setText(reserva.getNombre());
                        mEditNumeroView.setText(reserva.getNumeroTelef());

                        mEditFechaRecogidaView.setText(sdf.format(reserva.getFechaRecogida()));
                        mEditFechaDevolucionView.setText(sdf.format(reserva.getFechaDevolucion()));
                    }
                });
            }
        }

        /*final Button btnAnadirQuad = findViewById(R.id.btnAnadirQuad);
        btnAnadirQuad.setOnClickListener(v -> {
            Intent intent = new Intent(ModificarReservasActivity.this, ListaQuadsActivity.class);
            mGetContent.launch(intent);
        });**/

        final Button button = findViewById(R.id.btnConfirmar);
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

                    Reserva reservaActualizada = new Reserva(nombre, numero, fechaRecogida, fechaDevolucion);
                    reservaActualizada.setId(id);
                    // TODO: Actualizar también los quads asociados a la reserva
                    mReservaViewModel.update(reservaActualizada);
                    setResult(RESULT_OK, new Intent());
                    finish();
                } catch (ParseException e) {
                    Toast.makeText(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
