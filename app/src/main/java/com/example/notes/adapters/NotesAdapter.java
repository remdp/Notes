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

    private List<Note> mDataSource = null;
    private View.OnClickListener mOnItemClickListener = null;

    public View.OnClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void setDataSource(List<Note> dataSource){
        this.mDataSource = dataSource;
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
        Note note = mDataSource.get(position);
        holder.bindView(note);
    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder{

        private Note mNote;

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
            mNote = note;
            mPrimaryTextView.setText(note.getTitle());
            mSecondaryTextView.setText(note.getText());
            mDateTextView.setText(String.valueOf(note.getTime()));
        }

        public Note getNote() {
            return mNote;
        }

    }
}
