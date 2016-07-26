package com.bump.chris.bump.fragments;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.AnimationActivity;

/**
 * Created by chris on 18/07/16.
 */
public class BumpFragment extends Fragment {
    private ImageButton mBumpButton;
    private TextView mFunFact;
    private NfcAdapter mNfcAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bump, container, false);
        mBumpButton = (ImageButton) v.findViewById(R.id.bump_button);
        mFunFact = (TextView) v.findViewById(R.id.bump_fact);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());


        mBumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (mNfcAdapter!=null) {
                if (mNfcAdapter.isEnabled()) {
                    Intent i = new Intent(getActivity(), AnimationActivity.class);
                    startActivity(i);
                } else {
                    ManualBumpFragment manualBumpFragment = new ManualBumpFragment();
                    manualBumpFragment.show(getActivity().getSupportFragmentManager(), "Manual Bump");
                    //TODO: Bundle in a popup
                    //Intent setnfc = new Intent(Settings.ACTION_NFC_SETTINGS);
                    //startActivity(setnfc);
                }
            } else {
                //Open dialog to enter partner's id
                ManualBumpFragment manualBumpFragment = new ManualBumpFragment();
                manualBumpFragment.show(getActivity().getSupportFragmentManager(), "Manual Bump");
            }

            }
        });

        return v;
    }
}
