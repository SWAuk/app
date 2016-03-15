package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.swa.swapp.GodScrappyDog;

/**
 * Created by oliver on 14/03/2016.
 */
public class TeamCompetition extends Competition {

    private List<Team> teamList;

    public TeamCompetition(int competitionId, CompetitionType competitionType,
                           List<Heat> heats, List<Team> teams) {

        super(competitionId, competitionType, heats);
        this.teamList = teams;

    }

    public TeamCompetition(CompetitionType competitionType) {
        super(competitionType);
        this.teamList = new ArrayList<>();
    }

    public TeamCompetition(int competitionId, CompetitionType competitionType) {
        super(competitionId, competitionType);
        this.teamList = new ArrayList<>();
    }

    public TeamCompetition(CompetitionType competitionType, List<Team> teams) {

        super(competitionType);
        this.teamList = teams;
    }

    public Team getTeam(int index) {
        return this.teamList.get(index);
    }

    public List<Team> getAllTeams() {
        return this.teamList;
    }

    public boolean addTeam(Team... team) {
        return this.teamList.addAll(Arrays.asList(team));
    }

    public boolean addTeams(List<Team> teams) {
        return this.teamList.addAll(teams);
    }

    public boolean removeTeam(Team team) {
        return teamList.remove(team);
    }

    public Team removeTeam(int index) {
        return teamList.remove(index);
    }

    public boolean removeTeams(ArrayList<Competition> teams) {
        return teamList.removeAll(teams);
    }

}
