package com.seymour.brian.latexflashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.support.v4.app.FragmentManager;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Brian on 1/20/2016.
 */
public class SwipeCardRandom extends AppCompatActivity {


    /**
     * Created by Brian on 11/27/2015.
     */

    //WebView w;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_card2);
        mCustomPagerAdapter = new CustomPagerAdapter(this.getSupportFragmentManager(), this, true, 0);
        setTitle("SwipeCard");
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);


    }


}

