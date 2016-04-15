package uk.co.swa.swapp.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.swa.swapp.model.Competition;
import uk.co.swa.swapp.model.CompetitionEntrant;
import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Heat;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Round;
import uk.co.swa.swapp.model.Team;
import uk.co.swa.swapp.model.University;

public class MockAppStore implements AppStore {

    List<CompetitionType> competitionTypeList;
    List<University> universityList;
    List<Member> memberList;
    List<Team> teamList;

    List<Event> eventList;
    Map<Event, List<Competition>> competitionMap;
    Map<Competition, List<CompetitionEntrant>> competitionEntrantsMap;
    Map<Competition, List<Round>> competitionRoundsMap;
    Map<Round, List<Heat>> roundHeatsMap;
    Map<Heat, List<CompetitionEntrant>> heatEntrantsMap;
    Map<Event, List<Member>> eventAttendeesMap;

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
        this.competitionEntrantsMap = new HashMap<>();
        this.competitionRoundsMap = new HashMap<>();
        this.roundHeatsMap = new HashMap<>();

        this.populateEventList();
        this.populateCompetitionMap();
        this.populateCompetitionEntrantMap();
        this.populateEventAttendeesMap();
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
    public List<Event> getEvents() {
        return new ArrayList<>(this.eventList);
    }

    @Override
    public List<Event> getEvents(int limit) {

        int size = this.eventList.size();

        if (limit > size) {
            return new ArrayList<>(this.eventList);
        } else {
            return new ArrayList<>(this.eventList.subList(size - limit, size));
        }
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
    public List<CompetitionType> getCompetitionTypes() {
        return new ArrayList<>(this.competitionTypeList);
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
        return new ArrayList<>(this.competitionMap.get(event));
    }

    @Override
    public boolean addCompetition(Competition competition) {
        // get the list of Competitions for the Event the Competition is in
        List<Competition> competitionList = this.competitionMap.get(competition.getEvent());
        // set the appID in the competition
        competition.setAppID(competitionList.size());
        // add the competition to the list
        boolean success = competitionList.add(competition);
        // add an empty ArrayList to hold CompetitionEntrants in
        if (success) {
            competitionEntrantsMap.put(competition, new ArrayList<CompetitionEntrant>());
            competitionRoundsMap.put(competition, new ArrayList<Round>());
        }

        // return if the add was successful or not
        return success;
    }

    @Override
    public boolean removeCompetition(Competition competition) {
        boolean success = competitionMap.get(competition.getEvent()).remove(competition);

        if (success) {
            competitionEntrantsMap.remove(competition);
            competitionRoundsMap.remove(competition);
        }

        return success;
    }

    @Override
    public CompetitionEntrant getCompetitionEntrant(Competition competition, long id) {
        for (CompetitionEntrant competitionEntrant : this.competitionEntrantsMap.get(competition)) {
            if (competitionEntrant.getAppID() == id) {
                return competitionEntrant;
            }
        }

        return null;
    }

    @Override
    public List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition) {
        return new ArrayList<>(this.competitionEntrantsMap.get(competition));
    }

    @Override
    public boolean addCompetitionEntrant(Competition competition,
                                         CompetitionEntrant competitionEntrant) {
        List<CompetitionEntrant> competitionEntrantList = competitionEntrantsMap.get(competition);
        competition.setAppID(competitionEntrantList.size());
        return competitionEntrantList.add(competitionEntrant);
    }

    @Override
    public boolean addCompetitionEntrants(Competition competition,
                                          List<CompetitionEntrant> competitionEntrants) {

        List<CompetitionEntrant> competitionEntrantList = competitionEntrantsMap.get(competition);

        for (int i=0; i<competitionEntrants.size(); i++) {
            CompetitionEntrant competitionEntrant = competitionEntrants.get(i);
            competitionEntrant.setAppID(competitionEntrantList.size() + i);
        }

        return competitionEntrantList.addAll(competitionEntrants);
    }

    @Override
    public boolean removeCompetitionEntrant(Competition competition,
                                            CompetitionEntrant competitionEntrant) {

        return this.competitionEntrantsMap.get(competition).remove(competitionEntrant);
    }

    @Override
    public boolean removeCompetitionEntrants(Competition competition,
                                             List<CompetitionEntrant> competitionEntrants) {

        return this.competitionEntrantsMap.get(competition).removeAll(competitionEntrants);
    }

    @Override
    public Round getRound(long id) {
        for (List<Round> roundList : this.competitionRoundsMap.values()){
            for (Round round : roundList) {
                if (round.getAppID() == id) {
                    return round;
                }
            }
        }

        return null;
    }

