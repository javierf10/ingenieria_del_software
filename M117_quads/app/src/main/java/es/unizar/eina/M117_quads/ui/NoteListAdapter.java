package es.unizar.eina.M117_quads.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import es.unizar.eina.M117_quads.database.Quad;

public class NoteListAdapter extends ListAdapter<Quad, NoteViewHolder> {
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public NoteListAdapter(@NonNull DiffUtil.ItemCallback<Quad> diffCallback) {
        super(diffCallback);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NoteViewHolder.create(parent);
    }

    public Quad getCurrent() {
        return getItem(getPosition());
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {

        Quad current = getItem(position);
        holder.bind(current.getTitle());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });
    }


    static class NoteDiff extends DiffUtil.ItemCallback<Quad> {

        @Override
        public boolean areItemsTheSame(@NonNull Quad oldItem, @NonNull Quad newItem) {
            //android.util.Log.d ( "NoteDiff" , "areItemsTheSame " + oldItem.getId() + " vs " + newItem.getId() + " " +  (oldItem.getId() == newItem.getId()));
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Quad oldItem, @NonNull Quad newItem) {
            //android.util.Log.d ( "NoteDiff" , "areContentsTheSame " + oldItem.getTitle() + " vs " + newItem.getTitle() + " " + oldItem.getTitle().equals(newItem.getTitle()));
            // We are just worried about differences in visual representation, i.e. changes in the title
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
