
/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.loosepixel.scorecounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/*
6 colors
red: 2
yellow: 3
green: 4
blue: 8
white: 16
black 25
 */


public class Main extends FragmentActivity {


    //Constants
    private static final int RED_MULTIPLIER = 2;
    private static final int YELLOW_MULTIPLIER = 3;
    private static final int GREEN_MULTIPLIER = 4;
    private static final int BLUE_MULTIPLIER = 8;
    private static final int WHITE_MULTIPLIER = 16;
    private static final int BLACK_MULTIPLIER = 24;

    //Database Helper
    HistoryAdapter historyDatabaseAdapter;

    SharedPreferences sharedPref;
    ViewPager viewPager = null;
    MyAdapter myFragmentPageStateAdapter = null;

    // weight = (totalPoints - 3) * 50
    private int totalPoints, numberTotal, weightTotal = 0;
    //===============================================================
    //color number totals
    //===============================================================
    private int red, yellow, green, blue, white, black = 0;
    //Color point totals
    private int redT, yellowT, greenT, blueT, whiteT, blackT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        myFragmentPageStateAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPageStateAdapter);

        //Initialize Database Object
        historyDatabaseAdapter = HistoryAdapter.getInstance(this);//new HistoryAdapter(this);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    }


    public Cursor getCursor() {
        Toast.makeText(this, "Too SLOW!", Toast.LENGTH_SHORT);
        return historyDatabaseAdapter.getDataCursor();
    }

    @Override
    public void onStart() {
        super.onStart();
        // historyDatabaseAdapter.open();
    }

    @Override
    public void onStop() {
        super.onStop();
        //historyDatabaseAdapter.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_about) {

            startActivity(new Intent(this, About.class));
            return true;
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    //Sets all values to 0 and updates the view
    public void reset(View v) {

        red = 0;
        yellow = 0;
        green = 0;
        blue = 0;
        white = 0;
        black = 0;

        redT = 0;
        yellowT = 0;
        greenT = 0;
        blueT = 0;
        whiteT = 0;
        blackT = 0;

        totalPoints = 0;
        numberTotal = 0;
        weightTotal = 0;

        updateText();
        vibrate();
    }

    //calculates totals based on numbers then updates the view
    public void updateTotals() {
        redT = red * RED_MULTIPLIER;
        yellowT = yellow * YELLOW_MULTIPLIER;
        greenT = green * GREEN_MULTIPLIER;
        blueT = blue * BLUE_MULTIPLIER;
        whiteT = white * WHITE_MULTIPLIER;
        blackT = black * BLACK_MULTIPLIER;

        numberTotal = red + yellow + green + blue + white + black;
        totalPoints = redT + yellowT + greenT + blueT + whiteT + blackT;

        // weight = (totalPoints - 3) * 50
        weightTotal = (totalPoints - numberTotal) * 50;

        updateText();
    }

    public void vibrate() {

        if (sharedPref.getBoolean("pref_vibrate", true)) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            //Vibrate for 500 milliseconds
            v.vibrate(50);
        }

    }


    //I didn't remember how to do this in an array so I just typed everything out
    // I learned at one point but have since forgotten
    public void updateText() {
        //Initialize the number TextViews
        TextView redNumber = (TextView) findViewById(R.id.redNumberTextView);
        TextView yellowNumber = (TextView) findViewById(R.id.yellowNumberTextView);
        TextView greenNumber = (TextView) findViewById(R.id.greenNumberTextView);
        TextView blueNumber = (TextView) findViewById(R.id.blueNumberTextView);
        TextView whiteNumber = (TextView) findViewById(R.id.whiteNumberTextView);
        TextView blackNumber = (TextView) findViewById(R.id.blackNumberTextView);

        TextView redTotal = (TextView) findViewById(R.id.redTotalTextView);
        TextView yellowTotal = (TextView) findViewById(R.id.yellowTotalTextView);
        TextView greenTotal = (TextView) findViewById(R.id.greenTotalTextView);
        TextView blueTotal = (TextView) findViewById(R.id.blueTotalTextView);
        TextView whiteTotal = (TextView) findViewById(R.id.whiteTotalTextView);
        TextView blackTotal = (TextView) findViewById(R.id.blackTotalTextView);

        redNumber.setText(String.valueOf(red));
        yellowNumber.setText(String.valueOf(yellow));
        greenNumber.setText(String.valueOf(green));
        blueNumber.setText(String.valueOf(blue));
        whiteNumber.setText(String.valueOf(white));
        blackNumber.setText(String.valueOf(black));

        redTotal.setText(String.valueOf(redT));
        yellowTotal.setText(String.valueOf(yellowT));
        greenTotal.setText(String.valueOf(greenT));
        blueTotal.setText(String.valueOf(blueT));
        whiteTotal.setText(String.valueOf(whiteT));
        blackTotal.setText(String.valueOf(blackT));


        TextView numberTotalTextView = (TextView) findViewById(R.id.numberTotalTextView);
        numberTotalTextView.setText(String.valueOf(numberTotal));

        TextView weightTotalTextView = (TextView) findViewById(R.id.weightTotalTextView);
        weightTotalTextView.setText(String.valueOf(weightTotal));

        TextView totalPointsTextView = (TextView) findViewById(R.id.totalPointsTextView);
        totalPointsTextView.setText(String.valueOf(totalPoints));
    }

    /* I know there are numerous more efficient ways to do this but
    I didn't feel like spending too much time with more complex ways */

    public void addRed(View v) {
        red++;
        updateTotals();
        vibrate();
    }

    public void subtractRed(View v) {
        if (red > 0) {
            red--;
        }
        updateTotals();
        vibrate();
    }

    public void addYellow(View v) {
        yellow++;
        updateTotals();
        vibrate();
    }

    public void subtractYellow(View v) {
        if (yellow > 0) {
            yellow--;
            updateTotals();
            vibrate();
        }
    }

    public void addGreen(View v) {
        green++;
        updateTotals();
        vibrate();
    }

    public void subtractGreen(View v) {
        if (green > 0) {
            green--;
        }
        updateTotals();
        vibrate();
    }

    public void addBlue(View v) {
        blue++;
        updateTotals();
        vibrate();
    }

    public void subtractBlue(View v) {
        if (blue > 0) {
            blue--;
        }
        updateTotals();
        vibrate();
    }

    public void addWhite(View v) {
        white++;
        updateTotals();
        vibrate();
    }

    public void subtractWhite(View v) {
        if (white > 0) {
            white--;
        }
        updateTotals();
        vibrate();
    }

    public void addBlack(View v) {
        black++;
        updateTotals();
        vibrate();
    }

    public void subtractBlack(View v) {
        if (black > 0) {
            black--;
        }
        updateTotals();
        vibrate();
    }

    public void save(View v) {
        Calendar c = Calendar.getInstance();
        String d = c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            Log.e("Parse Ex.", "Failed to parse date");
        }
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(this);
        String s = dateFormat.format(date);

        //long id = historyDatabaseAdapter.insert(s, red, yellow, green, blue, white, black);

        long id = historyDatabaseAdapter.insert(s, numberTotal, weightTotal, totalPoints);
        Toast.makeText(this, getString(R.string.saving_database) + " #" + id, Toast.LENGTH_SHORT).show();
        vibrate();


        Fragment_History fh = (Fragment_History) getSupportFragmentManager().getFragments().get(1);
        fh.updateListView();
    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            if (i == 0) {
                fragment = new Fragment_Main();
            } else if (i == 1) {
                fragment = new Fragment_History();
            }

            return fragment;

        }

        @Override
        public int getCount() {
            //This shouldn't be hard coded in, but I'm not really sure how to NOT hard code it in at the moment
            return 2;
        }

    }

}
