package net.loosepixel.scorecounter;

import android.os.Bundle;
import android.preference.PreferenceActivity;
//TODO: add settings

public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }


}
