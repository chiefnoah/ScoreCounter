package net.loosepixel.scorecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


/*
6 colors
red: 2
yellow: 3
green: 4
blue: 8
white: 16
black 25
 */


public class Activity_Main extends FragmentActivity {

    //Constants
    private static final int RED_MULTIPLIER = 2;
    private static final int YELLOW_MULTIPLIER = 3;
    private static final int GREEN_MULTIPLIER = 4;
    private static final int BLUE_MULTIPLIER = 8;
    private static final int WHITE_MULTIPLIER = 16;
    private static final int BLACK_MULTIPLIER = 24;
    ViewPager viewPager = null;
    // weight = (totalPoints - 3) * 50
    private int totalPoints, numberTotal, weightTotal = 0;
    //===============================================================
    //color number totals
    //When the "save" button is clicked, these are the values saved.
    //===============================================================
    private int red, yellow, green, blue, white, black = 0;
    //Color point totals
    private int redT, yellowT, greenT, blueT, whiteT, blackT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

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

            startActivity(new Intent(this, Activity_About.class));
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
    }

    public void subtractRed(View v) {
        if (red > 0) {
            red--;
        }
        updateTotals();
    }

    public void addYellow(View v) {
        yellow++;
        updateTotals();
    }

    public void subtractYellow(View v) {
        if (yellow > 0) {
            yellow--;
            updateTotals();
        }
    }

    public void addGreen(View v) {
        green++;
        updateTotals();
    }

    public void subtractGreen(View v) {
        if (green > 0) {
            green--;
        }
        updateTotals();
    }

    public void addBlue(View v) {
        blue++;
        updateTotals();
    }

    public void subtractBlue(View v) {
        if (blue > 0) {
            blue--;
        }
        updateTotals();
    }

    public void addWhite(View v) {
        white++;
        updateTotals();
    }

    public void subtractWhite(View v) {
        if (white > 0) {
            white--;
        }
        updateTotals();
    }

    public void addBlack(View v) {
        black++;
        updateTotals();
    }

    public void subtractBlack(View v) {
        if (black > 0) {
            black--;
        }
        updateTotals();
    }

    public class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            if(i==0) {
                fragment = new Fragment_Main();
            }
            else if(i==1) {
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
