package com.bump.chris.bump.activities;

import android.support.v4.app.Fragment;

import com.bump.chris.bump.fragments.BumpFragment;

/**
 * Created by chris on 18/07/16.
 */
public class BumpActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new BumpFragment();
    }
}
