package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oliver on 12/03/2016.
 */
public class Event {

    private int id;
    private String eventName;
    private List<Competition> competitionList;

    public Event(int id, String eventName, List<Competition> competitionList) {
        this.id = id;
        this.eventName = eventName;
        this.competitionList = competitionList;
    }

    public Event(String eventName) {
        this(-1, eventName, new ArrayList<Competition>());
    }

    public Event(String eventName, Competition... competition) {
        this(-1, eventName, Arrays.asList(competition));
    }

    public Event(int id, String eventName, Competition... competition) {
        this(id, eventName, Arrays.asList(competition));
    }

    public Event(String eventName, List<Competition> competitions) {
        this(-1, eventName, competitions);
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Competition getCompetition(int index) {
        return this.competitionList.get(index);
    }

    public List<Competition> getAllCompetitions() {
        return this.competitionList;
    }

    public boolean addCompetition(Competition... competition) {
        return this.competitionList.addAll(Arrays.asList(competition));
    }

    public boolean addCompetitions(List<Competition> competitions) {
        return this.competitionList.addAll(competitions);
    }

    public boolean removeCompetition(Competition competition) {
        return this.competitionList.remove(competition);
    }

    public Competition removeCompetition(int index) {
        return this.competitionList.remove(index);
    }

    public boolean removeCompetitions(List<Competition> competitions) {
        return this.competitionList.removeAll(competitions);
    }

    @Override
    public String toString() {
        return this.eventName;
    }
}
