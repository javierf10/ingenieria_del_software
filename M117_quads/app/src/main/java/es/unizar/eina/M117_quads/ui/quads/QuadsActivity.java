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
import es.unizar.eina.M117_quads.R;

/** Pantalla utilizada para la creación o edición de una nota */

public class QuadsActivity extends AppCompatActivity implements QuadAdapter.OnQuadClickListener {

    private QuadViewModel quadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quads);

        Button btnCrear = findViewById(R.id.btnCrearQuad);
        btnCrear.setOnClickListener(v -> {
            Intent i = new Intent(QuadsActivity.this, CrearQuadsActivity.class);
            i.putExtra("modo", "crear");
            startActivity(i);
        });

        RecyclerView recycler = findViewById(R.id.recyclerQuads);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        QuadAdapter adapter = new QuadAdapter(this);
        recycler.setAdapter(adapter);

        quadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);

        quadViewModel.getAllQuads().observe(this, adapter::setQuads);
    }

    @Override
    public void onModificar(Quad quad) {
        Intent i = new Intent(QuadsActivity.this, ModificarQuadsActivity.class);
        i.putExtra("modo", "editar");
        i.putExtra("id", quad.getId());
        startActivity(i);
    }

    @Override
    public void onEliminar(Quad quad) {
        quadViewModel.delete(quad);
    }
}