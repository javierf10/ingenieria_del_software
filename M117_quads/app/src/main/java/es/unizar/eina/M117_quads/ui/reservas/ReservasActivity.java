//package es.unizar.eina.M117_quads.ui.reservas;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//
//import es.unizar.eina.M117_quads.database.Reserva;
//import es.unizar.eina.M117_quads.R;
//
///**
// * Actividad que muestra la lista de reservas y permite crear, modificar o eliminar reservas.
// * <p>
// * Implementa la interfaz {@link ReservaAdapter.OnReservaClickListener} para manejar las acciones
// * de los botones dentro de cada item del RecyclerView.
// */
//public class ReservasActivity extends AppCompatActivity implements ReservaAdapter.OnReservaClickListener {
//
//    /** ViewModel que gestiona la información de los reservas. */
//    private ReservaViewModel reservaViewModel;
//
//    /**
//     * Método llamado al crear la actividad.
//     * Inicializa el RecyclerView con la lista de reservas, configura el botón
//     * para crear un nuevo reserva y observa los cambios en la lista de reservas.
//     *
//     * @param savedInstanceState Estado previo de la actividad (si existe)
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reservas);
//
//        // Configuración del botón para crear un nuevo reserva
//        Button btnCrear = findViewById(R.id.btnCrearReserva);
//        btnCrear.setOnClickListener(v -> {
//            Intent i = new Intent(ReservasActivity.this, CrearReservasActivity.class);
//            i.putExtra("modo", "crear");
//            startActivity(i);
//        });
//
//        // Configuración del RecyclerView
//        RecyclerView recycler = findViewById(R.id.recyclerReservas);
//        recycler.setLayoutManager(new LinearLayoutManager(this));
//
//        ReservaAdapter adapter = new ReservaAdapter(this);
//        recycler.setAdapter(adapter);
//
//        // Inicialización del ViewModel y observación de cambios en los reservas
//        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
//        reservaViewModel.getAllReservas().observe(this, adapter::setReservas);
//    }
//
//    /**
//     * Acción a realizar cuando se solicita modificar un reserva.
//     * Inicia la actividad {@link ModificarReservasActivity} pasando el id del reserva a editar.
//     *
//     * @param reserva Reserva que se desea modificar
//     */
//    @Override
//    public void onModificar(Reserva reserva) {
//        Intent i = new Intent(ReservasActivity.this, ModificarReservasActivity.class);
//        i.putExtra("modo", "editar");
//        i.putExtra("id", reserva.getId());
//        startActivity(i);
//    }
//
//    /**
//     * Acción a realizar cuando se solicita eliminar un reserva.
//     * Llama al ViewModel para borrar el reserva de la base de datos.
//     *
//     * @param reserva Reserva que se desea eliminar
//     */
//    @Override
//    public void onEliminar(Reserva reserva) {
//        reservaViewModel.delete(reserva);
//    }
//}
