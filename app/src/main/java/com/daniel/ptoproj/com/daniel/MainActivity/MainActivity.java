package com.daniel.ptoproj.com.daniel.MainActivity;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Build;

import com.daniel.ptoproj.R;
import com.daniel.ptoproj.com.daniel.Settings.SettingsFragment;


public class MainActivity extends ActionBarActivity {
    private String TAG = MainActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // Display the fragment as the main content.
            getFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, new SettingsFragment())
                    .commit();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){
        if(Build.VERSION.SDK_INT >= 11 ){
            Log.d(TAG, "back " + getFragmentManager().getBackStackEntryCount());

            if (getFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStack();
            }
            else{
                super.onBackPressed();
            }
        }
        else{
            super.onBackPressed();
        }
    }
}
