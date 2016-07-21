package com.bump.chris.bump.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bump.chris.bump.fragments.SigninFragment;

public class LandingActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SigninFragment();
    }
}
