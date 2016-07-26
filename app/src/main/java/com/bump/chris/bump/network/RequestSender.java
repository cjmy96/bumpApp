package com.bump.chris.bump.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by chris on 20/07/16.
 */
public abstract class RequestSender {
    private static final String LOG_TAG = "requestSender";
    public String sendRequest(URL url, String urlParameters) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(urlParameters);
            outputStream.flush();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            String responseOutput = "";
            while((line = br.readLine()) != null ) {
                responseOutput += line;
            }
            br.close();
            return responseOutput;
        } catch (IOException e) {
            Log.d(LOG_TAG, "error");
            e.printStackTrace();
        }
        return null;
    }
}
