package com.bump.chris.bump.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 26/07/16.
 */
public class StatusHandler extends RequestSender {
    private String mUsername;
    public StatusHandler(String username) {
        mUsername = username;
    }
    public String retrieveStatus() {
        try {
            Gson gson = new GsonBuilder().create();
            Map<String, String> requestDetails = new HashMap<>();
            requestDetails.put("username", mUsername);
            String detailsJson = gson.toJson(requestDetails);
            String action = "retrieveStatus";
            String urlParameters = String.format("action=%s&contents=%s", action, detailsJson);
            URL url = new URL("http://192.168.1.100:8080/Bump/clientHandler");
            return sendRequest(url, urlParameters);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
