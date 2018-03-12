package com.acme.bank.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acme.bank.R;
import com.acme.bank.activity.AnalyticsLib;


public class SavingsAcc extends Fragment {
    private Dialog myDailog;
    private float mTouchPosition;
    private float mReleasePosition;
    AnalyticsLib analytics;

    public SavingsAcc() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_list, container, false);
        analytics =new AnalyticsLib();
        Button transactionButton=(Button)view.findViewById(R.id.transactionButton);
        ScrollView scrollView=(ScrollView)view.findViewById(R.id.scrollView);
        myDailog =new Dialog(getActivity());
        scrollView.setOnTouchListener(new View.OnTouchListener(){


            @Override
            public boolean onTouch(View view, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mTouchPosition = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mReleasePosition = event.getY();

                    if (mTouchPosition - mReleasePosition > 0) {
                        // user scroll down
                        analytics.stepsInSavings("SCROLL DOWN");
                    } else {
                        //user scroll up
                        analytics.stepsInSavings("SCROLL UP");
                    }
                }
                return false;
            }
        });



        transactionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myDailog.setContentView(R.layout.fragment_savingspopup);
               TextView acmeAccounts= (TextView)myDailog.findViewById(R.id.textView1);
               TextView otherAcmeAccounts= (TextView)myDailog.findViewById(R.id.textView2);
               TextView otherBankAccounts= (TextView)myDailog.findViewById(R.id.textView3);
               TextView oneTime= (TextView)myDailog.findViewById(R.id.textView4);
               TextView payCreditcard= (TextView)myDailog.findViewById(R.id.textView5);
               TextView addPayee= (TextView)myDailog.findViewById(R.id.textView6);

               acmeAccounts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_linked_Acme");
                        analytics.stepsInSavings("Trans_lin_Acme");
                    }
                });
                otherAcmeAccounts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_Other_Acme");
                        analytics.stepsInSavings("Trans_oth_Acme");
                    }
                });
               otherBankAccounts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_Other_Bank");
                        analytics.stepsInSavings("Trans_oth_bank");
                    }
                });
                oneTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_One_IMPS");
                        analytics.stepsInSavings("Trans_One_IMPS");
                    }
                });
                payCreditcard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_pay_CC");
                        analytics.stepsInSavings("Trans_pay_CC");
                    }
                });
                addPayee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        analytics.pageNavigation("Savings","Trans_add_paye");
                        analytics.stepsInSavings("Trans_add_paye");
                    }
                });

                    myDailog.show();
                //  Intent i = new Intent(getContext(),SavingsAccountList.class);
                //  startActivity(i);
            }
        });

        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        System.out.println("Printingasdasdasd");
//    }

//    @Override
//    public void setMenuVisibility(final boolean visible) {
//        super.setMenuVisibility(visible);
//        if (visible) {
//            analytics.pageVisted("SavingsPage");
//        }
//    }
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        mainActivity.setNumberOfPages(mainActivity.getNumberOfPages() + 1);
////        mainActivity.setCurrentPage("AccountsPage");
//       // analytics.pageVisted("AccountsPage");
//        System.out.println("Creating Savings page");
//
//    }


}
