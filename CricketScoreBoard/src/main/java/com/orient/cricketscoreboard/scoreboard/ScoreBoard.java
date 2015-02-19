package com.orient.cricketscoreboard.scoreboard;

import java.util.List;
import java.util.Locale;

import com.orient.cricketscoreboard.R;
import com.orient.cricketscoreboard.model.ScoreBoardModel;
import com.orient.cricketscoreboard.parser.ScoreBoardJsonParser;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This implementation provides multiple windows. You may extend this class or
 * use it as a reference for a basic foundation for your own windows.
 * 
 * <p>
 * Functionality includes system window decorators, moveable, resizeable,
 * hideable, closeable, and bring-to-frontable.
 * 
 * <p>
 * The persistent notification creates new windows. The hidden notifications
 * restores previously hidden windows.
 * 
 * @author Mark Wei <markwei@gmail.com>
 * 
 */
public class ScoreBoard extends StandOutWindow {

    private static String BoardTitle="Score Board";
    private static String Teams="T1 VS T2";

    private TextView firstTeamTextView,overTextView,scoreTextView,targetTextView,bowlerTextView,firstPlayerTextView,secondPlayerTextView,currentRunRateTextView,requiredRunRateTextView,requiredScoreStates;

	@Override
	public String getAppName() {
		return BoardTitle;
	}

	@Override
	public int getAppIcon() {
		return R.drawable.help;
	}

    private String getTeams(){

        return Teams;
    }
	@Override
	public String getTitle(int id) {
		return getTeams();
	}

