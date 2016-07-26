package com.bump.chris.bump.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;

import com.bump.chris.bump.R;
import com.bump.chris.bump.fragments.BumpFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

/**
 * Created by chris on 18/07/16.
 */
public class BumpActivity extends BottomNavigationActivity {
    @Override
    protected Fragment createFragment() {
        return new BumpFragment();
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected int getDefaultPosition() {
        return 0;
    }

}



