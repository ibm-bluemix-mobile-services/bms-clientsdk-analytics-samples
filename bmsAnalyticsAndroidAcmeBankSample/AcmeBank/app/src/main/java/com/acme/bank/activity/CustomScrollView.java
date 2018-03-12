package com.acme.bank.activity;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by adityabugadi on 07/03/18.
 */

public class CustomScrollView extends ScrollView {

    private float mTouchPosition;
    private float mReleasePosition;


    public CustomScrollView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchPosition = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mReleasePosition = event.getY();

            if (mTouchPosition - mReleasePosition > 0) {
                // user scroll down
            } else {
                //user scroll up
            }
        }
        return super.onTouchEvent(event);
    }
}
