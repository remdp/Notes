package com.example.notes.Activities;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.db.NotesContract;
import com.example.notes.model.Note;
import com.example.notes.util.DateUtil;
import com.tjeannin.provigen.ProviGenBaseContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditNoteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String SHARE_TYPE = "text/plain";
    public static final String RESULT = "RESULT";

    @BindView(R.id.contentEditText)
    protected TextView noteText;
    @BindView(R.id.titleEditText)
    protected TextView noteTitle;
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    private long mId = -1;

    private String mOriginalTitle = "";
    private String mOriginalText = "";

//    public static void start(Context context){
//        Intent intent = new Intent(context, EditNoteActivity.class);
//    }

    public static Intent newInstance(Context context){
        return new Intent(context, EditNoteActivity.class);
    }

    public static Intent newInstance(Context context, long id){
        Intent intent = newInstance(context);
        intent.putExtra(ProviGenBaseContract._ID,id);
        return intent;
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

    private void checkIntentByExtraId() {
        Intent intent = getIntent();
        if(!intent.hasExtra(ProviGenBaseContract._ID)) return;
        mId = intent.getLongExtra(ProviGenBaseContract._ID, mId);
        if(mId == -1) return;
        getLoaderManager().initLoader(R.id.edit_note_loader, null, this);
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
                case R.id.action_delete: {
                    deleteNote();
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {
        if(isNoteUpdatable()) {
            getContentResolver().delete(
                    Uri.withAppendedPath(NotesContract.CONTENT_URI, String.valueOf(mId)),
                    null,
                    null);
        }
        finish();
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                this,
                Uri.withAppendedPath(NotesContract.CONTENT_URI, String.valueOf(mId)),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) return;
        Note note = new Note(cursor);
        noteTitle.setText(note.getTitle());
        noteText.setText(note.getText());
        mOriginalTitle = note.getTitle();
        mOriginalText = note.getText();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onBackPressed() {
     //   safetyFinish(() -> EditNoteActivity.super.onBackPressed());
    }

    private void safetyFinish(Runnable finish) {
        if(mOriginalTitle.equals(noteTitle.getText().toString())
                && mOriginalText.equals(noteText.getText().toString())) {
            finish.run();
            return;
        }
      //  showDoYouSureAlert(finish);
    }

    private void save() {
        if(isNoteUpdatable()) {
            updateNote();
        } else {
            insertNote();
        }
    }

    private boolean isNoteUpdatable() {
        return mId != -1;
    }

//    private void showDoYouSureAlert(final Runnable finish) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.do_you_sure_alert_title);
//        builder.setMessage(R.string.do_yout_sure_alert_do_you_want_to_save_change);
//        builder.setCancelable(false);
//        builder.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
//            save();
//            finish.run();
//        });
//        builder.setNegativeButton(android.R.string.no, (dialogInterface, i) -> finish.run());
//        builder.show();
//    }

    private void updateNote() {
        final ContentValues values = new ContentValues();
        values.put(NotesContract.TITLE_COLUMN, noteTitle.getText().toString());
        values.put(NotesContract.TEXT_COLUMN, noteText.getText().toString());
        values.put(NotesContract.TIME_COLUMN, DateUtil.formatCurrentDate());
        getContentResolver().update(
                Uri.withAppendedPath(NotesContract.CONTENT_URI, String.valueOf(mId)),
                values,
                null,
                null);
    }

}
