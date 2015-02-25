package com.orient.cricketscoreboard.activity;

import wei.mark.standout.StandOutWindow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.orient.cricketscoreboard.gcm.AlertDialogManager;
import com.orient.cricketscoreboard.gcm.CommonUtilities;
import com.orient.cricketscoreboard.gcm.ConnectionDetector;
import com.orient.cricketscoreboard.gcm.ServerUtilities;
import com.orient.cricketscoreboard.gcm.WakeLocker;
import com.orient.cricketscoreboard.scoreboard.ScoreBoard;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    AlertDialogManager alert = new AlertDialogManager();
    ConnectionDetector cd;

    Context context;
    String regId;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        cd = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(MainActivity.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        regId = GCMRegistrar.getRegistrationId(this);

        Log.v(TAG, regId);
        if (regId.equals("")) {
            GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
        } else {
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                Log.v(TAG, "Already registered with GCM");
            } else {
                new RegisterTask().execute(null, null, null);
            }
        }


//		StandOutWindow.closeAll(this, SimpleWindow.class);
            StandOutWindow.closeAll(this, ScoreBoard.class);

            // show a ScoreBoard, SimpleWindow

//		StandOutWindow
//				.show(this, SimpleWindow.class, StandOutWindow.DEFAULT_ID);
            StandOutWindow.show(this, ScoreBoard.class, StandOutWindow.DEFAULT_ID);
//		StandOutWindow.show(this, WidgetsWindow.class,
//				StandOutWindow.DEFAULT_ID);

            // show a MostBasicWindow. It is commented out because it does not
            // support closing.

		/*
		 * StandOutWindow.show(this, StandOutMostBasicWindow.class,
		 * StandOutWindow.DEFAULT_ID);
		 */

            finish();

    }

        class RegisterTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... params) {
                // Register on our server
                // On server creates a new user
                ServerUtilities.register(context, "", "", regId);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
            }

        };

}