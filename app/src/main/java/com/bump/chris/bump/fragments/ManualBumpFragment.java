package com.bump.chris.bump.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.AnimationActivity;
import com.bump.chris.bump.network.BumpTask;

/**
 * Created by chris on 21/07/16.
 */
public class ManualBumpFragment extends DialogFragment {
    public static final String EXTRA_PARTNER_USERNAME = "partnerUsername";
    private EditText mPartnerEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_manual_bump, null);
        mPartnerEditText = (EditText) v.findViewById(R.id.dialog_partnerId_input);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String partnerID = mPartnerEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_PARTNER_USERNAME, partnerID);
                BumpPermissionFragment bumpPermissionFragment = new BumpPermissionFragment();
                bumpPermissionFragment.setArguments(bundle);
                bumpPermissionFragment.show(getActivity().getSupportFragmentManager(), "Join Network?");
                /*String partnerID = mPartnerEditText.getText().toString();
                Intent startAnimationActivityIntent = new Intent(getActivity(), AnimationActivity.class);
                startAnimationActivityIntent.putExtra(EXTRA_PARTNER_USERNAME, partnerID);
                startActivity(startAnimationActivityIntent);*/
            }
        };
        return new AlertDialog.Builder(getActivity()).setTitle("Bump").setView(v).setPositiveButton(android.R.string.ok, okListener).setNegativeButton(android.R.string.cancel, null).create();
    }
}
