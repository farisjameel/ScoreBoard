package com.orient.cricketscoreboard.parser;

import com.orient.cricketscoreboard.model.ScoreBoardModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * parse score json string into score model
 */
public class ScoreBoardJsonParser {

    private ScoreBoardModel scoreModel = null;

    public ScoreBoardModel getScoreModel(String jsonString){
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                scoreModel = new ScoreBoardModel();
                scoreModel.setFirstTeam(jsonObject.getString("first_team"));
                scoreModel.setTarget(jsonObject.getString("target"));
                scoreModel.setSecondTeam(jsonObject.getString("second_team"));
                scoreModel.setScore(jsonObject.getString("score"));
                scoreModel.setOut(jsonObject.getString("out"));
                scoreModel.setOver(jsonObject.getString("over"));
                scoreModel.setFirstPlayer(jsonObject.getString("first_player"));
                scoreModel.setSecondPlayer(jsonObject.getString("second_player"));
                scoreModel.setBowler(jsonObject.getString("bowler"));
                scoreModel.setRemainingBalls(jsonObject.getString("remaining_balls"));
                scoreModel.setRequiredRuns(jsonObject.getString("required_runs"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return scoreModel;
    }

}
