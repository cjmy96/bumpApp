package com.bump.chris.bump.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.bump.chris.bump.fragments.GuideFragment;

/**
 * Created by chris on 09/08/16.
 */
public class GuideActivity extends BottomNavigationActivity {
    public static final String EXTRA_IS_NEGATIVE = "isNegative";
    public static final String LOG_TAG = "GuideActivity";
    @Override
    protected Fragment createFragment() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_IS_NEGATIVE, getIntent().getBooleanExtra(EXTRA_IS_NEGATIVE, false));
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(bundle);
        return fragment;
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
