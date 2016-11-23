package com.example.notes.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.notes.fragments.note_fragment;
import com.example.notes.model.Note;

import java.util.List;

/**
 * Created by java on 23.11.2016.
 */

public class NotesFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Note> mDataSource = null;
    public NotesFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        long id
        return new note_fragment();
    }

    @Override
    public int getCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    public void setDataSource(List<Note> dataSource) {
        mDataSource = dataSource;
        notifyDataSetChanged();
    }

}