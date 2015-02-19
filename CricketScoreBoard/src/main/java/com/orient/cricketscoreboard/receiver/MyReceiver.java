package com.orient.cricketscoreboard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseBroadcastReceiver;

/**
 * Created by Faris on 2/12/2015.
 */
public class MyReceiver extends BroadcastReceiver{

    private String action;
    @Override
    public void onReceive(Context context, Intent intent) {
//        super.onReceive(context, intent);
        action = intent.getAction();
        Log.v("MyReceiver", action);
    }

}
