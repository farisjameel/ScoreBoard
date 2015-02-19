package com.orient.cricketscoreboard.model;

/**
 * Created by Faris on 2/12/2015.
 */
public class ScoreBoardModel {
    private String firstTeam;
    private String secondTeam;
    private String score;
    private String over;
    private String firstPlayer;
    private String secondPlayer;
    private String bowler;
    private String target;
    private String out;
    private String requiredRuns;
    private String remainingBalls;

    public String getRemainingBalls() {
        return remainingBalls;
    }

    public void setRemainingBalls(String remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    public String getRequiredRuns() {
        return requiredRuns;
    }

    public void setRequiredRuns(String requiredRuns) {
        this.requiredRuns = requiredRuns;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(String firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }
}
