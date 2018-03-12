package com.acme.bank.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.bank.R;
import com.applaunch.api.AppLaunch;
import com.applaunch.api.AppLaunchException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class LoyaltyFragment extends Fragment {

    public LoyaltyFragment() {
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
        View view =inflater.inflate(R.layout.fragment_loyalty, container, false);
        ImageView imageView = view.findViewById(R.id.offerimage);
        final CardView cardView = view.findViewById(R.id.offers_view);
        cardView.setVisibility(View.GONE);
        try {
           if(AppLaunch.getInstance().isFeatureEnabled("_lwccmx3w2")){
               cardView.setVisibility(View.VISIBLE);
               String featureColor  =AppLaunch.getInstance().getPropertyOfFeature("_lwccmx3w2","_vxk0ptl5d");
               cardView.setCardBackgroundColor(Color.parseColor("#"+featureColor));
               String imageUrl = AppLaunch.getInstance().getPropertyOfFeature("_lwccmx3w2","_qlyy2jpcu");
               if(imageUrl!=null && imageUrl.length()>0){
                   Picasso.with(getActivity())
                           .load(Uri.parse(imageUrl))
                           .placeholder(R.drawable.placeholder)
                           .into(imageView);
               }
               String title = AppLaunch.getInstance().getPropertyOfFeature("_lwccmx3w2","_s17k8apgl");
               if(title!=null && title.length()>0){
                   TextView textView = view.findViewById(R.id.titleview);
                   textView.setText(title);
               }

               String redeemText = AppLaunch.getInstance().getPropertyOfFeature("_lwccmx3w2","_7d83o7nyv");
               if(redeemText!=null && redeemText.length()>0){
                   Button redeemButton = view.findViewById(R.id.redeembutton);
                   redeemButton.setText(redeemText);
               }

               String redeemTextDesc = AppLaunch.getInstance().getPropertyOfFeature("_lwccmx3w2","_5d9rz2c9j");
               if(redeemTextDesc!=null && redeemTextDesc.length()>0){
                   TextView redeemTextView = view.findViewById(R.id.redeemdescription);
                   redeemTextView.setText(redeemTextDesc);
               }

               Button redeemButton = view.findViewById(R.id.redeembutton);
               redeemButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       ArrayList metricsList = new ArrayList();
                       metricsList.add("_3pd35snp1");
                       AppLaunch.getInstance().sendMetrics(metricsList);
                       final Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_coupon);
                       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                               getActivity());

                       // set title
                       alertDialogBuilder.setTitle("Offers!!");

                       // set dialog message
                       alertDialogBuilder
                               .setMessage("Coupon Redeemed Successfully!")
                               .setCancelable(false)
                               .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog,int id) {
                                       cardView.startAnimation(animation);
                                   }
                               });


                       // create alert dialog
                       AlertDialog alertDialog = alertDialogBuilder.create();
                       // show it
                       alertDialog.show();
                       animation.setAnimationListener(new Animation.AnimationListener() {
                           @Override
                           public void onAnimationStart(Animation animation) {

                           }

                           @Override
                           public void onAnimationEnd(Animation animation) {
                               cardView.setVisibility(View.GONE);
                           }

                           @Override
                           public void onAnimationRepeat(Animation animation) {

                           }
                       });
                   }
               });

           }else{
               cardView.setVisibility(View.GONE);
           }
        } catch (AppLaunchException e) {
            e.printStackTrace();
        }
        return view;
    }

}
