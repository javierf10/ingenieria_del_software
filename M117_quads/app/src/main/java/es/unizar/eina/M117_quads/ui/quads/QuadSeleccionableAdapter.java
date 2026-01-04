package es.unizar.eina.M117_quads.ui.quads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class QuadSeleccionableAdapter extends RecyclerView.Adapter<QuadSeleccionableAdapter.QuadViewHolder> {

    private List<Quad> quads = Collections.emptyList();
    private final List<Quad> quadsSeleccionados = new ArrayList<>();

    public QuadSeleccionableAdapter() {
        // Constructor
    }

    public void setQuads(List<Quad> quads) {
        this.quads = (quads != null) ? quads : Collections.emptyList();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quad_seleccionable, parent, false);
        return new QuadViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
        Quad current = quads.get(position);
        holder.bind(current);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!quadsSeleccionados.contains(current)) {
                    quadsSeleccionados.add(current);
                }
            } else {
                quadsSeleccionados.remove(current);
            }
        });

        holder.checkBox.setChecked(quadsSeleccionados.contains(current));
    }

    @Override
    public int getItemCount() {
        return quads.size();
    }

    public ArrayList<Quad> getQuadsSeleccionados() {
        return new ArrayList<>(quadsSeleccionados);
    }

    static class QuadViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final TextView matricula;
        private final TextView tipo;
        private final TextView precio;

        private QuadViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_quad_seleccionable);
            matricula = itemView.findViewById(R.id.matricula_quad_seleccionable);
            tipo = itemView.findViewById(R.id.tipo_quad_seleccionable);
            precio = itemView.findViewById(R.id.precio_quad_seleccionable);
        }

        public void bind(Quad quad) {
            matricula.setText("Matrícula: " + quad.getMatricula());
            tipo.setText("Tipo: " + quad.getTipo());
            precio.setText("Precio: " + quad.getPrecio() + "€");
        }
    }
}
