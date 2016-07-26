package com.bump.chris.bump.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bump.chris.bump.fragments.StatusFragment;

/**
 * Created by chris on 25/07/16.
 */
public class StatusActivity  extends BottomNavigationActivity {
    @Override
    protected Fragment createFragment() {
        return new StatusFragment();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected int getDefaultPosition() {
        return 1;
    }


}
