package uk.co.swa.swapp.model;

public class Round implements SwaObject {

    long appID;
    Competition competition;
    int roundNumber;
    int duration;
    int maxCompetitors;
    boolean finalRound;
//    List<Heat> heatList;

    public Round(long appID,
                 Competition competition,
                 int roundNumber,
                 int duration,
                 int maxCompetitors,
//                 List<Heat> heats,
                 boolean finalRound) {
        this.appID = appID;
        this.competition = competition;
        this.duration = duration;
        this.maxCompetitors = maxCompetitors;
        this.roundNumber = roundNumber;
//        this.heatList = heats;
        this.finalRound = finalRound;
    }

    public Round(Competition competition,
                 int roundNumber,
                 int duration,
                 int maxCompetitors,
                 boolean finalRound) {
        this(-1, competition, roundNumber, duration, maxCompetitors, finalRound);
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

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
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

    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    public void setMaxCompetitors(int maxCompetitors) {
        this.maxCompetitors = maxCompetitors;
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
