package com.bump.chris.bump.activities;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bump.chris.bump.R;
import com.bump.chris.bump.fragments.AnimationFragment;
import com.bump.chris.bump.fragments.ManualBumpFragment;
import com.bump.chris.bump.network.AuthenticatorTask;
import com.bump.chris.bump.network.BumpTask;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 18/07/16.
 */
public class AnimationActivity extends FragmentActivity implements NfcAdapter.OnNdefPushCompleteCallback {
    private static final String LOG_TAG = "AnimationActitivy";
    private String mUsername;
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFiltersArray;
    private TextView mProgressTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_animation);
        mProgressTextView = (TextView) findViewById(R.id.animation_wait_message);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUsername = preferences.getString(AuthenticatorTask.PREF_USERNAME, "");

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
            NdefMessage message = createNdefMessage();
            mNfcAdapter.setNdefPushMessage(message, this);
            Log.d(LOG_TAG, "Set push message");
        } else {
            String partnerUsername = getIntent().getStringExtra(ManualBumpFragment.EXTRA_PARTNER_USERNAME);
            sendBumpRequest(partnerUsername);
        }

        mPendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter iFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            iFilter.addDataType("text/plain");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }
        mIntentFiltersArray = new IntentFilter[] {iFilter, };
    }

    private void sendBumpRequest(String partnerUsername) {
        Map<String, String> bumpDetails = new HashMap<>();
        bumpDetails.put("username", mUsername);
        bumpDetails.put("partnerUsername", partnerUsername);
        new BumpTask(this, mProgressTextView, bumpDetails).execute();
    }

    @Override
    public void onNewIntent(Intent intent) {
        handleNfcIntent(intent);
    }

    @Override
    public void onNdefPushComplete(NfcEvent nfcEvent) {
        //Change text
        Log.d(LOG_TAG, "Successfully fired Nfc!");
    }

    @Override
    public void onPause() {
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFiltersArray, null);
    }



    public void handleNfcIntent(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMsgs != null) {
                NdefMessage receivedMessage = (NdefMessage) rawMsgs[0];
                String partnerUsername = new String(receivedMessage.getRecords()[0].getPayload());

                mProgressTextView.setText(R.string.bump_wait_message2);
                Toast.makeText(this, partnerUsername, Toast.LENGTH_LONG).show();
                sendBumpRequest(partnerUsername);
            } else {
                Toast.makeText(this, "Received empty message", Toast.LENGTH_LONG).show();
            }
        }
    }

    public NdefMessage createNdefMessage() {
        NdefRecord record = createRecord();
        return new NdefMessage(new NdefRecord[]{record});
    }

    public NdefRecord createRecord() {
        byte[] payload = mUsername.getBytes(Charset.forName("UTF-8"));
        NdefRecord record = new NdefRecord(
                NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT,
                new byte[0],
                payload);
        return record;
    }

}
