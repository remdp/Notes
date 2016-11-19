package com.example.notes.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.db.NotesContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.action_share: {
                share();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void share() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, prepareNoteForSharing());
        shareIntent.setType(SHARE_TYPE);
        startActivity(shareIntent);
    }

    private String prepareNoteForSharing() {
        return getString(
                R.string.sharing_template,
                noteTitle.getText(),
                noteText.getText());
    }


    @OnClick(R.id.saveBtn)
    public void onSaveBtnClick() {
        insertNote();
        finish();
    }

    private void insertNote() {
        ContentValues values = new ContentValues();
        values.put(NotesContract.TITLE_COLUMN, noteTitle.getText().toString());
        values.put(NotesContract.TEXT_COLUMN, noteText.getText().toString());
        values.put(NotesContract.TIME_COLUMN, String.valueOf(System.currentTimeMillis()));
        getContentResolver().insert(NotesContract.CONTENT_URI, values);
    }
}
