package com.acme.bank.fragments;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.bank.MainActivity;
import com.acme.bank.R;
import com.acme.bank.activity.AnalyticsLib;
import com.acme.bank.activity.CustomViewIconTextTabsActivity;
import com.acme.bank.activity.SavingsAccountList;
import com.acme.bank.activity.TabChangedListenerInternal;
import com.acme.bank.service.AcmeBankManager;


/**
 * Created by norton on 10/24/17.
 */

public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    TabChangedListenerInternal tabChangedListenerInternal;
    AnalyticsLib analytics;


    public HomeFragment() {
        // Required empty public constructor
    }

    public void addInternalTabChangedListener( TabChangedListenerInternal tabChangedListenerInternal){
        this.tabChangedListenerInternal = tabChangedListenerInternal;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // mainActivity = (MainActivity) getActivity();

        // Inflate the layout for this fragment
        analytics =new AnalyticsLib();
        View view =inflater.inflate(R.layout.fragment_home, container, false);
       CardView cardViewSavings = view.findViewById(R.id.savings_view);
        CardView cardViewCredit = view.findViewById(R.id.creditcard_view);

        LinearLayout linearLayout = view.findViewById(R.id.customertypeheader);
        TextView customerType = view.findViewById(R.id.customertypetext);
        if("norton".equals(AcmeBankManager.getInstance().getApplicationUser())){
            linearLayout.setBackgroundColor(getResources().getColor(R.color.goldenColor));
            customerType.setText("Privileged Customer");
        }else{
            linearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            customerType.setText("Welcome to Acme Bank");
        }

        cardViewSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analytics.ButtonClick("Savings_card");
                tabChangedListenerInternal.tabChanged("Savings");

              //  Intent i = new Intent(getContext(),SavingsAccountList.class);
              //  startActivity(i);
            }
        });

        cardViewCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analytics.ButtonClick("CC_card");
                tabChangedListenerInternal.tabChanged("CC");

                //  Intent i = new Intent(getContext(),SavingsAccountList.class);
                //  startActivity(i);
            }
        });
        return view;
    }

}

