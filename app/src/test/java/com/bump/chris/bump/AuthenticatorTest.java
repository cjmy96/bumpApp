package com.bump.chris.bump;

import com.bump.chris.bump.network.Authenticator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by chris on 20/07/16.
 */
public class AuthenticatorTest {
    @Test
    public void authenticator_returns() throws Exception {
        Map<String, String> credentials = new HashMap<String, String>();
        credentials.put("username", "chrisjmyoon");
        credentials.put("password", "123");
        Authenticator authenticator = new Authenticator(credentials);
        String result = authenticator.authenticate();
        System.out.println(result);
        assertEquals(result.length()>0, true);
    }
}
