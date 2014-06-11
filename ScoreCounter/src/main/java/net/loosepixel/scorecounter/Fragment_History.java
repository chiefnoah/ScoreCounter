/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package net.loosepixel.scorecounter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by peder_000 on 2/1/14.
 */
public class Fragment_History extends Fragment {


    Cursor cursor;

    CursorAdapter cursorAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_history, container, false);
        View V = inflater.inflate(R.layout.fragment_history, container, false);


        //Activity activity = getActivity();
        //if(activity instanceof Main) {
        //           cursor = ((Main) activity).getCursor();
        HistoryAdapter ha = HistoryAdapter.getInstance(getActivity());
        ha.open();
        cursor = ha.getDataCursor();
        Toast.makeText(getActivity(), "Cursor has " + cursor.getColumnCount() + " columns", Toast.LENGTH_SHORT);
        cursor.moveToFirst();
        //}

        int items[] = {R.id.item_index, R.id.item_date, R.id.item_weight, R.id.item_number, R.id.item_total};
        cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.database_list_item,
                cursor, HistoryAdapter.columns, items, 0);


        ListView LV = (ListView) V.findViewById(R.id.history_list);
        if (cursorAdapter != null) {
            LV.setAdapter(cursorAdapter);
            Log.i("NOAH", "The cursor adapter has been set!");
        } else {
            Log.i("NOAH", "The cursor adapter has NOT been set. :(");
            //Toast.makeText(getActivity(), "Failed to get cursor", Toast.LENGTH_SHORT).show();
        }
        return V;

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
