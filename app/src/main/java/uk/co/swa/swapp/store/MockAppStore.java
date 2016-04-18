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
        competitionTypeList = new ArrayList<>();
        universityList = new ArrayList<>();
        memberList = new ArrayList<>();
        teamList = new ArrayList<>();

        populateCompetitionTypeList();
        populateUniversityList();
        populateMemberList();
        populateTeamList();

        eventList = new ArrayList<>();
        competitionMap = new HashMap<>();
        competitionEntrantsMap = new HashMap<>();
        competitionRoundsMap = new HashMap<>();
        roundHeatsMap = new HashMap<>();

        populateEventList();
        populateCompetitionMap();
        populateCompetitionEntrantMap();
        populateEventAttendeesMap();
    }

    @Override
    public Event getEvent(long id) {
        for (Event event : eventList) {
            if (event.getAppID() == id) {
                return event;
            }
        }

        return null;
    }

    @Override
    public List<Event> getEvents() {
        return new ArrayList<>(eventList);
    }

    @Override
    public List<Event> getEvents(int limit) {

        int size = eventList.size();

        if (limit > size) {
            return new ArrayList<>(eventList);
        } else {
            return new ArrayList<>(eventList.subList(size - limit, size));
        }
    }

    @Override
    public CompetitionType getCompetitionType(long id) {
        for (CompetitionType competitionType : competitionTypeList) {
            if (competitionType.getAppID() == id) {
                return competitionType;
            }
        }

        return null;
    }

    @Override
    public List<CompetitionType> getCompetitionTypes() {
        return new ArrayList<>(competitionTypeList);
    }

    @Override
    public Competition getCompetition(long id) {
        for (List<Competition> competitionList : competitionMap.values()){
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
        return new ArrayList<>(competitionMap.get(event));
    }

    @Override
    public boolean addCompetition(Competition competition) {
        // get the list of Competitions for the Event the Competition is in
        List<Competition> competitionList = competitionMap.get(competition.getEvent());
        // set the appID in the competition
        competition.setAppID(competitionList.size() + 1);
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
        for (CompetitionEntrant competitionEntrant : competitionEntrantsMap.get(competition)) {
            if (competitionEntrant.getAppID() == id) {
                return competitionEntrant;
            }
        }

        return null;
    }

    @Override
    public List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition) {
        return new ArrayList<>(competitionEntrantsMap.get(competition));
    }

    @Override
    public boolean addCompetitionEntrant(Competition competition,
                                         CompetitionEntrant competitionEntrant) {
        List<CompetitionEntrant> competitionEntrantList = competitionEntrantsMap.get(competition);
        if (competitionEntrant.getAppID() == -1) {
            if (competitionEntrant instanceof Team) {
                competitionEntrant.setAppID(teamList.size() + 1);
                teamList.add((Team) competitionEntrant);
            } else if (competitionEntrant instanceof Member) {
                competitionEntrant.setAppID(memberList.size() + 1);
                memberList.add((Member) competitionEntrant);
            }
        }

        return competitionEntrantList.add(competitionEntrant);
    }

    @Override
    public boolean addCompetitionEntrants(Competition competition,
                                          List<CompetitionEntrant> competitionEntrants) {

        List<CompetitionEntrant> competitionEntrantList = competitionEntrantsMap.get(competition);

        for (int i=1; i<competitionEntrants.size() + 1; i++) {
            CompetitionEntrant competitionEntrant = competitionEntrants.get(i);
            competitionEntrant.setAppID(competitionEntrantList.size() + i);
        }

        return competitionEntrantList.addAll(competitionEntrants);
    }

    @Override
    public boolean removeCompetitionEntrant(Competition competition,
                                            CompetitionEntrant competitionEntrant) {

        return competitionEntrantsMap.get(competition).remove(competitionEntrant);
    }

    @Override
    public boolean removeCompetitionEntrants(Competition competition,
                                             List<CompetitionEntrant> competitionEntrants) {

        return competitionEntrantsMap.get(competition).removeAll(competitionEntrants);
    }

    @Override
    public Round getRound(long id) {
        for (List<Round> roundList : competitionRoundsMap.values()){
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
        List <Round> roundList = competitionRoundsMap.get(round.getCompetition());
        if (round.getAppID() == -1) {
            round.setAppID(roundList.size() + 1);
        }
        roundList.add(round);
        roundHeatsMap.put(round, new ArrayList<Heat>());
    }

    @Override
    public void removeRound(Round round) {
        competitionEntrantsMap.get(round.getCompetition()).remove(round);
        roundHeatsMap.remove(round);
    }

    @Override
    public Heat getHeat(long id) {
        for (List<Heat> heatList : roundHeatsMap.values()){
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
        for (University university : universityList) {
            if (university.getAppID() == id) {
                return university;
            }
        }

        return null;
    }

    @Override
    public List<University> getUniversities() {
        return new ArrayList<>(universityList);
    }

    @Override
    public Team getTeam(long id) {
        for (Team team : teamList) {
            if (team.getAppID() == id) {
                return team;
            }
        }

        return null;
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teamList);
    }

    @Override
    public List<Member> getEventAttendees(Event event) {
        // TODO: implement properly
        // return eventAttendeesMap.get(event);
        return memberList;
    }

    @Override
    public List<Member> getEventAttendees(Competition competition) {
        for (Event event : competitionMap.keySet()) {
            if (competitionMap.get(event).equals(competition)) {
                return getEventAttendees(event);
            }
        }

        // TODO: implement properly
        return memberList;
    }


    private void populateCompetitionTypeList() {
        competitionTypeList.add(new CompetitionType(1, "Beginner"));
        competitionTypeList.add(new CompetitionType(2, "Intermediate"));
        competitionTypeList.add(new CompetitionType(3, "Advanced"));
        competitionTypeList.add(new CompetitionType(4, "Freestyle"));
        competitionTypeList.add(new CompetitionType(5, "Team"));
        competitionTypeList.add(new CompetitionType(6, "Wave"));
    }

    private void populateUniversityList() {
        universityList.add(new University(1, "Bangor"));
        universityList.add(new University(2, "Birmingham"));
        universityList.add(new University(3, "Bristol"));
        universityList.add(new University(4, "Cardiff"));
        universityList.add(new University(5, "Exeter"));
        universityList.add(new University(6, "Imperial"));
        universityList.add(new University(7, "Leeds"));
        universityList.add(new University(8, "Liverpool"));
        universityList.add(new University(9, "Newcastle"));
        universityList.add(new University(10, "Nottingham"));
        universityList.add(new University(11, "Plymouth"));
        universityList.add(new University(12, "Southampton"));
        universityList.add(new University(13, "Southampton Solent"));
        universityList.add(new University(14, "UEA"));
        universityList.add(new University(15, "UWE"));
    }

    private void populateMemberList() {
        memberList.add(new Member(1, "Adam Franklin", getUniversity(15)));
        memberList.add(new Member(2, "Ollie Johnson", getUniversity(15)));
        memberList.add(new Member(3, "Roisin Irish", getUniversity(15)));
        memberList.add(new Member(4, "Kate Simpson", getUniversity(15)));
        memberList.add(new Member(5, "Ben Jones", getUniversity(15)));
        memberList.add(new Member(6, "Jane Janerson", getUniversity(8)));
        memberList.add(new Member(7, "Olives Are-Cool", getUniversity(8)));
        memberList.add(new Member(8, "Jenny Bradford", getUniversity(8)));
        memberList.add(new Member(9, "Larry Page", getUniversity(3)));
        memberList.add(new Member(10, "Louis LaLa", getUniversity(3)));
        memberList.add(new Member(11, "Ben Smith", getUniversity(12)));
        memberList.add(new Member(12, "Jack Jackson", getUniversity(5)));
        memberList.add(new Member(13, "Sam Sampson", getUniversity(12)));
        memberList.add(new Member(14, "Adam Small", getUniversity(5)));
        memberList.add(new Member(15, "Ann Anderson", getUniversity(5)));
        memberList.add(new Member(16, "Anthony Ant", getUniversity(8)));
    }

    private void populateTeamList() {
        teamList.add(new Team(1, getUniversity(7), 1));
        teamList.add(new Team(2, getUniversity(4), 1));
        teamList.add(new Team(3, getUniversity(12), 1));
        teamList.add(new Team(4, getUniversity(12), 2));
        teamList.add(new Team(5, getUniversity(15), 1));
    }

    private void populateEventList() {

        // 2014/15
        eventList.add(new Event(6, "BUCS Nationals", new Date()));
        eventList.add(new Event(5, "Nottingham", new Date()));
        eventList.add(new Event(4, "Cardiff", new Date()));
        eventList.add(new Event(3, "Bangor", new Date()));
        eventList.add(new Event(2, "PlymEx", new Date()));
        eventList.add(new Event(1, "BrUWE", new Date()));

        // 2015/16
        eventList.add(new Event(11, "Disney Presents Cardiff Wave", new Date()));
        eventList.add(new Event(10, "Nottingham Pondlife", new Date()));
        eventList.add(new Event(9, "BrUWE Wet Dreams", new Date()));
        eventList.add(new Event(8, "Bangor", new Date()));
        eventList.add(new Event(7, "Up the Brum!", new Date()));

        // foreach of the events add an empty ArrayList to the competitionMap
        for (Event event : eventList) {
            competitionMap.put(event, new ArrayList<Competition>());
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
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(13));
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(7));
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(4));
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(15));
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(2));
        competitionEntrantsMap.get(getCompetition(1)).add(getMember(11));

        competitionEntrantsMap.get(getCompetition(2)).add(getTeam(1));
        competitionEntrantsMap.get(getCompetition(2)).add(getTeam(3));
        competitionEntrantsMap.get(getCompetition(2)).add(getTeam(5));
        competitionEntrantsMap.get(getCompetition(2)).add(getTeam(4));
    }

    private void populateEventAttendeesMap() {

    }

}
