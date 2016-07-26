package com.bump.chris.bump.activities;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bump.chris.bump.fragments.AnimationFragment;
import com.bump.chris.bump.fragments.SupportFragment;

/**
 * Created by chris on 25/07/16.
 */
public class SupportActivity extends BottomNavigationActivity {
    @Override
    protected Fragment createFragment() {
        return new SupportFragment();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected int getDefaultPosition() {
        return 2;
    }


}
