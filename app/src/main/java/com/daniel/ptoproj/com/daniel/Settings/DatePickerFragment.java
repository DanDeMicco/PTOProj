package com.daniel.ptoproj.com.daniel.Settings;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        SharedPreferences shared;

        try{
            shared = PreferenceManager.getDefaultSharedPreferences(getActivity());
            int year = shared.getInt("YEAR", c.get(Calendar.YEAR));
            int month = shared.getInt("MONTH", c.get(Calendar.MONTH));
            int day = shared.getInt("DAY", c.get(Calendar.DAY_OF_MONTH));

            Log.d(TAG, "saved year: " + year);
            Log.d(TAG, "saved month: " + month);
            Log.d(TAG, "saved day: " + day);


            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        catch(Exception e){
            Log.d(TAG, e.toString());
        }

        return null;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // save date to shared prefs
        try{
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putInt("YEAR", year).putInt("MONTH", month).putInt("DAY", day).commit();
        }
        catch (Exception e){
            Log.d(TAG, e.toString());
        }

    }
}