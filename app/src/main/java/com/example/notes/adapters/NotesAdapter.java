package com.example.notes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.R;

import java.util.List;

/**
 * Created by maltsev on 02.11.2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<String> dataSource = null;

    public void setDataSource(List<String> dataSource){
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_notes_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NotesViewHolder holder, int position) {
        String title = dataSource.get(position);
        holder.bindView(title);
    }

    @Override
    public int getItemCount() {
        if (dataSource == null){
            return 0;
        }else {
            return dataSource.size();
        }
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView = null;
        public NotesViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        }

        void bindView(String title){
            titleTextView.setText(title);
        }
    }

}
