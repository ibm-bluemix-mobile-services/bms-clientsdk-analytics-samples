package com.acme.bank.activity;

/**
 * Created by adityabugadi on 07/03/18.
 */

import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.Analytics;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Response;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.ResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.Logger;

import org.json.JSONException;
import org.json.JSONObject;



public class AnalyticsLib {

    JSONObject jsonObjectPage = new JSONObject();

    public void pageNavigation(String sourcePage, String destinationPage) {   //done
        try {
            // WLAnalytics.init(app);
            jsonObjectPage.put("fromPage", sourcePage);
            jsonObjectPage.put("toPage", destinationPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }

    public void sessionTime(double SessionMinutes) {
        try {
            jsonObjectPage.put("sessionTime", SessionMinutes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);

    }

    public void numberOfPagesVisited(int count) {
        try {
            jsonObjectPage.put("numberOfPagesVisted", count);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }

    public void closedOnPage(String page) {              //done
        try {
            jsonObjectPage.put("closedOnPage", page);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }

    public void pageVisted(String page) {                //done
        try {
            jsonObjectPage.put("pageVisted", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }

    public void stepsInCheckDeposit(String step) {
        try {
            jsonObjectPage.put("stepsInCheckDeposit", step);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);

    }

    public void stepsInSavings(String step) {                //done
        try {
            jsonObjectPage.put("stepsInSavingsAccountPage", step);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);

    }

    public void stepsInCreditCard(String step) {
        try {
            jsonObjectPage.put("stepsInCreditCardPage", step);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);

    }

    public void stepsInAppointment(String step) {
        try {
            jsonObjectPage.put("stepsInAppointment", step);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }

    public void sendAnalytics() {
        Analytics.send(new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                // Handle Analytics send success here.
                System.out.println("*************************************success*************************************");
                System.exit(0);
            }

            @Override
            public void onFailure(Response response, Throwable throwable, JSONObject jsonObject) {
                System.out.println("*************************************Failure*************************************");
                // Handle Analytics send failure here.
            }
        });
    }

    public void sendLogger() {
        Logger.send(new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                // Handle Logger send success here.
                System.out.println("*************************************success logs*************************************");
            }

            @Override
            public void onFailure(Response response, Throwable throwable, JSONObject jsonObject) {
                // Handle Logger send failure here.
                System.out.println("*************************************Failure logs*************************************");
            }
        });
    }

    public void ButtonClick(String button) {
        try {
            jsonObjectPage.put("buttonClicked", button);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Analytics.log(jsonObjectPage);
    }
//    public void addNetworkEvents(){
//        Analytics.addDeviceEventListener(Analytics.DeviceEvent.NETWORK);
//    }
//    public void removeNetworkEvents(){
//        Analytics.removeDeviceEventListener(Analytics.DeviceEvent.NETWORK);
//    }
//    public void addLifeCycleEvents(){
//        Analytics.addDeviceEventListener(Analytics.DeviceEvent.LIFECYCLE);
//    }
//    public void removeLifeCycleEvents(){
//        Analytics.removeDeviceEventListener(WLAnalytics.DeviceEvent.LIFECYCLE);
//    }
}
