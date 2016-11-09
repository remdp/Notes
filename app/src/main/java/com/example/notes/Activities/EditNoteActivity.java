package com.example.notes.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notes.R;

public class EditNoteActivity extends AppCompatActivity {

    public static void start(Context context){
        Intent intent = new Intent(context, EditNoteActivity.class);
    }

    public static Intent newInstance(Context context){
        return new Intent(context, EditNoteActivity.class);
    }

    public static final String DATA_KEY = "DATA_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        String extraString = getIntent().getStringExtra(DATA_KEY);
        Toast.makeText(this, extraString, Toast.LENGTH_SHORT).show();
    }
}
