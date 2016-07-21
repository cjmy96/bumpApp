package com.bump.chris.bump.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bump.chris.bump.activities.BumpActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 20/07/16.
 */
public class SignupTask extends AsyncTask<Void, Void, String> {
    private static final String PREF_USERNAME = "username";
    private final String LOG_TAG = "SignupTask";
    private final Context mContext;
    ProgressDialog mProgressDialog;
    Map<String, String> mCredentials;

    public SignupTask(Context context, Map<String, String> credentials) {
        mContext = context;
        mCredentials = credentials;
    }


    @Override
    protected String doInBackground(Void... voids) {
        SignupHandler accountCreator = new SignupHandler(mCredentials);
        String result = accountCreator.signup();
        mProgressDialog.dismiss();
        return result;
    }

    protected void onPreExecute(){
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Signing up");
        mProgressDialog.show();
    }

    protected void onPostExecute(String resultJson) {
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, String>>(){}.getType();

        Map<String, String> jsonMap = gson.fromJson(resultJson, type);
        if (jsonMap.get("status").equals("error")) {
            if (jsonMap.get("errorCode").equals("1062")) {
                Log.d(LOG_TAG, "DUPLICATE!");
            }
        } else {
            PreferenceManager.getDefaultSharedPreferences(mContext)
                .edit()
                .putString(PREF_USERNAME, mCredentials.get("username"))
                .apply();
            Intent i = new Intent(mContext, BumpActivity.class);
            mContext.startActivity(i);
        }
        mProgressDialog.dismiss();
    }

}
