package es.unizar.eina.M117_quads.ui.quads;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class ListaQuadsActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "es.unizar.eina.M117_quads.ui.reservas.REPLY";

    private QuadViewModel mQuadViewModel;
    private QuadSeleccionableAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_quads);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_quads);
        mAdapter = new QuadSeleccionableAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuadViewModel = new ViewModelProvider(this).get(QuadViewModel.class);
        mQuadViewModel.getAllQuads().observe(this, quads -> {
            mAdapter.submitList(quads);
        });

        Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            ArrayList<Quad> selectedQuads = mAdapter.getSelectedQuads();
            //replyIntent.putParcelableArrayListExtra(EXTRA_REPLY, selectedQuads);
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }

    class QuadSeleccionableAdapter extends ListAdapter<Quad, QuadSeleccionableAdapter.QuadViewHolder> {

        private final List<Quad> mSelectedQuads = new ArrayList<>();

        QuadSeleccionableAdapter() {
            super(DIFF_CALLBACK);
        }

        @NonNull
        @Override
        public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quad_seleccionable, parent, false);
            return new QuadViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
            Quad current = getItem(position);
            holder.matricula.setText("Matrícula: " + current.getMatricula());
            holder.tipo.setText("Tipo: " + current.getTipo());

            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setChecked(mSelectedQuads.contains(current));
            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (!mSelectedQuads.contains(current)) {
                        mSelectedQuads.add(current);
                    }
                } else {
                    mSelectedQuads.remove(current);
                }
            });
        }

        ArrayList<Quad> getSelectedQuads() {
            return new ArrayList<>(mSelectedQuads);
        }

        class QuadViewHolder extends RecyclerView.ViewHolder {
            private final CheckBox checkBox;
            private final TextView matricula;
            private final TextView tipo;

            private QuadViewHolder(View itemView) {
                super(itemView);
                checkBox = itemView.findViewById(R.id.checkbox_quad_seleccionable);
                matricula = itemView.findViewById(R.id.matricula_quad_seleccionable);
                tipo = itemView.findViewById(R.id.tipo_quad_seleccionable);
            }
        }
    }

    private static final DiffUtil.ItemCallback<Quad> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Quad>() {
                @Override
                public boolean areItemsTheSame(@NonNull Quad oldItem, @NonNull Quad newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Quad oldItem, @NonNull Quad newItem) {
                    // Compara todos los campos que afectan a la UI
                    return oldItem.getMatricula().equals(newItem.getMatricula()) &&
                            Objects.equals(oldItem.getTipo(), newItem.getTipo()) &&
                            oldItem.getPrecio() == newItem.getPrecio() &&
                            Objects.equals(oldItem.getDescripcion(), newItem.getDescripcion());
                }
            };
}
