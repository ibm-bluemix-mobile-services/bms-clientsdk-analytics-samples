package com.mike.MyApp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal.BMSAnalytics;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.*;
import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.*;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.*;

public class MainActivity extends AppCompatActivity {

    private static final Logger logger = Logger.getLogger("bmsSampleapp");
    private static final String buttonClickKey = "buttonClick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(MainActivity.class.getName(),"MainActivity");
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_US_SOUTH); // You can change the region
        Analytics.init(getApplication(), "MFPAnalyticsLogger", "2bd5ad2a-ff2a-459c-bf0a-9d2ec90a538e", true,true,Analytics.DeviceEvent.ALL);//92cb9cd4-bbb2-4fbd-b9cc-009a92fdeaa7
        setContentView(R.layout.activity_main);
        BMSAnalytics.overrideServerHost="http://10.0.2.2:8000";//;////"https://mobile-analytics-dashboard.ng.bluemix.net"
        Logger.storeLogs(true);
        Logger.setLogLevel(Logger.LEVEL.INFO);
        //BMSAnalytics.logLocation();
        //Log.d("Mohan", "Calling Log Location");
        //Analytics.logLocation();
//        if (Logger.isUnCaughtExceptionDetected()) {
//            new CrashDetectedDialog(this).show();
//        }
        Analytics.send();
    }

    public void onButtonAdapterCall(View v) throws JSONException {

        Analytics.logLocation();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(buttonClickKey, "Adapter Call");
        Analytics.log(jsonObject);
        //Analytics.send();
    }

    public void onButtonLogSend(View v) throws JSONException {

        Analytics.send();
    }

    public void onButtonSendB(View v) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(buttonClickKey, "Send Logs");
        Logger.send();
    }

    public void onButtonLogStuff(View v) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(buttonClickKey, "Log Stuff");
        EditText mEdit = (EditText) findViewById(R.id.editText);

        Logger.setLogLevel(Logger.LEVEL.DEBUG);
        logger.info("INFO");
        logger.debug(mEdit.getText().toString());
        logger.warn("WARN");
        //logger.error("ERROR");

    }

    public void onButtonCrashMe(View v) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(buttonClickKey, "Crash Me");
        Analytics.log(jsonObject);

        int denominator = 0;
        logger.debug("About to divide by " + denominator);

        int crash = 8 / denominator;
    }

    public void onButtonRetrieveLogProfile(View v) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(buttonClickKey, "Get Log Profile");
    }

    public void onButtonAddNetwork(View v) throws JSONException {
        String getResponse = null;
        try {
            getResponse = doGetRequest("https://www.kaggle.com/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getResponse);
    }

    public void onButtonAddLife(View v) throws JSONException {

    }

    public void onButtonRemoveNetwork(View v) throws JSONException {
    }

    public void onButtonRemoveLife(View v) throws JSONException {
    }


    protected static void sendLogsAndAnalytics() {
        Analytics.send();
        Logger.send();
    }

    public void onButtonSetUser(View v) throws JSONException {
        EditText mEdit = (EditText) findViewById(R.id.editText3);
        Toast.makeText(getApplicationContext(),mEdit.getText().toString(),Toast.LENGTH_LONG).show();
        Analytics.setUserIdentity(mEdit.getText().toString());
    }

    public void onButtonUnsetUser(View v) throws JSONException {
        //WLAnalytics.unsetUserContext();
        Analytics.clearUserIdentity();
    }

    String doGetRequest(String url) throws IOException {
        Request request = new Request(url, "GET");

        ResponseListener listener = new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                Log.i("MyApp", "Response: " + response.getResponseText());
            }

            @Override
            public void onFailure(Response response, Throwable t, JSONObject extendedInfo) {
                Log.i("MyApp", "Request failed. Response: " + response.getResponseText() + ". Error: " + t.getLocalizedMessage());
            }
        };

        request.send(getApplicationContext(), listener);

        return "";
    }
}
