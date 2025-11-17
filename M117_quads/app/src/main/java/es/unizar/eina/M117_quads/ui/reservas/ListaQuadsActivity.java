package es.unizar.eina.M117_quads.ui.reservas;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;
import es.unizar.eina.M117_quads.ui.quads.QuadViewModel;

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
            mAdapter.setQuads(quads);
        });

        Button confirmButton = findViewById(R.id.button_confirm);
        confirmButton.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            ArrayList<Quad> selectedQuads = mAdapter.getSelectedQuads();
            replyIntent.putExtra(EXTRA_REPLY, selectedQuads);
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }

    class QuadSeleccionableAdapter extends RecyclerView.Adapter<QuadSeleccionableAdapter.QuadViewHolder> {

        private List<Quad> mQuads;
        private List<Quad> mSelectedQuads = new ArrayList<>();

        @NonNull
        @Override
        public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quad, parent, false);
            return new QuadViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
            if (mQuads != null) {
                Quad current = mQuads.get(position);
                holder.matricula.setText("Matrícula: " + current.getMatricula());
                holder.tipo.setText("Tipo: " + current.getTipo());
                holder.precio.setText("Precio: " + current.getPrecio() + " euros");

                holder.checkBox.setOnCheckedChangeListener(null);
                holder.checkBox.setChecked(mSelectedQuads.contains(current));
                holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        mSelectedQuads.add(current);
                    } else {
                        mSelectedQuads.remove(current);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mQuads != null)
                return mQuads.size();
            else return 0;
        }

        void setQuads(List<Quad> quads) {
            mQuads = quads;
            notifyDataSetChanged();
        }

        ArrayList<Quad> getSelectedQuads() {
            return new ArrayList<>(mSelectedQuads);
        }

        class QuadViewHolder extends RecyclerView.ViewHolder {
            private final CheckBox checkBox;
            private final TextView matricula;
            private final TextView tipo;
            private final TextView precio;

            private QuadViewHolder(View itemView) {
                super(itemView);
                checkBox = itemView.findViewById(R.id.checkbox_quad);
                matricula = itemView.findViewById(R.id.matricula_quad);
                tipo = itemView.findViewById(R.id.tipo_quad);
                precio = itemView.findViewById(R.id.precio_quad);
            }
        }
    }
}
