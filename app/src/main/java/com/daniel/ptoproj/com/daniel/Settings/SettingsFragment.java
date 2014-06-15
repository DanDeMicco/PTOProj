package com.daniel.ptoproj.com.daniel.Settings;

/**
 * Created by ddemicco on 6/12/14.
 */

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;

import com.daniel.ptoproj.R;

/**
 * Settings fragment
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsFragment extends PreferenceFragment {
    private String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);

        Preference date = findPreference("next_paycheck_preference");
        date.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Log.d(TAG, "clicked date");
                showDatePickerDialog();
                return false;
            }
        });
//
    }

    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        
    }
}
