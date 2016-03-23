package uk.co.swa.swapp.store;

import java.util.List;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.IndividualCompetition;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Season;
import uk.co.swa.swapp.model.Team;
import uk.co.swa.swapp.model.TeamCompetition;
import uk.co.swa.swapp.model.University;

public class SQLiteStore implements Store {
    @Override
    public List<Season> getSeasons() {
        return null;
    }

    @Override
    public Season getSeason(long id) {
        return null;
    }

    @Override
    public List<Event> getEvents(Season season) {
        return null;
    }

    @Override
    public Event getEvent(long id) {
        return null;
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return null;
    }

    @Override
    public CompetitionType getCompetitionType(long id) {
        return null;
    }

    @Override
    public List<Competition> getCompetitions(Event event) {
        return null;
    }

    @Override
    public Competition getCompetition(long id) {
        return null;
    }

    @Override
    public List<Member> getIndividualCompetitors(IndividualCompetition competition) {
        return null;
    }

    @Override
    public List<Team> getTeamCompetitors(TeamCompetition competition) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public Member getMember(long id) {
        return null;
    }

    @Override
    public List<University> getUniversities() {
        return null;
    }

    @Override
    public University getUniversity(long id) {
        return null;
    }

    @Override
    public List<Member> getEventAttendees(Event event) {
        return null;
    }
}
