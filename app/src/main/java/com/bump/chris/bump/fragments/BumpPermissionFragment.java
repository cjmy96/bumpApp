package com.bump.chris.bump.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.AnimationActivity;

/**
 * Created by chris on 11/08/16.
 */
public class BumpPermissionFragment extends DialogFragment {
    public static final String LOG_TAG = "BumpPermissionFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bump_permission, null);

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String partnerID = getArguments().getString(ManualBumpFragment.EXTRA_PARTNER_USERNAME);
                Intent startAnimationActivityIntent = new Intent(getActivity(), AnimationActivity.class);
                startAnimationActivityIntent.putExtra(ManualBumpFragment.EXTRA_PARTNER_USERNAME, partnerID);
                startActivity(startAnimationActivityIntent);
            }
        };

        return new AlertDialog.Builder(getActivity()).setTitle("Join Network?").setView(v).setPositiveButton(android.R.string.ok, okListener).setNegativeButton(android.R.string.cancel, null).create();
    }
}
