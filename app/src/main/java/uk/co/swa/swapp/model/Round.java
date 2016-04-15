package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.List;

public class Round implements SwaObject {

    long appID;
    Competition competition;
    int roundNumber;
    int duration;
    boolean finalRound;
//    List<Heat> heatList;

    public Round(long appID,
                 Competition competition,
                 int roundNumber,
                 int duration,
//                 List<Heat> heats,
                 boolean finalRound) {
        this.appID = appID;
        this.competition = competition;
        this.duration = duration;
        this.roundNumber = roundNumber;
//        this.heatList = heats;
        this.finalRound = finalRound;
    }

    public Round(Competition competition,
                 int roundNumber,
                 int duration,
                 List<Heat> heats,
                 boolean finalRound) {
        this(-1, competition, duration, roundNumber, /*heats,*/ finalRound);
    }

    public Round(Competition competition,
                 int roundNumber,
                 int duration,
                 List<Heat> heats) {
        this(-1, competition, duration, roundNumber, /*heats,*/ false);
    }

    public Round(Competition competition, int roundNumber, int duration, boolean finalRound) {
        this(-1, competition, roundNumber, duration, /*new ArrayList<Heat>(),*/ finalRound);
    }

    public Round(Competition competition, int roundNumber, int duration) {
        this(-1, competition, roundNumber, duration, /*new ArrayList<Heat>(),*/ false);
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

//    public List<Heat> getHeats() {
//        return heatList;
//    }

//    public void setHeatList(List<Heat> heatList) {
//        this.heatList = heatList;
//    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isFinalRound() {
        return finalRound;
    }

    public void setFinalRound(boolean finalRound) {
        this.finalRound = finalRound;
    }

    @Override
    public String toString() {
        return "Round " + roundNumber + (finalRound ? " - Final" : "");
    }
}
