//package es.unizar.eina.M117_quads.ui.reservas;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import es.unizar.eina.M117_quads.R;
//import es.unizar.eina.M117_quads.database.Reserva;
//
///**
// * Actividad para modificar los datos de una reserva existente.
// * <p>
// * Permite al usuario editar el nombre, número, fecha de recogida y fecha de devolución de una reserva
// * previamente creada y guardar los cambios en la base de datos a través del {@link ReservaViewModel}.
// */
//public class ModificarReservasActivity extends AppCompatActivity {
//
//    /** Campo de texto para el nombre de la reserva */
//    private EditText mEditNombreView;
//    /** Campo de texto para el número de la reserva */
//    private EditText mEditNumeroView;
//    /** Campo de texto para la fecha de recogida de la reserva */
//    private EditText mEditFechaRecogidaView;
//    /** Campo de texto para la fecha de devolución de la reserva */
//    private EditText mEditFechaDevolucionView;
//
//    /** ViewModel para interactuar con los datos de las reservas */
//    private ReservaViewModel mReservaViewModel;
//
//    /** ID de la reserva que se está modificando */
//    private int id;
//
//    /**
//     * Método llamado al crear la actividad.
//     * <p>
//     * Inicializa la interfaz de usuario, obtiene las referencias a los elementos de la vista,
//     * carga los datos de la reserva a modificar y configura el listener del botón de confirmar
//     * para actualizar la reserva.
//     *
//     * @param savedInstanceState Estado previo de la actividad, si lo hubiera.
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_modificar_reserva);
//
//        mEditNombreView = findViewById(R.id.nombre);
//        mEditNumeroView = findViewById(R.id.numero);
//        mEditFechaRecogidaView = findViewById(R.id.fecha_recogida);
//        mEditFechaDevolucionView = findViewById(R.id.fecha_devolucion);
//
//        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
//
//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            id = extras.getInt("reserva_id");
//            mReservaViewModel.getReserva(id).observe(this, reserva -> {
//                mEditNombreView.setText(reserva.getNombre());
//                mEditNumeroView.setText(reserva.getNumero());
//                mEditFechaRecogidaView.setText(reserva.getFechaRecogida());
//                mEditFechaDevolucionView.setText(reserva.getFechaDevolucion());
//            });
//        }
//
//        final Button button = findViewById(R.id.confirm_button);
//        button.setOnClickListener(view -> {
//            Intent replyIntent = new Intent();
//            String nombre = mEditNombreView.getText().toString();
//            String numero = mEditNumeroView.getText().toString();
//            String fechaRecogida = mEditFechaRecogidaView.getText().toString();
//            String fechaDevolucion = mEditFechaDevolucionView.getText().toString();
//
//            if (nombre.isEmpty() || numero.isEmpty() || fechaRecogida.isEmpty() || fechaDevolucion.isEmpty()) {
//                Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
//            } else {
//                Reserva reserva = new Reserva(id, nombre, numero, fechaRecogida, fechaDevolucion);
//                mReservaViewModel.update(reserva);
//                setResult(RESULT_OK, replyIntent);
//                finish();
//            }
//        });
//    }
//}
//