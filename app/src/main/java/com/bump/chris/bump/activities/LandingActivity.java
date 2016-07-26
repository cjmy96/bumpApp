package com.bump.chris.bump.activities;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bump.chris.bump.R;
import com.bump.chris.bump.fragments.SigninFragment;
import com.bump.chris.bump.network.AuthenticatorTask;

public class LandingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SigninFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (isLoggedIn()) {
            super.superOnCreate(savedInstanceState);
            Intent i = new Intent(this, BumpActivity.class);
            startActivity(i);
        } else {
            super.onCreate(savedInstanceState);
        }
    }

    public boolean isLoggedIn() {
        String userName = PreferenceManager.getDefaultSharedPreferences(this).getString(AuthenticatorTask.PREF_USERNAME, null);
        return userName != null;
    }
}
