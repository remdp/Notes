package com.example.notes.fragments;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.db.NotesContract;
import com.example.notes.model.Note;
import com.tjeannin.provigen.ProviGenBaseContract;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by java on 23.11.2016.
 */

public class NoteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.titleEditText)
    protected EditText mTitleEditText;
    @BindView(R.id.contentEditText)
    protected EditText mContentEditText;

    public static NoteFragment newInstance(long id) {

        Bundle args = new Bundle();

        args.putLong(ProviGenBaseContract._ID, id);
        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(
                R.layout.activity_fragment_note,
                container,
                false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        long noteId = getArguments().getLong(ProviGenBaseContract._ID);
        return new CursorLoader(
                getActivity(),
                Uri.withAppendedPath(NotesContract.CONTENT_URI, String.valueOf(noteId)),
                null,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) return;
        Note note = new Note(cursor);
        mTitleEditText.setText(note.getTitle());
        mContentEditText.setText(note.getText());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