	@Override
	public void createAndAttachView(int id, FrameLayout frame) {
		// create a new layout from body.xml
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.body, frame, true);

//		TextView idText = (TextView) view.findViewById(R.id.score);
//		idText.setText("dkjvnsfijbnfbn");
	}

	// every window is initially same size
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		return new StandOutLayoutParams(id, 500, 600,
				StandOutLayoutParams.AUTO_POSITION,
				StandOutLayoutParams.AUTO_POSITION, 100, 100);
	}

	// we want the system window decorations, we want to drag the body, we want
	// the ability to hide windows, and we want to tap the window to bring to
	// front
	@Override
	public int getFlags(int id) {
		return StandOutFlags.FLAG_DECORATION_SYSTEM
                | StandOutFlags.FLAG_BODY_MOVE_ENABLE
                | StandOutFlags.FLAG_WINDOW_HIDE_ENABLE
                | StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TAP
                | StandOutFlags.FLAG_WINDOW_EDGE_LIMITS_ENABLE
                | StandOutFlags.FLAG_WINDOW_PINCH_RESIZE_ENABLE;
	}

	@Override
	public String getPersistentNotificationTitle(int id) {
		return getAppName() + " Running";
	}

	@Override
	public String getPersistentNotificationMessage(int id) {
		return "Click to view " + getAppName();
	}

	// return an Intent that creates a new ScoreBoard
	@Override
	public Intent getPersistentNotificationIntent(int id) {
		return StandOutWindow.getShowIntent(this, getClass(), id);
	}

	@Override
	public int getHiddenIcon() {
		return android.R.drawable.ic_menu_info_details;
	}

	@Override
	public String getHiddenNotificationTitle(int id) {
		return getAppName() + " Hidden";
	}

	@Override
	public String getHiddenNotificationMessage(int id) {
		return "Click to restore " + getAppName();
	}

	// return an Intent that restores the ScoreBoard
	@Override
	public Intent getHiddenNotificationIntent(int id) {
		return StandOutWindow.getShowIntent(this, getClass(), id);
	}

	@Override
	public Animation getShowAnimation(int id) {
		if (isExistingId(id)) {
			// restore
			return AnimationUtils.loadAnimation(this,
					android.R.anim.slide_in_left);
		} else {
			// show
			return super.getShowAnimation(id);
		}
	}

	@Override
	public Animation getHideAnimation(int id) {
		return AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
	}

	@Override
	public List<DropDownListItem> getDropDownItems(int id) {

		return null;
	}

	@Override
	public void onReceiveData(int id, int requestCode, Bundle data,
			Class<? extends StandOutWindow> fromCls, int fromId) {
		// receive data from WidgetsWindow's button press
		// to show off the data sending framework
		switch (requestCode) {
			case DataSender.DATA_CHANGED_TEXT:
				Window window = getWindow(id);
				if (window == null) {
					String errorText = String.format(Locale.US,
							"Received updated score but Score Board is not open.",
							getAppName(), id);
					Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show();
					return;
				}
                String json = data.getString("data");
                ScoreBoardJsonParser parser = new ScoreBoardJsonParser();

                ScoreBoardModel scoreBoardModel=parser.getScoreModel(json);



                if(scoreBoardModel!=null||scoreBoardModel.getFirstTeam()==null) {
//                    TextView status = (TextView) window.findViewById(R.id.score);
//                    status.setTextSize(20);
//                    status.setText("score: scoreBoardModel.getScore()");

                    try {
                        BoardTitle = scoreBoardModel.getFirstTeam().toUpperCase() + " VS " + scoreBoardModel.getSecondTeam().toUpperCase();
                        setTitle(0, BoardTitle);
                        firstTeamTextView = (TextView) window.findViewById(R.id.firstTeam);
                        overTextView = (TextView) window.findViewById(R.id.over);
                        firstPlayerTextView = (TextView) window.findViewById(R.id.firstPlayer);
                        secondPlayerTextView = (TextView) window.findViewById(R.id.secondPlayer);
                        scoreTextView = (TextView) window.findViewById(R.id.score);
                        targetTextView = (TextView) window.findViewById(R.id.target);
                        bowlerTextView = (TextView) window.findViewById(R.id.bowler);
                        currentRunRateTextView = (TextView) window.findViewById(R.id.currentRunRate);
                        requiredRunRateTextView = (TextView) window.findViewById(R.id.requiredRunRate);
                        requiredScoreStates = (TextView) window.findViewById(R.id.requiredScoreStats);

                        float crr = Float.parseFloat(scoreBoardModel.getScore())/Float.parseFloat(scoreBoardModel.getOver());
                        String crrString = String.format("%.2f", crr);


//                        firstTeamTextView.setText(scoreBoardModel.getSecondTeam().toUpperCase() + "vs" + scoreBoardModel.getFirstTeam().toUpperCase() + ": ");
                        overTextView.setText("Overs: " + scoreBoardModel.getOver());
                        scoreTextView.setText(scoreBoardModel.getScore()+"/"+scoreBoardModel.getOut());
                        firstPlayerTextView.setText(scoreBoardModel.getFirstPlayer() + "*");
                        secondPlayerTextView.setText(scoreBoardModel.getSecondPlayer());
                        bowlerTextView.setText(scoreBoardModel.getBowler());
                        currentRunRateTextView.setText("CRR: "+crrString);
                        if(scoreBoardModel.getTarget().equals(""))
                            targetTextView.setVisibility(View.GONE);
                        else {
                            targetTextView.setVisibility(View.VISIBLE);
                            targetTextView.setText("Target: " + scoreBoardModel.getTarget());
                        }
                        if(scoreBoardModel.getRequiredRuns().equals("")||scoreBoardModel.getRemainingBalls().equals(""))
                            requiredRunRateTextView.setVisibility(View.GONE);
                        else {
                            float rrr = Float.parseFloat(scoreBoardModel.getRequiredRuns())/(Float.parseFloat(scoreBoardModel.getRemainingBalls())/6);
                            String rrrString = String.format("%.2f", rrr);
                            requiredRunRateTextView.setVisibility(View.VISIBLE);
                            requiredRunRateTextView.setText("RRR: " + rrrString);
                        }
                        if(scoreBoardModel.getRequiredRuns().equals("")||scoreBoardModel.getRemainingBalls().equals(""))
                            requiredScoreStates.setVisibility(View.GONE);
                        else {
                            requiredScoreStates.setVisibility(View.VISIBLE);
                            requiredScoreStates.setText("need " + scoreBoardModel.getRequiredRuns()+" runs on "+scoreBoardModel.getRemainingBalls()+" balls");
                        }
                    }catch (Exception e){

                    }

                }
				break;
			default:
				Log.d("ScoreBoard", "Unexpected data received.");
				break;
		}
	}
}
