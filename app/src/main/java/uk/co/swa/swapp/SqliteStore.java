package uk.co.swa.swapp;

import java.util.List;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Season;
import uk.co.swa.swapp.model.University;

public class SQLiteStore implements Store {
    @Override
    public List<Season> getSeasons() {
        return null;
    }

    @Override
    public Season getSeason(int id) {
        return null;
    }

    @Override
    public List<Event> getEvents(Season season) {
        return null;
    }

    @Override
    public Event getEvent(int id) {
        return null;
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return null;
    }

    @Override
    public CompetitionType getCompetitionType(int id) {
        return null;
    }

    @Override
    public List<Competition> getCompetitions(Event event) {
        return null;
    }

    @Override
    public Competition getCompetition(int id) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public Member getMember(int id) {
        return null;
    }

    @Override
    public List<University> getUniversities() {
        return null;
    }

    @Override
    public University getUniversity(int id) {
        return null;
    }

    @Override
    public List<Member> getEventAttendees(Event event) {
        return null;
    }
}
