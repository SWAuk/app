package uk.co.swa.swapp.model;

/**
 * Created by oliver on 14/03/2016.
 */
public class Team {
    private int teamId;
    private University university;
    private int teamNumber;

    public Team(int teamId, University university, int teamNumber) {
        this.teamId = teamId;
        this.university = university;
        this.teamNumber = teamNumber;
    }

    public Team(University university, int teamNumber) {
        this.teamId = 0;
        this.university = university;
        this.teamNumber = teamNumber;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
        return this.university.toString() + " " + this.teamNumber;
    }
}
