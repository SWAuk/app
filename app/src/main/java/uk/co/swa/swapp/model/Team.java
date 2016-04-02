package uk.co.swa.swapp.model;

public class Team implements CompetitionEntrant {

    private long appID;
    private University university;
    private int teamNumber;

    public Team(long appID, University university, int teamNumber) {
        this.appID = appID;
        this.university = university;
        this.teamNumber = teamNumber;
    }

    public Team(University university, int teamNumber) {
        this(-1, university, teamNumber);
    }

    @Override
    public String getName() {
        return this.university + " " + this.teamNumber;
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    @Override
    public University getUniversity() {
        return university;
    }

    @Override
    public void setUniversity(University university) {
        this.university = university;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
