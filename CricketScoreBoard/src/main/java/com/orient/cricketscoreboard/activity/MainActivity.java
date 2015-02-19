package com.orient.cricketscoreboard.activity;

import wei.mark.standout.StandOutWindow;

import android.app.Activity;
import android.os.Bundle;

import com.orient.cricketscoreboard.scoreboard.ScoreBoard;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
}