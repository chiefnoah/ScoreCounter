/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.loosepixel.scorecounter;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;

public class Settings extends PreferenceActivity {

    HistoryAdapter historyDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        setContentView(R.layout.activity_settings);
        historyDatabaseAdapter = HistoryAdapter.getInstance(this);
    }

    public void clearDatabase(View v) {
        historyDatabaseAdapter.clearHistory();
    }

    @Override
    public void onStart() {
        super.onStart();
        //historyDatabaseAdapter.open();
    }

    @Override
    public void onStop() {
        super.onStop();
        //historyDatabaseAdapter.close();
    }


}
