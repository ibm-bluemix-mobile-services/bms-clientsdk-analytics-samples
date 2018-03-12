package com.acme.bank.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.acme.bank.R;
import com.acme.bank.activity.AnalyticsLib;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.Logger;

import java.util.ArrayList;
import java.util.List;


public class CrediCardFragment extends Fragment {

    private AnalyticsLib analytics;
    private String previoustab;
    private String currenttab;
    private Logger logger;
    public CrediCardFragment() {
        // Required empty public constructor
    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new Unbilled(), "unBilled");
        adapter.addFragment(new Billed(), "Billed");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //duedate
        previoustab ="unBilled";

        View view =inflater.inflate(R.layout.fragment_creditcard, container, false);
        analytics =new AnalyticsLib();
        logger =Logger.getLogger("logger1");
        logger.storeLogs(true);
        analytics.pageVisted(previoustab);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        Button MakePayment =(Button)view.findViewById(R.id.ccPaymentButton);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

               // analytics.pageNavigation(previoustab,tab.getText().toString());

                // analytics.sendAnalytics();
                // analytics.sendLogger();
                analytics.pageVisted(tab.getText().toString());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                previoustab = tab.getText().toString();
//                analytics.pageVisted(previoustab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabs.setupWithViewPager(viewPager);
        MakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int denominator = 0;
                logger.debug("About to divide by " + denominator);

                int crash = 8 / denominator;

            }
        });
        return view;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


}
