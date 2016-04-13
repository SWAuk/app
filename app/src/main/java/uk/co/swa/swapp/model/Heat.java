package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.List;

public class Heat implements SwaObject {

    long appID;
    Competition competition;
    List<? extends CompetitionEntrant> competitionEntrantList;
    int duration;
    int roundNumber;
    boolean finalHeat;

    public Heat(long appID,
                Competition competition,
                int duration,
                int roundNumber,
                boolean finalHeat,
                List<? extends CompetitionEntrant> competitionEntrants) {
        this.appID = appID;
        this.competition = competition;
        this.duration = duration;
        this.roundNumber = roundNumber;
        this.finalHeat = finalHeat;
        this.competitionEntrantList = competitionEntrants;
    }

    public Heat (Competition competition,
                 int duration,
                 int roundNumber,
                 boolean finalHeat,
                 List<? extends CompetitionEntrant> competitionEntrants) {
        this(-1, competition, duration, roundNumber, finalHeat, competitionEntrants);
    }

    public Heat (Competition competition, int duration, int roundNumber, boolean finalHeat) {
        this(-1, competition, duration, roundNumber, finalHeat, new ArrayList<CompetitionEntrant>());
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

    public List<? extends CompetitionEntrant> getCompetitionEntrants() {
        return competitionEntrantList;
    }

    public void setCompetitionEntrants(List<? extends CompetitionEntrant> competitionEntrantList) {
        this.competitionEntrantList = competitionEntrantList;
    }

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

    public boolean isFinalHeat() {
        return finalHeat;
    }

    public void setFinalHeat(boolean finalHeat) {
        this.finalHeat = finalHeat;
    }

    @Override
    public String toString() {
        return "Heat " + roundNumber + (finalHeat ? " - Final" : "");
    }
}
