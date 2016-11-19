package com.example.notes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maltsev on 02.11.2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> mdataSource = null;

    public void setDataSource(List<Note> dataSource){
        this.mdataSource = dataSource;
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
        Note note = mdataSource.get(position);
        holder.bindView(note);
    }

    @Override
    public int getItemCount() {
        if (mdataSource == null){
            return 0;
        }else {
            return mdataSource.size();
        }
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.primary_text_view)
        protected TextView mPrimaryTextView;
        @BindView(R.id.secondary_text_view)
        protected TextView mSecondaryTextView;
        @BindView(R.id.date_text_view)
        protected TextView mDateTextView;

        public NotesViewHolder(View itemView) {
            super(itemView);
            //titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Note note){
           // titleTextView.setText(title);
            mPrimaryTextView.setText(note.getTitle());
            mSecondaryTextView.setText(note.getText());
            mDateTextView.setText(String.valueOf(note.getTime()));
        }
    }

}
