package com.bump.chris.bump.fragments;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bump.chris.bump.R;
import com.bump.chris.bump.network.AuthenticatorTask;
import com.bump.chris.bump.network.StatusTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by chris on 25/07/16.
 */
public class StatusFragment extends Fragment {
    private final static String LOG_TAG = "StatusFragment";
    private TextView mStatusAlert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, container, false);
        mStatusAlert = (TextView) v.findViewById(R.id.status_alert);
        String username = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(AuthenticatorTask.PREF_USERNAME, "");
        Log.d(LOG_TAG, username);
        new StatusTask(getContext(), username) {
            @Override
            protected void onPostExecute(String result) {
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, String> jsonMap = gson.fromJson(result, type);
                if (jsonMap.get("status").equals("error")) {
                    Log.d(LOG_TAG, "Error!");
                } else {
                    boolean isUserAtRisk = Boolean.parseBoolean(jsonMap.get("isUserAtRisk"));
                    if (isUserAtRisk) {
                        String infections = jsonMap.get("infections");
                        String suggestedTestDate = jsonMap.get("suggestedTestDate");
                        String alertText = String.format(getString(R.string.status_alert_risk), infections, suggestedTestDate);
                        mStatusAlert.setText(alertText);
                    } else {
                        mStatusAlert.setText(R.string.status_alert_safe);
                    }
                }
            }
        }.execute();
        return v;
    }



}
