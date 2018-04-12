package com.acme.bank.activity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.bank.R;
import com.acme.bank.fragments.CrediCardFragment;
import com.acme.bank.fragments.HomeFragment;
import com.acme.bank.fragments.SavingsAcc;
import com.applaunch.api.AppLaunch;
import com.applaunch.api.AppLaunchException;
import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.Analytics;
import com.ibm.mobilefirstplatform.clientsdk.android.analytics.internal.BMSAnalytics;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPush;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushException;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushNotificationListener;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPPushResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.push.api.MFPSimplePushNotification;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CustomViewIconTextTabsActivity extends AppCompatActivity implements TabChangedListenerInternal {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private boolean privelagedCustomer = true;
    private AnalyticsLib analytics;
    private String previousPage;
    private String currentPage;
    private long activeSessionStartTime;
    private MFPPush push; // Push client
    private MFPPushNotificationListener notificationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        previousPage ="Home";
        currentPage="Home";

        //  Analytics Initialisation
        activeSessionStartTime = System.currentTimeMillis();
        analytics =new AnalyticsLib();
       // BMSClient.getInstance().initialize(getApplicationContext(), ".stage1.ng.bluemix.net");
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_SYDNEY);
        Analytics.init(getApplication(), "Acme Bank", "ef253401-3e57-4448-a341-f45730c23885", true, Analytics.DeviceEvent.ALL);
        Analytics.enable();
        //  End of Initialisation

        //PUSH

//        push = MFPPush.getInstance();
//        push.initialize(getApplicationContext(),"97b82834-4118-41d7-83ad-b09d5d87407b","1a904dfc-8347-4e0d-8b81-27df8caf4e1f");

        push = MFPPush.getInstance();
        push.initialize(getApplicationContext(),"ac8a06c1-2343-4998-a717-ce885eb350e2","0eac1c64-e20b-463d-a63b-2a778ffb0db3");

        notificationListener = new MFPPushNotificationListener() {
            @Override
            public void onReceive(final MFPSimplePushNotification message) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        new android.app.AlertDialog.Builder(CustomViewIconTextTabsActivity.this)
                                .setTitle("Received a Push Notification")
                                .setMessage(message.getAlert())
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                                .show();
                    }
                });
            }
        };

        MFPPushResponseListener registrationResponselistener = new MFPPushResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                // Split response and convert to JSON object to display User ID confirmation from the backend
                String[] resp = response.split("Text: ");
                try {
                    JSONObject responseJSON = new JSONObject(resp[1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Start listening to notification listener now that registration has succeeded
                push.listen(notificationListener);
            }

            @Override
            public void onFailure(MFPPushException exception) {
                String errLog = "Error registering for push notifications: ";
                String errMessage = exception.getErrorMessage();
                int statusCode = exception.getStatusCode();

                // Set error log based on response code and error message
                if(statusCode == 401){
                    errLog += "Cannot authenticate successfully with Bluemix Push instance, ensure your CLIENT SECRET was set correctly.";
                } else if(statusCode == 404 && errMessage.contains("Push GCM Configuration")){
                    errLog += "Push GCM Configuration does not exist, ensure you have configured GCM Push credentials on your Bluemix Push dashboard correctly.";
                } else if(statusCode == 404 && errMessage.contains("PushApplication")){
                    errLog += "Cannot find Bluemix Push instance, ensure your APPLICATION ID was set correctly and your phone can successfully connect to the internet.";
                } else if(statusCode >= 500){
                    errLog += "Bluemix and/or your Push instance seem to be having problems, please try again later.";
                }

                // make push null since registration failed
                push = null;
            }
        };

        // Attempt to register device using response listener created above
        // Include unique sample user Id instead of Sample UserId in order to send targeted push notifications to specific users
        push.registerDeviceWithUserId("Sample UserID",registrationResponselistener);



        setContentView(R.layout.activity_custom_view_icon_text_tabs);
        try {
            if(AppLaunch.getInstance().isFeatureEnabled("_lwccmx3w2")){
              privelagedCustomer=true;
            }
        } catch (AppLaunchException e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                analytics.pageNavigation(previousPage,tab.getText().toString());
                currentPage=tab.getText().toString();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                previousPage = tab.getText().toString();
                analytics.pageVisted(previousPage);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ImageButton logout =(ImageButton)findViewById(R.id.Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                long totalActiveMillis =  System.currentTimeMillis() - activeSessionStartTime;
//                float seconds = (totalActiveMillis / 1000) % 60;
//                float minutes = (totalActiveMillis / 1000) / 60;
//                seconds = seconds / 100;
//                float minutesSec = minutes + seconds;
//              System.out.println("Data"+minutesSec);
                Random r = new Random();
                int minutesSec = 1;
                analytics.sessionTime(minutesSec);

                analytics.closedOnPage(currentPage);

                analytics.sendLogger();
                analytics.sendAnalytics();



            }
        });

        ImageButton feedback =(ImageButton)findViewById(R.id.Feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(" ****** machi clicked da");
                BMSAnalytics.triggerFeedbackMode();
            }
        });
        setupTabIcons();
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (push != null) {
            push.listen(notificationListener);
        }
      //  activeSessionStartTime = System.currentTimeMillis();

        // Toast.makeText(this.getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (push != null) {
            push.hold();
        }

       // Toast.makeText(this.getApplicationContext(), "Pausing tooo", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Closed on paged");
        analytics.closedOnPage(currentPage);
       // Toast.makeText(getApplicationContext(), "Closed on "+currentPage, Toast.LENGTH_SHORT).show();


    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Home");

        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("CC");

        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_credit_cards, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);


        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Savings Acc");

        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_money, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.addInternalTabChangedListener(this);
        adapter.addFrag(homeFragment, "Home");
        adapter.addFrag(new CrediCardFragment(), "CC");
        adapter.addFrag(new SavingsAcc(), "Savings");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void tabChanged(String tabName) {
        if("CC".equals(tabName)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    analytics.pageNavigation(previousPage,"CC");
                    viewPager.setCurrentItem(1);

                }
            });

        }else if("Savings".equals(tabName)){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    analytics.pageNavigation(previousPage,"Savings");
                    viewPager.setCurrentItem(2);
                }
            });
        }

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public void removeFragment(int position){
            mFragmentList.remove(position);
            mFragmentTitleList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
