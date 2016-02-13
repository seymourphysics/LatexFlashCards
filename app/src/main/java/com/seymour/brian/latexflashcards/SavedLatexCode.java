package com.seymour.brian.latexflashcards;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Brian on 1/17/2016.
 * <p/>
 * This is a class which extends latex stuff for being able to use its methods, but it does not have all the features of seeing the defualt latex
 * It is what manages storing and saving of latex code
 */
public class SavedLatexCode extends LatexStuff {
    private Context mcontext;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    private ArrayList<String> latex;
    private ArrayList<String> notes;
    final public static String LATEX_KEY = "latexkey";
    final public static String NOTE_KEY = "notekey";
    public static final String PREFS_NAME = "BRIAN_APP";


    public SavedLatexCode(Context context) {
        super(context);
        this.mcontext = context;
        latex = new ArrayList<String>();
        notes = new ArrayList<String>();
        latex = getArrayListFromPrefs(LATEX_KEY);
        notes = getArrayListFromPrefs(NOTE_KEY);


    }

    public void storeArrayList(List list, String pref) {
// used for store arrayList in json format
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = mcontext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(list);
        editor.putString(pref, jsonFavorites);
        editor.commit();
    }

    /**
     * this method adds an ending value to both the latexcode array and the notestoadd array.
     *
     * @param latexCode  Latex code to add to the prefs
     * @param notesToAdd Notes to add to the prefs
     */
    public void addElement(String latexCode, String notesToAdd) {


        //SharedPreferences settings;
        //SharedPreferences.Editor editor;


        latex = getArrayListFromPrefs(LATEX_KEY);
        notes = getArrayListFromPrefs(NOTE_KEY);
        latex.add(latexCode);
        notes.add(notesToAdd);
        storeArrayList(latex, LATEX_KEY);
        storeArrayList(notes, NOTE_KEY);




        /*



        latex = getArrayFromPrefs(LATEX_KEY);
        notes = getArrayFromPrefs(NOTE_KEY);
        latex.add(latexCode);
        notes.add(notesToAdd);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mcontext);
        editor = sharedPrefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(this.latex);
        editor.putStringSet(LATEX_KEY, set);
        editor.commit();

        Set<String> set2 = new HashSet<String>();
        set2.addAll(this.notes);
        editor.putStringSet(NOTE_KEY, set2);
        editor.commit();*/
    }

    /**
     * this class gets an arraylist which is stored
     *
     * @param s key to pull from prefs, they are static final vars in this class
     * @return an arraylist from the prefs
     */
    public ArrayList<String> getArrayListFromPrefs(String s) {


        List stuffList;
        sharedPrefs = mcontext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(s)) {
            String jsonS = sharedPrefs.getString(s, null);
            Gson gson = new Gson();
            String[] stuffArray = gson.fromJson(jsonS, String[].class);
            stuffList = Arrays.asList(stuffArray);
            stuffList = new ArrayList(stuffList);
        } else
            return new ArrayList<String>();
        return (ArrayList) stuffList;


        /*sharedPrefs = PreferenceManager.getDefaultSharedPreferences(mcontext);
        Set<String> set = sharedPrefs.getStringSet(s, null);
        ArrayList<String> array = new ArrayList<String>(set);
        return array;*/
    }

    public void deleteEQ(int index) {
        latex = getArrayListFromPrefs(LATEX_KEY);
        notes = getArrayListFromPrefs(NOTE_KEY);
        latex.remove(index);
        notes.remove(index);
        storeArrayList(latex, LATEX_KEY);
        storeArrayList(notes, NOTE_KEY);
    }

    public int getSize() {

        return getArrayListFromPrefs(LATEX_KEY).size();
    }
}

