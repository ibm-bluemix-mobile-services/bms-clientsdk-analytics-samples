package com.mike.MyApp;

import android.app.Application;
import android.util.Log;

//import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPush;
//import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushException;
//import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushNotificationListener;
//import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushResponseListener;
//import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPSimplePushNotification;


/**
 * Created by rott on 2/17/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        MFPPush.getInstance().initialize(this);
//
//        MFPPush.getInstance().registerDevice(new MFPPushResponseListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                Log.d("MIKE", "push registration successful: " + s);
//            }
//
//            @Override
//            public void onFailure(MFPPushException e) {
//                Log.d("MIKE", "push registration failed: " + e.getMessage());
//            }
//        });


        //WLClient.createInstance(this);
        //Logger.setContext(this);
        //WLAnalytics. init(this);
        //WLLifecycleHelper.getInstance();
        //WLAnalytics.removeDeviceEventListener(WLAnalytics.DeviceEvent.LIFECYCLE);

        //WLAnalytics.addDeviceEventListener(WLAnalytics.DeviceEvent.NETWORK);
        //WLAnalytics.addDeviceEventListener(WLAnalytics.DeviceEvent.LIFECYCLE);
    }
}
