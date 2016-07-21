package com.bump.chris.bump.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bump.chris.bump.R;
import com.bump.chris.bump.network.AuthenticatorTask;
import com.bump.chris.bump.network.SignupTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 18/07/16.
 */
public class SigninFragment extends Fragment {
    private Button mLoginButton;
    private TextView mSignupPrompt;
    private EditText mIdEditText;
    private EditText mPasswordEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin, container, false);
        mIdEditText = (EditText) v.findViewById(R.id.signin_id);
        mPasswordEditText = (EditText) v.findViewById(R.id.signin_password);
        mLoginButton = (Button) v.findViewById(R.id.signin_button);
        mSignupPrompt = (TextView) v.findViewById(R.id.signin_signup_prompt);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                Map<String, String> credentials = new HashMap<String, String>();
                credentials.put("username", username);
                credentials.put("password", password);
                new AuthenticatorTask(getActivity(), credentials).execute();
            }
        });

        mSignupPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                Map<String, String> credentials = new HashMap<String, String>();
                credentials.put("username", username);
                credentials.put("password", password);
                new SignupTask(getActivity(), credentials).execute();
            }
        });
        return v;
    }
}
