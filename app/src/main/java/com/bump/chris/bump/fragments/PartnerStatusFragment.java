package com.bump.chris.bump.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.AnimationActivity;
import com.bump.chris.bump.activities.BumpActivity;
import com.bump.chris.bump.activities.GuideActivity;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by chris on 18/07/16.
 */
public class PartnerStatusFragment extends DialogFragment {
    public final static String ARG_PARTNER_STATUS = "partnerStatus";
    public final static String ARG_TEST_DATE = "testDate";

    private String mPartnerStatus;
    private String mTestDate;

    private TextView mStatusTextView;
    private TextView mTestDateTextView;
    private TextView mWarningTextView;
    private TextView mSloganTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPartnerStatus = getArguments().getString(ARG_PARTNER_STATUS);
        mTestDate = getArguments().getString(ARG_TEST_DATE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_partner_status, null);
        mStatusTextView = (TextView) v.findViewById(R.id.dialog_partner_status);
        mTestDateTextView = (TextView) v.findViewById(R.id.dialog_partner_date);
        mWarningTextView = (TextView) v.findViewById(R.id.dialog_partner_warning_message);
        mSloganTextView = (TextView) v.findViewById(R.id.dialog_partner_slogan);
        if (mPartnerStatus.equals("true")) {
            mStatusTextView.setText(R.string.partner_status_message_negative);
            mTestDateTextView.setText(mTestDate);
        } else {
            mStatusTextView.setText(R.string.partner_status_message_unknown);
            mTestDateTextView.setVisibility(View.GONE);
            mWarningTextView.setVisibility(View.GONE);
        }
        Random random = new Random();
        Resources resources = getResources();
        String[] availableSlogans = resources.getStringArray(R.array.protection_slogan);
        String slogan = availableSlogans[random.nextInt(availableSlogans.length)];
        mSloganTextView.setText(slogan);

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), GuideActivity.class);
                intent.putExtra(GuideActivity.EXTRA_IS_NEGATIVE, mPartnerStatus.equals("true"));
                startActivity(intent);
            }
        };
        return new AlertDialog.Builder(getActivity()).setTitle("Partner Status").setView(v).setPositiveButton(android.R.string.ok, okListener).create();
    }
}
