package com.example.notes.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditNoteActivity extends AppCompatActivity {

    private static final String SHARE_TYPE = "text/plain";
    public static final String RESULT = "RESULT";

    @BindView(R.id.contentEditText)
    protected TextView noteText;
    @BindView(R.id.titleEditText)
    protected TextView noteTitle;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

//    public static void start(Context context){
//        Intent intent = new Intent(context, EditNoteActivity.class);
//    }

    public static Intent newInstance(Context context){
        return new Intent(context, EditNoteActivity.class);
    }

//    public static final String DATA_SET = "DATA_SET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        String extraString = getIntent().getStringExtra(DATA_SET);
//        Toast.makeText(this, extraString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.);
        return super.onCreateOptionsMenu(menu);
    }
}
