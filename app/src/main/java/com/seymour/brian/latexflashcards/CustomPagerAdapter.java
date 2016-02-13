package com.seymour.brian.latexflashcards;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created by Brian on 11/27/2015.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    protected Context mContext;
    SavedLatexCode slc;
    ArrayList<String> code;
    ArrayList<String> notes;
    private int pos;

    public CustomPagerAdapter(FragmentManager fm, Context context, boolean random, int startFrom) {
        super(fm);
        mContext = context;
        slc = new SavedLatexCode(mContext);
        code = slc.getArrayListFromPrefs(slc.LATEX_KEY);
        notes = slc.getArrayListFromPrefs(slc.NOTE_KEY);
        if (random) {
            long seed = System.nanoTime();
            Collections.shuffle(code, new Random(seed));
            Collections.shuffle(notes, new Random(seed));
        }


    }

    @Override
    // This method returns the fragment associated with
    // the specified position.
    //
    // It is called when the Adapter needs a fragment
    // and it does not exists.
    public Fragment getItem(int position) {

        // Create fragment object
        Fragment fragment = new DemoFragment();

        // Attach some data to it that we'll
        // use to populate our fragment layouts
        Bundle args = new Bundle();
        args.putInt("page_position", position + 1);
        args.putString("latex", code.get(position));
        args.putString("notes", notes.get(position));

        // Set the arguments on the fragment
        // that will be fetched in DemoFragment@onCreateView
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public int getCount() {
        return slc.getSize();
    }


}