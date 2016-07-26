package com.bump.chris.bump.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by chris on 18/07/16.
 */
public class BumpHandler extends RequestSender {
    private Map<String, String> mBumpDetails;

    public BumpHandler(Map<String, String> bumpDetails) {
        mBumpDetails = bumpDetails;
    }

    public String bump() {
        try {
            Gson gson = new GsonBuilder().create();
            String detailsJson = gson.toJson(mBumpDetails);
            String action = "bump";
            String urlParameters = String.format("action=%s&contents=%s", action, detailsJson);
            URL url = new URL("http://192.168.1.100:8080/Bump/clientHandler");
            return sendRequest(url, urlParameters);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
