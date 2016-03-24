package uk.co.swa.swapp.model;

/**
 * Created by oliver on 14/03/2016.
 */
public class Team extends SwaObject implements CompetitionEntrant {

    private University university;
    private int teamNumber;

    public Team(long appID, University university, int teamNumber) {
        super(appID);
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

    public University getUniversity() {
        return university;
    }

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
