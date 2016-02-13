package com.seymour.brian.latexflashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Brian on 11/28/2015.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
