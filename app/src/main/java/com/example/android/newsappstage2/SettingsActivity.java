package com.example.android.newsappstage2;

import android.content.Intent;
import android.preference.PreferenceActivity;

import java.util.List;

/**
 * Created by Emoke Hajdu on 7/20/2018.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return SettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
