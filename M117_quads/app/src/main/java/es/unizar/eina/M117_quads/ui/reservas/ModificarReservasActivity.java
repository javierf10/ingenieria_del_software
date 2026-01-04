package es.unizar.eina.M117_quads.ui.reservas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.database.Reserva;
import es.unizar.eina.M117_quads.ui.quads.ListaQuadsActivity;
import es.unizar.eina.M117_quads.ui.quads.QuadSeleccionadoAdapter;
import es.unizar.eina.M117_quads.ui.quads.QuadViewModel;

public class ModificarReservasActivity extends AppCompatActivity implements QuadSeleccionadoAdapter.OnItemClickListener {

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
    private QuadViewModel mQuadViewModel;

    /** ID de la reserva que se está modificando */
    private int id;

    /** Formateador de fechas */
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    /** Lista de IDs de quads seleccionados para la reserva */
    private ArrayList<Integer> quadsSeleccionadosIds = new ArrayList<>();
    private ArrayList<Quad> quadsSeleccionados = new ArrayList<>();
    private QuadSeleccionadoAdapter mAdapter;

    /**
     * Launcher para iniciar la actividad de selección de quads y recibir el resultado.
     */
    private final ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        quadsSeleccionadosIds = data.getIntegerArrayListExtra(ListaQuadsActivity.EXTRA_REPLY_QUADS_SELECCIONADOS);
                        updateQuadsSeleccionados();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reserva);

        mEditNombreView = findViewById(R.id.editNombre);
        mEditNumeroView = findViewById(R.id.editNumero);
        mEditFechaRecogidaView = findViewById(R.id.editFechaRecogida);
        mEditFechaDevolucionView = findViewById(R.id.editFechaDevolucion);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_quads_seleccionados);
        mAdapter = new QuadSeleccionadoAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
        mQuadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            id = extras.getInt("id", -1);
            if (id != -1) {
                mReservaViewModel.getReservaById(id).observe(this, reserva -> {
                    if (reserva != null) {
                        mEditNombreView.setText(reserva.getNombre());
                        mEditNumeroView.setText(reserva.getNumeroTelef());

                        if (reserva.getFechaRecogida() != null) {
                            mEditFechaRecogidaView.setText(sdf.format(reserva.getFechaRecogida()));
                        }
                        if (reserva.getFechaDevolucion() != null) {
                            mEditFechaDevolucionView.setText(sdf.format(reserva.getFechaDevolucion()));
                        }
                    }
                });
                
                mReservaViewModel.getQuadIdsForReserva(id).observe(this, quadIds -> {
                    if (quadIds != null) {
                        quadsSeleccionadosIds = new ArrayList<>(quadIds);
                        updateQuadsSeleccionados();
                    }
                });
            }
        }

        final Button btnAnadirQuad = findViewById(R.id.btnAnadirQuad);
        btnAnadirQuad.setOnClickListener(v -> {
            Intent intent = new Intent(ModificarReservasActivity.this, ListaQuadsActivity.class);
            intent.putIntegerArrayListExtra("quads_ya_seleccionados", quadsSeleccionadosIds);
            mGetContent.launch(intent);
        });

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
                    
                    mReservaViewModel.update(reservaActualizada, quadsSeleccionadosIds);
                    setResult(RESULT_OK, new Intent());
                    finish();
                } catch (ParseException e) {
                    Toast.makeText(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateQuadsSeleccionados() {
        if (quadsSeleccionadosIds != null && !quadsSeleccionadosIds.isEmpty()) {
            mQuadViewModel.getQuadsByIds(quadsSeleccionadosIds).observe(this, quads -> {
                quadsSeleccionados = new ArrayList<>(quads);
                mAdapter.setQuads(quadsSeleccionados);
            });
        } else {
            quadsSeleccionados.clear();
            mAdapter.setQuads(quadsSeleccionados);
        }
    }

    @Override
    public void onEliminarClick(Quad quad) {
        quadsSeleccionadosIds.remove(Integer.valueOf(quad.getId()));
        updateQuadsSeleccionados();
    }
}
