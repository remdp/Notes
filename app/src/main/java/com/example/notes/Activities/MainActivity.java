package com.example.notes.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.notes.R;
import com.example.notes.adapters.NotesAdapter;
import com.example.notes.adapters.NotesAdapter.NotesViewHolder;
import com.example.notes.db.NotesContract;
import com.example.notes.model.Note;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import static com.example.notes.Activities.EditNoteActivity.DATA_SET;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {

    @BindView(R.id.notes_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
//    @BindView(R.id.fab)
//    protected FloatingActionButton mFabButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //private RecyclerView recyclerView = null;
    //private Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        //recyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        getSupportLoaderManager().initLoader(R.id.notes_loader, null, this);

        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put(NotesContract.TEXT_COLUMN, "fddfdf" + i);
            getContentResolver().insert(NotesContract.CONTENT_URI, values);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @OnClick(R.id.fab)
         public void onFabBtnClick() {
                 startActivity(EditNoteActivity.newInstance(this));
             }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Snackbar.make(recyclerView, R.string.action_settings, Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                Snackbar.make(recyclerView, R.string.action_help, Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_options:
                Snackbar.make(recyclerView, R.string.action_options, Snackbar.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                NotesContract.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        List<Note> dataSource = new ArrayList<>();
        while (data.moveToNext()) {
            dataSource.add(new Note(data));
        }
        NotesAdapter adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setDataSource(dataSource);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View view) {
        NotesViewHolder holder = (NotesViewHolder) recyclerView.findContainingViewHolder(view);
        if (holder == null) return;
        startActivity(EditNoteActivity.newInstance(this, holder.getNote().getId()));
    }
}
