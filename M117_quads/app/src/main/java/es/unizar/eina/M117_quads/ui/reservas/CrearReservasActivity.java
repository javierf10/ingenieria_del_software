package es.unizar.eina.M117_quads.ui.reservas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Reserva;

/**
 * Actividad para crear nuevos reservas en la aplicación.
 * <p>
 * Permite al usuario introducir los datos de un reserva (nombre, numero, fechas)
 * y almacenarlos en la base de datos mediante el {@link ReservaViewModel}.
 */
public class CrearReservasActivity extends AppCompatActivity {

    /** ViewModel que gestiona los reservas y la comunicación con la base de datos. */
    private ReservaViewModel reservaViewModel;

    private EditText mEditNombre;
    private EditText mEditNumero;
    private EditText mEditFechaRecogida;
    private EditText mEditFechaDevolucion;

    /**
     * Método llamado al crear la actividad.
     * <p>
     * Inicializa la interfaz de usuario, obtiene las referencias a los elementos de la vista,
     * y configura el listener del botón de guardar para insertar una nueva reserva.
     *
     * @param savedInstanceState Estado previo de la actividad, si lo hubiera.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reservas);
        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        mEditNombre = findViewById(R.id.edit_nombre);
        mEditNumero = findViewById(R.id.edit_numero);
        mEditFechaRecogida = findViewById(R.id.edit_fecha_recogida);
        mEditFechaDevolucion = findViewById(R.id.edit_fecha_devolucion);

        final Button button = findViewById(R.id.button_confirmar);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(mEditNombre.getText()) ||
                    TextUtils.isEmpty(mEditFechaRecogida.getText()) ||
                    TextUtils.isEmpty(mEditFechaDevolucion.getText())) {
                Toast.makeText(
                        getApplicationContext(),
                        "Los campos nombre, fecha de recogida y fecha de devolución son obligatorios",
                        Toast.LENGTH_LONG).show();
            } else {
                String nombre = mEditNombre.getText().toString();
                String numero = mEditNumero.getText().toString();
                String fechaRecogidaStr = mEditFechaRecogida.getText().toString();
                String fechaDevolucionStr = mEditFechaDevolucion.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                try {
                    Date fechaRecogida = sdf.parse(fechaRecogidaStr);
                    Date fechaDevolucion = sdf.parse(fechaDevolucionStr);

                    if (fechaDevolucion.before(fechaRecogida)) {
                        Toast.makeText(getApplicationContext(), "La fecha de devolución no puede ser anterior a la de recogida", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Reserva reserva = new Reserva(nombre, numero, fechaRecogida, fechaDevolucion);
                    reservaViewModel.insert(reserva);
                    setResult(RESULT_OK);
                    finish();
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Formato de fecha incorrecto. Use dd/MM/yyyy.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
