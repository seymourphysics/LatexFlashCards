package com.seymour.brian.latexflashcards;

import android.app.Activity;
import android.content.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Some of these methods were made by Tom Leathrum and posted on his blog on May 1, 2014.
 * Here is the url: http://cs.jsu.edu/wordpress/index.php/2014/05/01/mathjax-standalone-android-app-updated-links-with-kitkat-fix/
 * this is a class for getting strings from indices. It is used since later I will use user stored values, so I will only have to change here
 */
public class LatexStuff {
    private int currentIndex;
    private Context mcontext;
    private int lastIndex;
    private final String[] array;
    int length;


    public LatexStuff(Context context) {
        currentIndex = 0;
        lastIndex = 0;
        this.mcontext = context;
        length = mcontext.getResources().getStringArray(R.array.tex_examples).length;
        array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = doubleEscapeTeX(mcontext.getResources().getStringArray(R.array.tex_examples)[i]);
        }

    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public String[] getRandomizedArray() {
        Collections.shuffle(Arrays.asList(array));
        return array;
    }

    /**
     * this gets an array of all the examples
     * need to fix to make not randomized after called in randomized
     *
     * @return
     */
    public String[] getArray() {
        return array;
    }

    public String getRandomExample(Context context) {
        mcontext = context;
        return getExample(getRandomIndex());
    }

    public String getLast(Context context) {
        mcontext = context;
        return getExample(lastIndex);
    }

    /**
     * assumes that it is a working index, too lazy to fix now, but would cause crash
     *
     * @param i       is an int which is hte index
     * @param context is the application context
     * @return a string which is an example
     */
    public String getIndex(int i, Context context) {
        mcontext = context;
        return getExample(i);
    }

    public static int getExamplesSize(Context context) {
        return context.getResources().getStringArray(R.array.tex_examples).length;
    }

    /**
     * this class gets a string from the stored examples array
     *
     * @param //index of that array
     * @return the string of that which is already fixed for the double escape
     */
    private String getExample(int index) {
        return doubleEscapeTeX(mcontext.getResources().getStringArray(R.array.tex_examples)[index]);
    }

    /**
     * this class fixes the double escaped string
     *
     * @param //a string to be fixed
     * @return a string which has been fixed
     */
    private String doubleEscapeTeX(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\'') t += '\\';
            if (s.charAt(i) != '\n') t += s.charAt(i);
            if (s.charAt(i) == '\\') t += "\\";
        }
        return t;
    }

    /**
     * this is a helper method which gets a random index
     *
     * @return the next index
     */
    private int getRandomIndex() {
        Random random = new Random();
        lastIndex = currentIndex;
        do

        {
            currentIndex = random.nextInt(mcontext.getResources().getStringArray(R.array.tex_examples).length - 1);
        }

        while (currentIndex == lastIndex);

        return currentIndex;
    }


}