    @Override
    public List<Round> getCompetitionRounds(Competition competition) {
        return new ArrayList<>(competitionRoundsMap.get(competition));
    }

    @Override
    public void addRound(Round round) {
        competitionRoundsMap.get(round.getCompetition()).add(round);
        roundHeatsMap.put(round, new ArrayList<Heat>());
    }

    @Override
    public void removeRound(Round round) {
        competitionEntrantsMap.get(round.getCompetition()).remove(round);
        roundHeatsMap.remove(round);
    }

    @Override
    public Heat getHeat(long id) {
        for (List<Heat> heatList : this.roundHeatsMap.values()){
            for (Heat heat : heatList) {
                if (heat.getAppID() == id) {
                    return heat;
                }
            }
        }

        return null;
    }

    @Override
    public List<Heat> getRoundHeats(Round round) {
        return new ArrayList<>(roundHeatsMap.get(round));
    }

    @Override
    public Member getMember(long id) {
        for (Member member : memberList) {
            if (member.getAppID() == id) {
                return member;
            }
        }

        return null;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(memberList);
    }

    @Override
    public University getUniversity(long id) {
        for (University university : this.universityList) {
            if (university.getAppID() == id) {
                return university;
            }
        }

        return null;
    }

    @Override
    public List<University> getUniversities() {
        return new ArrayList<>(this.universityList);
    }

    @Override
    public Team getTeam(long id) {
        for (Team team : this.teamList) {
            if (team.getAppID() == id) {
                return team;
            }
        }

        return null;
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(this.teamList);
    }

    @Override
    public List<Member> getEventAttendees(Event event) {
        // TODO: implement properly
        // return this.eventAttendeesMap.get(event);
        return this.memberList;
    }

    @Override
    public List<Member> getEventAttendees(Competition competition) {
        for (Event event : this.competitionMap.keySet()) {
            if (this.competitionMap.get(event).equals(competition)) {
                return this.getEventAttendees(event);
            }
        }

        // TODO: implement properly
        // return null;
        return this.memberList;
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
        this.memberList.add(new Member(6, "Jane Janerson", this.getUniversity(8)));
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
        this.eventList.add(new Event(6, "BUCS Nationals", new Date()));
        this.eventList.add(new Event(5, "Nottingham", new Date()));
        this.eventList.add(new Event(4, "Cardiff", new Date()));
        this.eventList.add(new Event(3, "Bangor", new Date()));
        this.eventList.add(new Event(2, "PlymEx", new Date()));
        this.eventList.add(new Event(1, "BrUWE", new Date()));

        // 2015/16
        this.eventList.add(new Event(11, "Disney Presents Cardiff Wave", new Date()));
        this.eventList.add(new Event(10, "Nottingham Pondlife", new Date()));
        this.eventList.add(new Event(9, "BrUWE Wet Dreams", new Date()));
        this.eventList.add(new Event(8, "Bangor", new Date()));
        this.eventList.add(new Event(7, "Up the Brum!", new Date()));

        // foreach of the events add an empty ArrayList to the competitionMap
        for (Event event : this.eventList) {
            this.competitionMap.put(event, new ArrayList<Competition>());
        }
    }

    private void populateCompetitionMap() {

        // add competitions to "Disney Presents Cardiff Wave"
        competitionMap.get(getEvent(11)).add(new Competition(1, getCompetitionType(6), getEvent(11)));

        // add competitions to "Nottingham Pondlife"
        competitionMap.get(getEvent(10)).add(new Competition(2, getCompetitionType(5), getEvent(10)));


        // foreach of the competitions add an empty ArrayList to the competitionEntrantsMap
        for (List<Competition> competitionList : competitionMap.values()) {
            for (Competition competition : competitionList) {
                competitionEntrantsMap.put(competition, new ArrayList<CompetitionEntrant>());
                competitionRoundsMap.put(competition, new ArrayList<Round>());
            }
        }

    }

    private void populateCompetitionEntrantMap() {
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(13));
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(7));
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(4));
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(15));
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(2));
        competitionEntrantsMap.get(getCompetition(1)).add(this.getMember(11));

        competitionEntrantsMap.get(getCompetition(2)).add(this.getTeam(1));
        competitionEntrantsMap.get(getCompetition(2)).add(this.getTeam(3));
        competitionEntrantsMap.get(getCompetition(2)).add(this.getTeam(5));
        competitionEntrantsMap.get(getCompetition(2)).add(this.getTeam(4));
    }

    private void populateEventAttendeesMap() {

    }

}
