package uk.co.swa.swapp.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.University;

/**
 * Created by adam on 23/03/2016.
 */
public class MockMainStore implements MainStore {

    Map<Integer, CompetitionType> competitionTypes;
    Map<Integer, University> universities;
    Map<Integer, Member> members;
    Map<Integer, Event> events;
    Map<Integer, List<Integer>> eventAttendees;

    public MockMainStore() {
        this.competitionTypes = new HashMap<>();
        this.universities = new HashMap<>();
        this.members = new HashMap<>();
        this.events = new HashMap<>();
        this.eventAttendees = new HashMap<>();

        this.competitionTypes.put(1, new CompetitionType(1, "Beginner"));
        this.competitionTypes.put(2, new CompetitionType(1, "Intermediate"));
        this.competitionTypes.put(3, new CompetitionType(1, "Advanced"));
        this.competitionTypes.put(4, new CompetitionType(1, "Freestyle"));
        this.competitionTypes.put(5, new CompetitionType(1, "Team"));
        this.competitionTypes.put(6, new CompetitionType(1, "Wave"));

        this.universities.put(1, new University(1, "Bangor"));
        this.universities.put(2, new University(1, "Birmingham"));
        this.universities.put(3, new University(1, "Bristol"));
        this.universities.put(4, new University(1, "Cardiff"));
        this.universities.put(5, new University(1, "Exeter"));
        this.universities.put(6, new University(1, "Imperial"));
        this.universities.put(7, new University(1, "Leeds"));
        this.universities.put(8, new University(1, "Liverpool"));
        this.universities.put(9, new University(1, "Newcastle"));
        this.universities.put(10, new University(10, "Nottingham"));
        this.universities.put(11, new University(11, "Plymouth"));
        this.universities.put(12, new University(12, "Southampton"));
        this.universities.put(13, new University(13, "Southampton Solent"));
        this.universities.put(14, new University(14, "UEA"));
        this.universities.put(15, new University(15, "UWE"));

        this.members.put(1, new Member(1, "Adam Franklin", this.universities.get(15)));
        this.members.put(2, new Member(2, "Ollie Johnson", this.universities.get(15)));
        this.members.put(3, new Member(3, "Roisin Irish", this.universities.get(15)));
        this.members.put(4, new Member(4, "Kate Simpson", this.universities.get(15)));
        this.members.put(5, new Member(5, "Ben Jones", this.universities.get(15)));
        this.members.put(6, new Member(6, "Jaayme", this.universities.get(15)));
        this.members.put(7, new Member(7, "Olives Are-Cool", this.universities.get(8)));
        this.members.put(8, new Member(8, "Jane Jenny Janson", this.universities.get(8)));
        this.members.put(9, new Member(9, "Larry Page", this.universities.get(3)));
        this.members.put(10, new Member(10, "Louis LaLa", this.universities.get(3)));
        this.members.put(11, new Member(11, "Ben Smith", this.universities.get(12)));
        this.members.put(12, new Member(12, "Jack DooDar", this.universities.get(5)));
        this.members.put(13, new Member(13, "Adam Small", this.universities.get(5)));
        this.members.put(14, new Member(14, "Ann Bar", this.universities.get(5)));

        this.events.put(1, new Event(1, "Disney Presents Cardiff Wave", new Date()));
        this.events.put(2, new Event(2, "Nottingham Pondlife", new Date()));
        this.events.put(3, new Event(3, "BrUWE Wet Dreams", new Date()));
        this.events.put(4, new Event(4, "Bangor", new Date()));
        this.events.put(5, new Event(5, "Up the Brum!", new Date()));

        this.eventAttendees.put(1, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        this.eventAttendees.put(2, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 8, 9, 10, 11)));
        this.eventAttendees.put(3, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14)));
    }

    @Override
    public Map<Integer, Event> getEvents() {
        return this.events;
    }

    @Override
    public Map<Integer, CompetitionType> getCompetitionTypes() {
        return this.competitionTypes;
    }

    @Override
    public Map<Integer, University> getUniversities() {
        return this.universities;
    }

    @Override
    public Map<Integer, Member> getMembers() {
        return this.members;
    }

    @Override
    public Map<Integer, List<Integer>> getEventAttendees(Event event) {
        return this.eventAttendees;
    }

}
