package com.bump.chris.bump.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.bump.chris.bump.R;
import com.bump.chris.bump.activities.BumpActivity;
import com.bump.chris.bump.fragments.ManualBumpFragment;
import com.bump.chris.bump.fragments.PartnerStatusFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by chris on 22/07/16.
 */
public class BumpTask extends AsyncTask<Void, Void, String> {
    private static String LOG_TAG = "BumpTask";
    private Context mContext;
    private Map<String, String> mBumpDetails;
    private TextView mProgessText;

    public BumpTask(Context context, TextView progressText, Map<String, String> bumpDetails) {
        mContext = context;
        mBumpDetails = bumpDetails;
        mProgessText = progressText;
    }
    @Override
    protected String doInBackground(Void... voids) {
        BumpHandler bumpHandler = new BumpHandler(mBumpDetails);
        String result = bumpHandler.bump();
        return result;
    }

    @Override
    protected void onPreExecute() {
        mProgessText.setText(R.string.bump_wait_message3);
    }

    protected void onPostExecute(String resultJson) {
        Log.d(LOG_TAG, resultJson);
        mProgessText.setText(R.string.bump_wait_message4);
        if (resultJson == null) {
            return;
        }
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> jsonMap = gson.fromJson(resultJson, type);
        if (jsonMap.get("status").equals("error")) {
            Log.d(LOG_TAG, "Error!");
        } else {
            String partnerStatus = jsonMap.get("partnerStatus");
            String testDate = jsonMap.get("testDate");
            Bundle fragmentArguments = new Bundle();
            fragmentArguments.putString(PartnerStatusFragment.ARG_PARTNER_STATUS, partnerStatus);
            fragmentArguments.putString(PartnerStatusFragment.ARG_TEST_DATE, testDate);

            PartnerStatusFragment partnerStatusFragment = new PartnerStatusFragment();
            partnerStatusFragment.setArguments(fragmentArguments);
            FragmentActivity activity = (FragmentActivity) mContext;
            partnerStatusFragment.show(activity.getSupportFragmentManager(), "Partner Status");

        }
    }


}
