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

/**
 * Adaptador para un RecyclerView que muestra una lista de quads.
 * <p>
 * Proporciona la funcionalidad para mostrar cada quad con sus datos
 * y permite acciones de modificar o eliminar a través de botones.
 */
public class QuadAdapter extends RecyclerView.Adapter<QuadAdapter.QuadViewHolder> {

    /** Lista de quads que se mostrarán en el RecyclerView. */
    private List<Quad> quads;

    /** Listener que gestiona los clics sobre los botones de cada item. */
    private final OnQuadClickListener listener;

    /**
     * Interfaz que define las acciones posibles sobre un quad en el RecyclerView.
     */
    public interface OnQuadClickListener {
        /**
         * Acción al pulsar el botón de modificar un quad.
         * @param quad Quad que se desea modificar
         */
        void onModificar(Quad quad);

        /**
         * Acción al pulsar el botón de eliminar un quad.
         * @param quad Quad que se desea eliminar
         */
        void onEliminar(Quad quad);
    }

    /**
     * Constructor del adaptador.
     * @param listener Listener que gestiona las acciones sobre los quads.
     */
    public QuadAdapter(OnQuadClickListener listener) {
        this.listener = listener;
    }

    /**
     * Establece la lista de quads que se van a mostrar.
     * @param lista Lista de quads
     */
    public void setQuads(List<Quad> lista) {
        this.quads = lista;
        notifyDataSetChanged();
    }

    /**
     * Crea un ViewHolder para un item del RecyclerView.
     * @param parent Vista padre
     * @param viewType Tipo de vista
     * @return QuadViewHolder con la vista inflada
     */
    @NonNull
    @Override
    public QuadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quad, parent, false);
        return new QuadViewHolder(v);
    }

    /**
     * Vincula los datos de un quad al ViewHolder correspondiente.
     * @param holder ViewHolder donde se mostrarán los datos
     * @param position Posición del item en la lista
     */
    @Override
    public void onBindViewHolder(@NonNull QuadViewHolder holder, int position) {
        Quad q = quads.get(position);

        holder.txtMatricula.setText(q.getMatricula());
        holder.txtTipo.setText(String.valueOf(q.getTipo()));
        String precio = q.getPrecio() + "€";
        holder.txtPrecio.setText(precio);

        holder.btnModificar.setOnClickListener(v -> listener.onModificar(q));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(q));
    }

    /**
     * Devuelve el número de items en la lista de quads.
     * @return Número de quads, 0 si la lista es nula
     */
    @Override
    public int getItemCount() {
        return (quads == null) ? 0 : quads.size();
    }

    /**
     * ViewHolder para un item del RecyclerView que muestra los datos de un quad.
     */
    static class QuadViewHolder extends RecyclerView.ViewHolder {

        /** TextView que muestra la matrícula del quad. */
        TextView txtMatricula;
        /** TextView que muestra el tipo del quad. */
        TextView txtTipo;
        /** TextView que muestra el precio del quad. */
        TextView txtPrecio;
        /** Botón para modificar el quad. */
        Button btnModificar;
        /** Botón para eliminar el quad. */
        Button btnEliminar;

        /**
         * Constructor del ViewHolder.
         * @param itemView Vista del item
         */
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
