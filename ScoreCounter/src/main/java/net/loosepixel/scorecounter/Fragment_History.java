package net.loosepixel.scorecounter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

/**
 * Created by peder_000 on 2/1/14.
 */
public class Fragment_History extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_history, container, false);
    }
}
