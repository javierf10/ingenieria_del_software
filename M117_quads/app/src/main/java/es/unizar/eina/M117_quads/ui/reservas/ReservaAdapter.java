//package es.unizar.eina.M117_quads.ui.reservas;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.text.SimpleDateFormat;
//import java.util.List;
//import java.util.Locale;
//
//import es.unizar.eina.M117_quads.R;
//import es.unizar.eina.M117_quads.database.Reserva;
//
///**
// * Adaptador para un RecyclerView que muestra una lista de reservas.
// * <p>
// * Proporciona la funcionalidad para mostrar cada reserva con sus datos
// * y permite acciones de modificar o eliminar a través de botones.
// */
//public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {
//
//    /** Lista de reservas que se mostrarán en el RecyclerView. */
//    private List<Reserva> reservas;
//
//    /** Listener que gestiona los clics sobre los botones de cada item. */
//    private final OnReservaClickListener listener;
//
//    /**
//     * Interfaz que define las acciones posibles sobre un reserva en el RecyclerView.
//     */
//    public interface OnReservaClickListener {
//        /**
//         * Acción al pulsar el botón de modificar un reserva.
//         * @param reserva Reserva que se desea modificar
//         */
//        void onModificar(Reserva reserva);
//
//        /**
//         * Acción al pulsar el botón de eliminar un reserva.
//         * @param reserva Reserva que se desea eliminar
//         */
//        void onEliminar(Reserva reserva);
//    }
//
//    /**
//     * Constructor del adaptador.
//     * @param listener Listener que gestiona las acciones sobre los reservas.
//     */
//    public ReservaAdapter(OnReservaClickListener listener) {
//        this.listener = listener;
//    }
//
//    /**
//     * Establece la lista de reservas que se van a mostrar.
//     * @param lista Lista de reservas
//     */
//    public void setReservas(List<Reserva> lista) {
//        this.reservas = lista;
//        notifyDataSetChanged();
//    }
//
//    /**
//     * Crea un ViewHolder para un item del RecyclerView.
//     * @param parent Vista padre
//     * @param viewType Tipo de vista
//     * @return ReservaViewHolder con la vista inflada
//     */
//    @NonNull
//    @Override
//    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.reserva, parent, false);
//        return new ReservaViewHolder(v);
//    }
//
//    /**
//     * Vincula los datos de un reserva al ViewHolder correspondiente.
//     * @param holder ViewHolder donde se mostrarán los datos
//     * @param position Posición del item en la lista
//     */
//    @Override
//    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
//        Reserva current = reservas.get(position);
//
//        holder.txtNombre.setText(current.getNombre());
//        holder.txtNumeroTelef.setText(current.getNumeroTelef());
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String fechaRecogida = sdf.format(current.getFechaRecogida());
//        String fechaDevolucion = sdf.format(current.getFechaDevolucion());
//        String fechas = "Recogida: " + fechaRecogida + "\nDevolución: " + fechaDevolucion;
//        holder.txtFechas.setText(fechas);
//
//        holder.btnModificar.setOnClickListener(v -> listener.onModificar(current));
//        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(current));
//    }
//
//    /**
//     * Devuelve el número de items en la lista de reservas.
//     * @return Número de reservas, 0 si la lista es nula
//     */
//    @Override
//    public int getItemCount() {
//        return (reservas == null) ? 0 : reservas.size();
//    }
//
//    /**
//     * ViewHolder para un item del RecyclerView que muestra los datos de un reserva.
//     */
//    static class ReservaViewHolder extends RecyclerView.ViewHolder {
//
//        /** TextView que muestra el nombre del cliente de la reserva. */
//        TextView txtNombre;
//        /** TextView que muestra el número de teléfono de la reserva. */
//        TextView txtNumeroTelef;
//        /** TextView que muestra las fechas de la reserva. */
//        TextView txtFechas;
//        /** Botón para modificar la reserva. */
//        Button btnModificar;
//        /** Botón para eliminar la reserva. */
//        Button btnEliminar;
//
//        /**
//         * Constructor del ViewHolder.
//         * @param itemView Vista del item
//         */
//        public ReservaViewHolder(@NonNull View itemView) {
//            super(itemView);
//            txtNombre = itemView.findViewById(R.id.textView);
//            txtNumeroTelef = itemView.findViewById(R.id.textView2);
//            txtFechas = itemView.findViewById(R.id.textView3);
//            btnModificar = itemView.findViewById(R.id.btnModificar);
//            btnEliminar = itemView.findViewById(R.id.btnEliminar);
//        }
//    }
//}
//