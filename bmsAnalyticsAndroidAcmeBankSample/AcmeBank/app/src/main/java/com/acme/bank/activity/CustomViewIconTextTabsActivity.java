package com.acme.bank.activity;

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

import com.acme.bank.R;
import com.acme.bank.fragments.CrediCardFragment;
import com.acme.bank.fragments.HomeFragment;
import com.acme.bank.fragments.SavingsAcc;
import com.applaunch.api.AppLaunch;
import com.applaunch.api.AppLaunchException;
import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.Analytics;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;

import java.util.ArrayList;
import java.util.List;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        previousPage ="Home";
        currentPage="Home";

        //  Analytics Initialisation
        activeSessionStartTime = System.currentTimeMillis();
        analytics =new AnalyticsLib();
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_US_SOUTH);
        Analytics.init(getApplication(), "Acme Bank", "08f14171-735f-43cc-b1a8-c9c0abcb9344", true, Analytics.DeviceEvent.ALL);
        Analytics.enable();
        //  End of Initialisation


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
                long totalActiveMillis =  System.currentTimeMillis() - activeSessionStartTime;
                double seconds = (totalActiveMillis / 1000) % 60;
                double minutes = (totalActiveMillis / 1000) / 60;
                seconds = seconds / 100;
                double minutesSec = minutes + seconds;
                analytics.sessionTime(minutesSec);

                analytics.closedOnPage(currentPage);

                analytics.sendLogger();
                analytics.sendAnalytics();



            }
        });
        setupTabIcons();
    }



    @Override
    protected void onResume() {
        super.onResume();
      //  activeSessionStartTime = System.currentTimeMillis();

        // Toast.makeText(this.getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

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
