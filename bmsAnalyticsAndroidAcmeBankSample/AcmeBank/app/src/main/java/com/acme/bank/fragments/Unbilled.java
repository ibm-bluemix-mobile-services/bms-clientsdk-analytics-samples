package com.acme.bank.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acme.bank.R;
import com.acme.bank.activity.AnalyticsLib;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Unbilled extends Fragment {
    private float mTouchPosition;
    private float mReleasePosition;
    private AnalyticsLib analytics;
    public Unbilled() {
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
        View view =inflater.inflate(R.layout.fragment_unbilled, container, false);
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
                        analytics.stepsInCreditCard("UnBilled_SCROLL_DOWN");
                    } else {
                        //user scroll up
                        analytics.stepsInCreditCard("UnBilled_SCROLL_UP");
                    }
                }
                return false;
            }
        });
        return view;
    }


}
