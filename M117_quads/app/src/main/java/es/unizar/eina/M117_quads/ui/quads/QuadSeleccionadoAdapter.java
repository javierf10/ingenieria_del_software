package es.unizar.eina.M117_quads.ui.quads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class QuadSeleccionadoAdapter extends RecyclerView.Adapter<QuadSeleccionadoAdapter.QuadViewHolder> {

    private List<Quad> quads = Collections.emptyList();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEliminarClick(Quad quad);
    }

    public QuadSeleccionadoAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setQuads(List<Quad> quads) {
        this.quads = (quads != null) ? quads : Collections.emptyList();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quad_seleccionado, parent, false);
        return new QuadViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
        Quad current = quads.get(position);
        holder.bind(current, listener);
    }

    @Override
    public int getItemCount() {
        return quads.size();
    }

    static class QuadViewHolder extends RecyclerView.ViewHolder {
        private final TextView matricula;
        private final TextView tipo;
        private final Button eliminarButton;

        private QuadViewHolder(View itemView) {
            super(itemView);
            matricula = itemView.findViewById(R.id.textview_matricula_seleccionado);
            tipo = itemView.findViewById(R.id.textview_tipo_seleccionado);
            eliminarButton = itemView.findViewById(R.id.button_eliminar_quad_seleccionado);
        }

        public void bind(Quad quad, OnItemClickListener listener) {
            matricula.setText(quad.getMatricula());
            tipo.setText(String.valueOf(quad.getTipo()));
            eliminarButton.setOnClickListener(v -> listener.onEliminarClick(quad));
        }
    }
}
