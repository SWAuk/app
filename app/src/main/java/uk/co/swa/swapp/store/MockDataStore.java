package uk.co.swa.swapp.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.IndividualCompetition;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Season;
import uk.co.swa.swapp.model.Team;
import uk.co.swa.swapp.model.TeamCompetition;
import uk.co.swa.swapp.model.University;

/**
 * Created by oliver on 20/03/2016.
 */
public class MockDataStore implements Store {
    List<CompetitionType> competitionTypeList;
    List<University> universityList;
    List<Member> memberList;

    List<Season> seasonList;
    Map<Season, List<Event>> eventMap;
    Map<Event, List<Competition>> competitionMap;
    Map<Competition, List<Member>> individualCompetitorMap;

    public MockDataStore() {
        this.competitionTypeList = new ArrayList<>();
        this.universityList = new ArrayList<>();
        this.memberList = new ArrayList<>();

        this.populateCompetitionTypeList();
        this.populateUniversityList();
        this.populateMemberList();

        this.seasonList = new ArrayList<>();
        this.eventMap = new HashMap<>();
        this.competitionMap = new HashMap<>();
        individualCompetitorMap = new HashMap<>();

        this.populateSeasonList();
        this.populateEventMap();
        this.populateCompetitionMap();
    }

    @Override
    public List<Season> getSeasons() {
        return this.seasonList;
    }

    @Override
    public Season getSeason(long id) {
        for (Season season : this.seasonList) {
            if (season.getAppID() == id) {
                return season;
            }
        }

        return this.seasonList.get((int)id);
    }

    @Override
    public List<Event> getEvents(Season season) {
        return this.eventMap.get(season);
    }

    @Override
    public Event getEvent(long id) {
        for (List<Event> events : this.eventMap.values()){
            for (Event event : events) {
                if (event.getAppID() == id) {
                    return event;
                }
            }
        }

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
        return this.competitionMap.get(event);
    }

    @Override
    public Competition getCompetition(long id) {
        for (List<Competition> competitionList : this.competitionMap.values()){
            for (Competition competition : competitionList) {
                if (competition.getAppID() == id) {
                    return competition;
                }
            }
        }

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

    private void populateCompetitionTypeList() {
        this.competitionTypeList.add(new CompetitionType(1, "Beginner"));
        this.competitionTypeList.add(new CompetitionType(2, "Intermediate"));
        this.competitionTypeList.add(new CompetitionType(3, "Advanced"));
        this.competitionTypeList.add(new CompetitionType(4, "Freestyle"));
        this.competitionTypeList.add(new CompetitionType(5, "Team"));
        this.competitionTypeList.add(new CompetitionType(6, "Wave"));
    }

    private void populateUniversityList() {
        this.universityList.add(new University(1, "Bangor"));
        this.universityList.add(new University(2, "Birmingham"));
        this.universityList.add(new University(3, "Bristol"));
        this.universityList.add(new University(4, "Cardiff"));
        this.universityList.add(new University(5, "Exeter"));
        this.universityList.add(new University(6, "Imperial"));
        this.universityList.add(new University(7, "Leeds"));
        this.universityList.add(new University(8, "Liverpool"));
        this.universityList.add(new University(9, "Newcastle"));
        this.universityList.add(new University(10, "Nottingham"));
        this.universityList.add(new University(11, "Plymouth"));
        this.universityList.add(new University(12, "Southampton"));
        this.universityList.add(new University(13, "Southampton Solent"));
        this.universityList.add(new University(14, "UEA"));
        this.universityList.add(new University(15, "UWE"));
    }

    private void populateMemberList() {
        this.memberList.add(new Member(1, "Adam Franklin", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Ollie Johnson", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Roisin Irish", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Kate Simpson", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Ben Jones", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Jaayme", this.getUniversity(15)));
        this.memberList.add(new Member(1, "Olives Are-Cool", this.getUniversity(8)));
        this.memberList.add(new Member(1, "Jane Jenny Janson", this.getUniversity(8)));
        this.memberList.add(new Member(1, "Larry Page", this.getUniversity(3)));
        this.memberList.add(new Member(1, "Louis LaLa", this.getUniversity(3)));
        this.memberList.add(new Member(1, "Ben Smith", this.getUniversity(12)));
        this.memberList.add(new Member(1, "Jack DooDar", this.getUniversity(5)));
        this.memberList.add(new Member(1, "Adam Small", this.getUniversity(5)));
        this.memberList.add(new Member(1, "Ann Bar", this.getUniversity(5)));
    }

    private void populateSeasonList() {
        this.seasonList.add(new Season(1, "2015 - 2016"));
        this.seasonList.add(new Season(2, "2014 - 2015"));

        // foreach of the seasons create an empty ArrayList in the eventMap
        for (Season season : this.seasonList) {
            this.eventMap.put(season, new ArrayList<Event>());
        }
    }

    private void populateEventMap() {
        // Season 1 - 2015/16
        this.eventMap.get(this.seasonList.get(0)).add(new Event(1, "Disney Presents Cardiff Wave"));
        this.eventMap.get(this.seasonList.get(0)).add(new Event(2, "Nottingham Pondlife"));
        this.eventMap.get(this.seasonList.get(0)).add(new Event(3, "BrUWE Wet Dreams"));
        this.eventMap.get(this.seasonList.get(0)).add(new Event(4, "Bangor"));
        this.eventMap.get(this.seasonList.get(0)).add(new Event(5, "Up the Brum!"));

        // Season 2 - 2014/15
        this.eventMap.get(this.seasonList.get(1)).add(new Event(5, "Up the Brum!"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(6, "BUCS Nationals"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(7, "Nottingham"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(8, "Cardiff"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(9, "Bangor"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(10, "PlymEx"));
        this.eventMap.get(this.seasonList.get(1)).add(new Event(11, "BrUWE"));

        // foreach of the events add an empty ArrayList to the competitionMap
        for (List<Event> events : this.eventMap.values()) {
            for (Event event : events) {
                this.competitionMap.put(event, new ArrayList<Competition>());
            }
        }

    }

    private void populateCompetitionMap() {
        // create a list of competitions
        ArrayList<Competition> competitions = new ArrayList<>();
        competitions.add(new IndividualCompetition(1, this.competitionTypeList.get(1)));

        // add the competitions to Season 15/16 - Event "Disney Presents Cardiff Wave"
        this.competitionMap.get(this.eventMap.get(this.seasonList.get(0)).get(0)).addAll(competitions);

        // foreach of the competitions add an empty ArrayList to the ???Map
        for (List<Competition> competitionList : this.competitionMap.values()) {
            for (Competition competition : competitionList) {
                // TODO: How to differentiate between individuals and teams?
                this.individualCompetitorMap.put(competition, new ArrayList<Member>());
            }
        }

    }
}
