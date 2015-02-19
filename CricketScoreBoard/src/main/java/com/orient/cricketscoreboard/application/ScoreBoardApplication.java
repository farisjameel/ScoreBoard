package com.orient.cricketscoreboard.application;

import android.app.Application;
import android.util.Log;

import com.orient.cricketscoreboard.activity.MainActivity;
import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;

import java.text.ParseException;

/**
 * Created by Faris on 2/11/2015.
 */
public class ScoreBoardApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Parse SDK.
        Parse.initialize(this, "qZLgrOv954cCw2ganmE1lrIFUJHsRHYiduMOjCwq", "Jh7Plyp411sudx8wjFWqdU69GPiJ95J5PEf2iwph");

        // Specify an Activity to handle all pushes by default.
        PushService.setDefaultPushCallback(this, MainActivity.class);
    }
}
