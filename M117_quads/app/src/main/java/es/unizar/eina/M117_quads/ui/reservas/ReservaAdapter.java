package es.unizar.eina.M117_quads.ui.reservas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import es.unizar.eina.M117_quads.R;
import es.unizar.eina.M117_quads.database.Reserva;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {

    private List<Reserva> reservas = Collections.emptyList();
    private final OnReservaClickListener listener;

    public interface OnReservaClickListener {
        void onModificar(Reserva reserva);
        void onEliminar(Reserva reserva);
    }

    public ReservaAdapter(OnReservaClickListener listener) {
        this.listener = listener;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = (reservas != null) ? reservas : Collections.emptyList();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reserva_item, parent, false);
        return new ReservaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva current = reservas.get(position);
        holder.bind(current, listener);
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    static class ReservaViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombre;
        private final TextView numero;
        private final TextView fechas;
        private final Button modificar;
        private final Button eliminar;

        private ReservaViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.reserva_item_nombre);
            numero = itemView.findViewById(R.id.reserva_item_numero);
            fechas = itemView.findViewById(R.id.reserva_item_fechas);
            modificar = itemView.findViewById(R.id.reserva_item_modificar);
            eliminar = itemView.findViewById(R.id.reserva_item_eliminar);
        }

        public void bind(Reserva reserva, OnReservaClickListener listener) {
            nombre.setText(reserva.getNombre());
            numero.setText(reserva.getNumeroTelef() != null ? reserva.getNumeroTelef() : "");

            String fechaRecogidaStr = "N/A";
            String fechaDevolucionStr = "N/A";

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            if (reserva.getFechaRecogida() != null) {
                fechaRecogidaStr = sdf.format(reserva.getFechaRecogida());
            }
            if (reserva.getFechaDevolucion() != null) {
                fechaDevolucionStr = sdf.format(reserva.getFechaDevolucion());
            }

            fechas.setText(itemView.getContext().getString(R.string.reserva_fechas_label, fechaRecogidaStr, fechaDevolucionStr));

            modificar.setOnClickListener(v -> listener.onModificar(reserva));
            eliminar.setOnClickListener(v -> listener.onEliminar(reserva));
        }
    }
}
