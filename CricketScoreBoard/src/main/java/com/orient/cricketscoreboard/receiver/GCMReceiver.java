package com.orient.cricketscoreboard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.orient.cricketscoreboard.scoreboard.DataSender;
import com.orient.cricketscoreboard.scoreboard.ScoreBoard;
import com.parse.PushService;

import org.json.JSONException;
import org.json.JSONObject;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.ui.Window;

/**
 * Created by Faris on 2/12/2015.
 */
public class GCMReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        Intent sendData = new Intent(context,DataSender.class);
        sendData.putExtra("json", extras.getString("data"));
        context.startService(sendData);
    }

}
