package uk.co.swa.swapp.store;

import java.util.List;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionEntrant;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Heat;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.University;

public class SQLiteStore implements AppStore {

    @Override
    public Event getEvent(long id) {
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    @Override
    public List<Event> getEvents(int limit) {
        return null;
    }

    @Override
    public CompetitionType getCompetitionType(long id) {
        return null;
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return null;
    }

    @Override
    public Competition getCompetition(long id) {
        return null;
    }

    @Override
    public List<Competition> getCompetitions(Event event) {
        return null;
    }

    @Override
    public CompetitionEntrant getCompetitionEntrant(long id) {
        return null;
    }

    @Override
    public List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition) {
        return null;
    }

    @Override
    public boolean storeCompetitionEntrant(Competition competition, CompetitionEntrant competitionEntrant) {
        return false;
    }

    @Override
    public boolean storeCompetitionEntrants(Competition competition, List<CompetitionEntrant> competitionEntrants) {
        return false;
    }

    @Override
    public Heat getCompetitionHeat(long id) {
        return null;
    }

    @Override
    public List<Heat> getCompetitionHeats(Competition competition) {
        return null;
    }

    @Override
    public Member getMember(long id) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public University getUniversity(long id) {
        return null;
    }

    @Override
    public List<University> getUniversities() {
        return null;
    }

    @Override
    public List<Member> getEventAttendees(Event event) {
        return null;
    }

}
