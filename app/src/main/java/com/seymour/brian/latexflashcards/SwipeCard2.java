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
 * Created by Brian on 11/27/2015.
 */
public class SwipeCard2 extends AppCompatActivity {
    //WebView w;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int startFrom = intent.getIntExtra("StartFrom",0);
        setContentView(R.layout.swipe_card2);
        mCustomPagerAdapter = new CustomPagerAdapter(this.getSupportFragmentManager(), this, false,startFrom);
        setTitle("SwipeCard");
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(startFrom);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.swipecard, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete:
                int i = mViewPager.getCurrentItem();
                SavedLatexCode s = new SavedLatexCode(this);
                s.deleteEQ(i);
                Toast.makeText(this, "You have successfully deleted this equation",Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                intent.putExtra("StartFrom", i);
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
