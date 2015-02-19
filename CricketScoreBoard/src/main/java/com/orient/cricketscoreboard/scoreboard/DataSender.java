package com.orient.cricketscoreboard.scoreboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.ui.Window;

/**
 * Created by Phaedra on 2/13/2015.
 */
public class DataSender extends StandOutWindow {

    public static final int DATA_CHANGED_TEXT = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String json = intent.getStringExtra("json");

        Bundle data = new Bundle();
        data.putString("data",json);
        sendData(0, ScoreBoard.class, DEFAULT_ID, DATA_CHANGED_TEXT,
                data);
        return START_NOT_STICKY;
    }

    @Override
    public String getAppName() {
        return null;
    }

    @Override
    public int getAppIcon() {
        return 0;
    }

    @Override
    public void createAndAttachView(int id, FrameLayout frame) {

    }

    @Override
    public StandOutLayoutParams getParams(int id, Window window) {
        return null;
    }
}
