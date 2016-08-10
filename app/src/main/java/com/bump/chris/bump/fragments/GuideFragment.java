package com.bump.chris.bump.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.GuideActivity;

/**
 * Created by chris on 09/08/16.
 */
public class GuideFragment extends Fragment {
    public static final String LOG_TAG = "guideFragment";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide, container, false);
        boolean isNegative = getArguments().getBoolean(GuideActivity.EXTRA_IS_NEGATIVE);
        if (isNegative) {
            v.findViewById(R.id.unknown_guide_container).setVisibility(View.GONE);
            v.findViewById(R.id.negative_guide_container).setVisibility(View.VISIBLE);
        } else {
            v.findViewById(R.id.unknown_guide_container).setVisibility(View.VISIBLE);
            v.findViewById(R.id.negative_guide_container).setVisibility(View.GONE);
        }


        return v;
    }
}
