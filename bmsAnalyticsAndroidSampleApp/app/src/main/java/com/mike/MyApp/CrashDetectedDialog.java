package com.mike.MyApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;



/**
 * Created by rott on 2/17/16.
 */
public class CrashDetectedDialog extends AlertDialog {

    private static class PositiveButtonClick implements OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity.sendLogsAndAnalytics();
        }
    }

    private static class NegativeButtonClick implements OnClickListener {

        private Activity activity;

        public NegativeButtonClick(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, "You are not nice.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public CrashDetectedDialog(Activity activity) {
        super(activity);
        this.setTitle("Crash detected!");
        this.setMessage("Your app ended with a crash last time you used it.  Would you like to help make it better by sending us logs?");
        this.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I love helping!", new PositiveButtonClick());
        this.setButton(AlertDialog.BUTTON_NEGATIVE, "No, you stink!", new NegativeButtonClick(activity));
    }

}


