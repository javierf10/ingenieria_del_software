package es.unizar.eina.M117_quads.ui.quads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.R;

/**
 * Actividad que muestra la lista de quads y permite crear, modificar o eliminar quads.
 * <p>
 * Implementa la interfaz {@link QuadAdapter.OnQuadClickListener} para manejar las acciones
 * de los botones dentro de cada item del RecyclerView.
 */
public class QuadsActivity extends AppCompatActivity implements QuadAdapter.OnQuadClickListener {

    /** ViewModel que gestiona la información de los quads. */
    private QuadViewModel quadViewModel;

    /**
     * Método llamado al crear la actividad.
     * Inicializa el RecyclerView con la lista de quads, configura el botón
     * para crear un nuevo quad y observa los cambios en la lista de quads.
     *
     * @param savedInstanceState Estado previo de la actividad (si existe)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quads);

        // Configuración del botón para crear un nuevo quad
        Button btnCrear = findViewById(R.id.btnCrearQuad);
        btnCrear.setOnClickListener(v -> {
            Intent i = new Intent(QuadsActivity.this, CrearQuadsActivity.class);
            i.putExtra("modo", "crear");
            startActivity(i);
        });

        // Configuración del RecyclerView
        RecyclerView recycler = findViewById(R.id.recyclerQuads);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        QuadAdapter adapter = new QuadAdapter(this);
        recycler.setAdapter(adapter);

        // Inicialización del ViewModel y observación de cambios en los quads
        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);
        quadViewModel.getAllQuads().observe(this, adapter::setQuads);
    }

    /**
     * Acción a realizar cuando se solicita modificar un quad.
     * Inicia la actividad {@link ModificarQuadsActivity} pasando el id del quad a editar.
     *
     * @param quad Quad que se desea modificar
     */
    @Override
    public void onModificar(Quad quad) {
        Intent i = new Intent(QuadsActivity.this, ModificarQuadsActivity.class);
        i.putExtra("modo", "editar");
        i.putExtra("id", quad.getId());
        startActivity(i);
    }

    /**
     * Acción a realizar cuando se solicita eliminar un quad.
     * Llama al ViewModel para borrar el quad de la base de datos.
     *
     * @param quad Quad que se desea eliminar
     */
    @Override
    public void onEliminar(Quad quad) {
        quadViewModel.delete(quad);
    }
}
