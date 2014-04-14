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
        historyDatabaseAdapter = new HistoryAdapter(this);
    }

    public void clearDatabase(View v) {
        historyDatabaseAdapter.clearHistory();
    }

    @Override
    public void onStart() {
        super.onStart();
        historyDatabaseAdapter.open();
    }

    @Override
    public void onStop() {
        super.onStop();
        historyDatabaseAdapter.close();
    }


}
