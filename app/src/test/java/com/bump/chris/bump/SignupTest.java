package com.bump.chris.bump;

import android.app.ProgressDialog;

import com.bump.chris.bump.network.Authenticator;
import com.bump.chris.bump.network.SignupHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by chris on 20/07/16.
 */
public class SignupTest {
    @Test
    public void authenticator_returns() throws Exception {
        Map<String, String> credentials = new HashMap<String, String>();
        credentials.put("username", "chrisjmyoon");
        credentials.put("password", "123");
        SignupHandler signupHandler = new SignupHandler(credentials);
        String result = signupHandler.signup();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, Object> jsonMap = gson.fromJson(result, type);
        System.out.println(jsonMap.size());
        System.out.println(jsonMap.get("errorCode"));

        assertEquals(result.length()>0, true);
    }
}
