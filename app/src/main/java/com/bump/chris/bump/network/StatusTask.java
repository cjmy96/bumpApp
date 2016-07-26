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
import java.util.Map;

/**
 * Created by chris on 26/07/16.
 */
public class StatusTask extends AsyncTask<Void, Void, String> {
    private final static String LOG_TAG = "StatusTask";

    private String mUsername;
    private Context mContext;
    private ProgressDialog mProgressDialog;

    @Override
    protected String doInBackground(Void... voids) {
        StatusHandler statusHandler = new StatusHandler(mUsername);
        String result = statusHandler.retrieveStatus();
        mProgressDialog.dismiss();
        return result;
    }

    public StatusTask(Context context, String username) {
        mContext = context;
        mUsername = username;
    }

    protected void onPreExecute(){
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Retrieving Status...");
        mProgressDialog.show();
    }

    protected void onPostExecute() {

    }

}
