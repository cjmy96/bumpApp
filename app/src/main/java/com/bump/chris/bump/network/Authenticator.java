package com.bump.chris.bump.network;

import android.app.DownloadManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

/**
 * Created by chris on 18/07/16.
 */
public class Authenticator extends RequestSender {
    private final String LOG_TAG = "Authenticator";
    Map<String, String> mCredentials;
    public Authenticator(Map<String, String> credentials) {
        mCredentials = credentials;
    }

    public String authenticate() {
        try {
            Gson gson = new GsonBuilder().create();
            String credentialsJson = gson.toJson(mCredentials);
            String action = "login";
            String urlParameters = String.format("action=%s&contents=%s", action, credentialsJson);
            URL url = new URL("http://192.168.1.100:8080/Bump/clientHandler");
            return sendRequest(url, urlParameters);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
