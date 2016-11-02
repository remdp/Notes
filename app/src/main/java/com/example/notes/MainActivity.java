package com.example.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.notes.adapters.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        NotesAdapter notesAdapter = new NotesAdapter();
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i<100; i++){
            dataSource.add("title: " + i);
        }

        recyclerView.setAdapter(notesAdapter);
        notesAdapter.setDataSource(dataSource);
    }
}
