package com.seymour.brian.latexflashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.seymour.brian.latexflashcards.OldJavaClasses.SwipeCard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonx;
    Button button2;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.listview);
        String[] uses = new String[]{"Create new cards",  "View notecards in order and delete", "Shuffled notecards", "Common formula examples"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.text_list, android.R.id.text1, uses);
        lv.setAdapter(adapter);
        //setListViewHeightBasedOnItems(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lv.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Position: " + itemPosition + " ListItem: " + itemValue, Toast.LENGTH_LONG).show();

                if (position == 0) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, ViewEquations.class);
                    startActivity(intent);
                }
               /*if (position == 1) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, SwipeCard.class);
                    startActivity(intent);
                }*/
                if (position == 1/*2*/) {
                    if (new SavedLatexCode(getApplicationContext()).getSize() != 0) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, SwipeCard2.class);
                        int i = 0;
                        intent.putExtra("StartFrom", i);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "You have not added any equations yet \n Please add them first", Toast.LENGTH_SHORT).show();
                    }
                }
                if (position == 2) {
                    if (new SavedLatexCode(getApplicationContext()).getSize() != 0) {
                        Intent intent;
                        intent = new Intent(MainActivity.this, SwipeCardRandom.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "You have not added any equations yet \n Please add them first", Toast.LENGTH_SHORT).show();
                    }
                }
                if (position== 3){
                    Toast.makeText(getApplicationContext(),"This feature is soon to be added",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(buttonx)) {
            Intent intent = new Intent(this, ViewEquations.class);
            startActivity(intent);
        }
        if (v.equals(button2)) {
            Intent i = new Intent(this, SwipeCard.class);
            startActivity(i);
        }
    }
}
