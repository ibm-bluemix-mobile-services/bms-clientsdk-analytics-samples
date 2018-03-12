package com.acme.bank.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.acme.bank.R;
import com.acme.bank.activity.AnalyticsLib;


public class Billed extends Fragment {
    private float mTouchPosition;
    private float mReleasePosition;
    private AnalyticsLib analytics;
    public Billed() {
        // Required empty public constructor
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
        analytics =new AnalyticsLib();
        View view =inflater.inflate(R.layout.fragment_billed, container, false);
        ScrollView scrollView =(ScrollView) view.findViewById(R.id.scrollView);
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
                        analytics.stepsInCreditCard("Billed_SCROLL_DOWN");
                    } else {
                        //user scroll up
                        analytics.stepsInCreditCard("Billed_SCROLL_UP");
                    }
                }
                return false;
            }
        });
        return view;
    }


}
