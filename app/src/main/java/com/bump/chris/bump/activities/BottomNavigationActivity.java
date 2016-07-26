package com.bump.chris.bump.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bump.chris.bump.R;
import com.bump.chris.bump.fragments.BumpFragment;
import com.bump.chris.bump.network.AuthenticatorTask;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;
import com.roughike.bottombar.OnTabClickListener;

/**
 * Created by chris on 25/07/16.
 */
public abstract class BottomNavigationActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    BottomBar bottomBar;
    protected abstract Fragment createFragment();

    protected abstract Context getContext();

    protected abstract int getDefaultPosition();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.bottom_navigation_activity);

        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setDefaultTabPosition(getDefaultPosition());

        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                switch(position) {
                    case 0:
                        Intent startBumpActivityIntent = new Intent(getContext(), BumpActivity.class);
                        startActivity(startBumpActivityIntent);
                        break;
                    case 1:
                        Intent startStatusActivityIntent = new Intent(getContext(), StatusActivity.class);
                        startActivity(startStatusActivityIntent);
                        break;
                    case 2:
                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().remove(AuthenticatorTask.PREF_USERNAME).apply();
                        Intent startSupportActivityIntent = new Intent(getContext(), LandingActivity.class);
                        startActivity(startSupportActivityIntent);
                        break;
                }
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });
        bottomBar.setItems(R.menu.bottom_navigation_buttons);

        // Set the color for the active tab. Ignored on mobile when there are more than three tabs.
        bottomBar.setActiveTabColor("#C2185B");



    }
}
