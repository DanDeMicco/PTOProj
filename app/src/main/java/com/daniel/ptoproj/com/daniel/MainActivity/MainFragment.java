package com.daniel.ptoproj.com.daniel.MainActivity;

/**
 * Created by ddemicco on 6/12/14.
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.daniel.ptoproj.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainFragment extends Fragment {
    String TAG = this.getClass().getSimpleName();
    private CalculateHours calculateHours;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d(TAG, "on create main frag");

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String hoursPerPaycheck =  shared.getString("hours_edittext_preference", "");
        String currentHours = shared.getString("current_hours_edittext_preference", "");
        String maxHours = shared.getString("max_hours_edittext_preference", "");
        String paycheckFrequency = shared.getString("frequency_edittext_preference", "Weekly");
        String nextPaycheckDate = shared.getString("next_paycheck_preference", "");

        int nextPaycheckYear = shared.getInt("YEAR", 0);
        int nextPaycheckMonth = shared.getInt("MONTH", 0);
        int nextPaycheckDay = shared.getInt("DAY", 0);

        Log.d(TAG, "hours " + hoursPerPaycheck);
        Log.d(TAG, "current " + currentHours);
        Log.d(TAG, "max " + maxHours);
        Log.d(TAG, "paycheckFrequency " + paycheckFrequency);
        Log.d(TAG, "nextPaycheckDate " + nextPaycheckMonth + "/" + nextPaycheckDay + "/" + nextPaycheckYear);

        calculateHours = new CalculateHours();

        //setup the datepicker
        setDate(rootView);

        return rootView;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "on resume main frag");

        super.onResume();

    }

    public void setDate(View v){
        //the datepicker
        DatePicker dp = (DatePicker) v.findViewById(R.id.datePicker);
        final TextView hoursDescTextView = (TextView) v.findViewById(R.id.total_textView);
        final TextView hoursTextView = (TextView) v.findViewById(R.id.total_number_textView);

        try{
            Calendar cal = Calendar.getInstance();
            //set the min date to today
            Time now = new Time();
            now.setToNow();
            dp.setMinDate(now.toMillis(false));

            dp.init(now.year, now.month, now.monthDay, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                    Log.d(TAG, "" + year);
                    float ptoHours = calculateHours.calculatePtoHours(year, month, day);
                    hoursTextView.setText("" + ptoHours);
                    hoursDescTextView.setVisibility(View.VISIBLE);
                    hoursTextView.setVisibility(View.VISIBLE);
                }
            });
        }
        catch (Exception e){
            Log.e(TAG, "error");
            Log.e(TAG, e.toString());
        }
    }


}