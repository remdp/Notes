package com.example.notes.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.notes.R;
import com.example.notes.adapters.NotesAdapter;
import com.example.notes.model.Note;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.notes.Activities.EditNoteActivity.DATA_KEY;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.notes_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.fab)
    protected FloatingActionButton mFabButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    //private RecyclerView recyclerView = null;
    //private Toolbar toolbar = null;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        NotesAdapter notesAdapter = new NotesAdapter();
        List<Note> dataSource = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Note note = new Note();
            note.setTitle("title: " + i);
            note.setText("text: " + i);
            note.setTime(System.currentTimeMillis());
            dataSource.add(note);
        }

        recyclerView.setAdapter(notesAdapter);
        notesAdapter.setdataSource(dataSource);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra(DATA_KEY, EditNoteActivity.class.getSimpleName());
                startActivity(intent);
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Snackbar.make(recyclerView, "on touch listener", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

    }

}
