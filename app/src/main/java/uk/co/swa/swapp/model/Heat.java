package uk.co.swa.swapp.model;

import java.util.List;

public class Heat implements SwaObject {

    long appID;
    Competition competition;
    Round round;
    int heatNumber;
    List<? extends CompetitionEntrant> competitionEntrantList;

    public Heat(long appID,
                Competition competition,
                Round round,
                int heatNumber,
                List<? extends CompetitionEntrant> competitionEntrants) {
        this.appID = appID;
        this.competition = competition;
        this.round = round;
        this.heatNumber = heatNumber;
        this.competitionEntrantList = competitionEntrants;
    }

    public Heat (Competition competition,
                 Round round,
                 int heatNumber,
                 List<? extends CompetitionEntrant> competitionEntrants) {
        this(-1, competition, round, heatNumber, competitionEntrants);
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

    public int getHeatNumber() {
        return heatNumber;
    }

    public void setHeatNumber(int heatNumber) {
        this.heatNumber = heatNumber;
    }

    @Override
    public String toString() {
        return "Heat " + heatNumber;
    }
}
