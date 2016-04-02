package uk.co.swa.swapp.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionEntrant;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Heat;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Team;
import uk.co.swa.swapp.model.University;

/**
 * Created by oliver on 20/03/2016.
 */
public class MockAppStore implements AppStore {

    List<CompetitionType> competitionTypeList;
    List<University> universityList;
    List<Member> memberList;
    List<Team> teamList;

    List<Event> eventList;
    Map<Event, List<Competition>> competitionMap;
    Map<Competition, List<CompetitionEntrant>> competitorMap;

    public MockAppStore() {
        this.competitionTypeList = new ArrayList<>();
        this.universityList = new ArrayList<>();
        this.memberList = new ArrayList<>();
        this.teamList = new ArrayList<>();

        this.populateCompetitionTypeList();
        this.populateUniversityList();
        this.populateMemberList();
        this.populateTeamList();

        this.eventList = new ArrayList<>();
        this.competitionMap = new HashMap<>();
        this.competitorMap = new HashMap<>();

        this.populateEventList();
        this.populateCompetitionMap();
        this.populateCompetitionEntrantMap();
    }

    @Override
    public List<Event> getEvents() {
        return this.eventList;
    }

    @Override
    public List<Event> getEvents(int limit) {

        int size = this.eventList.size();

        if (limit > size) {
            return this.eventList;
        } else {
            return this.eventList.subList(size - limit, size);
        }
    }

    @Override
    public Event getEvent(long id) {
        for (Event event : this.eventList) {
            if (event.getAppID() == id) {
                return event;
            }
        }

        return null;
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return this.competitionTypeList;
    }

    @Override
    public CompetitionType getCompetitionType(long id) {
        for (CompetitionType competitionType : this.competitionTypeList) {
            if (competitionType.getAppID() == id) {
                return competitionType;
            }
        }

        return null;
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
    public List<Competition> getCompetitions(Event event) {
        return this.competitionMap.get(event);
    }

    @Override
    public CompetitionEntrant getCompetitionEntrant(long id) {
        for (List<CompetitionEntrant> competitionEntrantList : this.competitorMap.values()){
            for (CompetitionEntrant competitionEntrant : competitionEntrantList) {
                if (competitionEntrant.getAppID() == id) {
                    return competitionEntrant;
                }
            }
        }

        return null;
    }

    @Override
    public List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition) {
        return this.competitorMap.get(competition);
    }

    @Override
    public boolean storeCompetitionEntrant(Competition competition,
                                           CompetitionEntrant competitionEntrant) {
        return this.competitorMap.get(competition).add(competitionEntrant);
    }

    @Override
    public boolean storeCompetitionEntrants(Competition competition,
                                            List<CompetitionEntrant> competitionEntrants) {
        return this.competitorMap.get(competition).addAll(competitionEntrants);
    }

    @Override
    public List<Heat> getCompetitionHeats(Competition competition) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return this.memberList;
    }

    @Override
    public Member getMember(long id) {
        return null;
    }

    @Override
    public List<University> getUniversities() {
        return this.universityList;
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
        this.memberList.add(new Member(2, "Ollie Johnson", this.getUniversity(15)));
        this.memberList.add(new Member(3, "Roisin Irish", this.getUniversity(15)));
        this.memberList.add(new Member(4, "Kate Simpson", this.getUniversity(15)));
        this.memberList.add(new Member(5, "Ben Jones", this.getUniversity(15)));
        this.memberList.add(new Member(6, "Jane Janerson", this.getUniversity(15)));
        this.memberList.add(new Member(7, "Olives Are-Cool", this.getUniversity(8)));
        this.memberList.add(new Member(8, "Jenny Bradford", this.getUniversity(8)));
        this.memberList.add(new Member(9, "Larry Page", this.getUniversity(3)));
        this.memberList.add(new Member(10, "Louis LaLa", this.getUniversity(3)));
        this.memberList.add(new Member(11, "Ben Smith", this.getUniversity(12)));
        this.memberList.add(new Member(12, "Jack Jackson", this.getUniversity(5)));
        this.memberList.add(new Member(13, "Sam Sampson", this.getUniversity(12)));
        this.memberList.add(new Member(14, "Adam Small", this.getUniversity(5)));
        this.memberList.add(new Member(15, "Ann Anderson", this.getUniversity(5)));
        this.memberList.add(new Member(16, "Anthony Ant", this.getUniversity(8)));
    }

    private void populateTeamList() {
        this.teamList.add(new Team(1, this.getUniversity(7), 1));
        this.teamList.add(new Team(2, this.getUniversity(4), 1));
        this.teamList.add(new Team(3, this.getUniversity(12), 1));
        this.teamList.add(new Team(4, this.getUniversity(12), 2));
        this.teamList.add(new Team(5, this.getUniversity(15), 1));
    }

    private void populateEventList() {

        // 2014/15
        this.eventList.add(new Event(6, "BUCS Nationals"));
        this.eventList.add(new Event(5, "Nottingham"));
        this.eventList.add(new Event(4, "Cardiff"));
        this.eventList.add(new Event(3, "Bangor"));
        this.eventList.add(new Event(2, "PlymEx"));
        this.eventList.add(new Event(1, "BrUWE"));

        // 2015/16
        this.eventList.add(new Event(11, "Disney Presents Cardiff Wave"));
        this.eventList.add(new Event(10, "Nottingham Pondlife"));
        this.eventList.add(new Event(9, "BrUWE Wet Dreams"));
        this.eventList.add(new Event(8, "Bangor"));
        this.eventList.add(new Event(7, "Up the Brum!"));

        // foreach of the events add an empty ArrayList to the competitionMap
        for (Event event : this.eventList) {
            this.competitionMap.put(event, new ArrayList<Competition>());
        }
    }

    private void populateCompetitionMap() {

        // add competitions to "Disney Presents Cardiff Wave"
        this.competitionMap.get(getEvent(11)).add(new Competition(1, getCompetitionType(6)));

        // add competitions to "Nottingham Pondlife"
        this.competitionMap.get(getEvent(10)).add(new Competition(2, getCompetitionType(5)));


        // foreach of the competitions add an empty ArrayList to the competitorMap
        for (List<Competition> competitionList : this.competitionMap.values()) {
            for (Competition competition : competitionList) {
                this.competitorMap.put(competition, new ArrayList<CompetitionEntrant>());
            }
        }

    }

    private void populateCompetitionEntrantMap() {
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(13));
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(7));
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(4));
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(15));
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(2));
        this.competitorMap.get(this.getCompetition(1)).add(this.memberList.get(11));

        this.competitorMap.get(this.getCompetition(2)).add(this.teamList.get(1));
        this.competitorMap.get(this.getCompetition(2)).add(this.teamList.get(3));
        this.competitorMap.get(this.getCompetition(2)).add(this.teamList.get(0));
        this.competitorMap.get(this.getCompetition(2)).add(this.teamList.get(4));
    }

}
