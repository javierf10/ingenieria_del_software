package es.unizar.eina.M117_quads.ui.quads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Quad;

public class QuadAdapter extends RecyclerView.Adapter<QuadAdapter.QuadViewHolder> {

    private List<Quad> quads;
    private final OnQuadClickListener listener;

    public interface OnQuadClickListener {
        void onModificar(Quad quad);
        void onEliminar(Quad quad);
    }

    public QuadAdapter(OnQuadClickListener listener) {
        this.listener = listener;
    }

    public void setQuads(List<Quad> lista) {
        this.quads = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quad, parent, false);
        return new QuadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
        Quad q = quads.get(position);

        holder.txtMatricula.setText("Matrícula: " + q.getMatricula());
        holder.txtTipo.setText("Tipo: " + q.getTipo());
        holder.txtPrecio.setText("Precio: " + q.getPrecio() + "€");

        holder.btnModificar.setOnClickListener(v -> listener.onModificar(q));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(q));
    }

    @Override
    public int getItemCount() {
        return (quads == null) ? 0 : quads.size();
    }

    static class QuadViewHolder extends RecyclerView.ViewHolder {

        TextView txtMatricula, txtTipo, txtPrecio;
        Button btnModificar, btnEliminar;

        public QuadViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMatricula = itemView.findViewById(R.id.lblMatricula);
            txtTipo = itemView.findViewById(R.id.lblTipo);
            txtPrecio = itemView.findViewById(R.id.lblPrecio);
            btnModificar = itemView.findViewById(R.id.btnModificarQuad);
            btnEliminar = itemView.findViewById(R.id.btnEliminarQuad);
        }
    }
}
