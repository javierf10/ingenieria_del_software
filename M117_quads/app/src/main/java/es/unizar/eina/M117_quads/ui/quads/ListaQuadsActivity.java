package es.unizar.eina.M117_quads.ui.quads;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class ListaQuadsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_QUADS_SELECCIONADOS = "es.unizar.eina.M117_quads.ui.reservas.REPLY_QUADS_SELECCIONADOS";

    private QuadViewModel mQuadViewModel;
    private QuadSeleccionableAdapter mAdapter;
    private ArrayList<Integer> mQuadsYaSeleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quads);

        mQuadsYaSeleccionados = getIntent().getIntegerArrayListExtra("quads_ya_seleccionados");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_quads);
        mAdapter = new QuadSeleccionableAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);
        mQuadViewModel.getAllQuads().observe(this, quads -> {
            if (quads != null && mQuadsYaSeleccionados != null) {
                List<Quad> quadsDisponibles = quads.stream()
                        .filter(q -> !mQuadsYaSeleccionados.contains(q.getId()))
                        .collect(Collectors.toList());
                mAdapter.setQuads(quadsDisponibles);
            } else {
                mAdapter.setQuads(quads);
            }
        });

        Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            ArrayList<Quad> selectedQuads = mAdapter.getQuadsSeleccionados();
            ArrayList<Integer> selectedQuadsIds = new ArrayList<>();
            for (Quad quad : selectedQuads) {
                selectedQuadsIds.add(quad.getId());
            }
            // Devolvemos tanto los nuevos como los que ya estaban
            if (mQuadsYaSeleccionados != null) {
                selectedQuadsIds.addAll(mQuadsYaSeleccionados);
            }

            replyIntent.putIntegerArrayListExtra(EXTRA_REPLY_QUADS_SELECCIONADOS, selectedQuadsIds);
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }
}
