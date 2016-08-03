package com.bump.chris.bump.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by chris on 20/07/16.
 */
public class SignupHandler extends RequestSender {
    Map<String, String> mCredentials;
    public SignupHandler(Map<String, String> credentials) {
        mCredentials = credentials;
    }
    public String signup() {
        try {
            Gson gson = new GsonBuilder().create();
            String credentialsJson = gson.toJson(mCredentials);
            String action = "signup";
            String urlParameters = String.format("action=%s&contents=%s", action, credentialsJson);
            URL url = new URL("http://52.160.106.132/clientHandler");
            return sendRequest(url, urlParameters);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
