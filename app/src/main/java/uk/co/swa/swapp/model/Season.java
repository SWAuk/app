package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oliver on 12/03/2016.
 */
public class Season {

    private int seasonId;
    private String seasonName;
    private List<Event> eventList;

    public Season(int seasonId, String seasonName, List<Event> events) {
        this.seasonId = seasonId;
        this.seasonName = seasonName;
        this.eventList = events;
    }

    public Season(String seasonName) {
        this(-1, seasonName, new ArrayList<Event>());
    }

    public Season(int seasonId, String seasonName) {
        this(seasonId, seasonName, new ArrayList<Event>());
    }

    public Season(String seasonName, List<Event> events) {
        this(-1, seasonName, events);
    }

    public Season(String seasonName, Event... event) {
        this(seasonName, Arrays.asList(event));
    }

    public Season(int id, String seasonName, Event... event) {
        this(id, seasonName, Arrays.asList(event));
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public Event getEvent(int index) {
        return eventList.get(index);
    }

    public List<Event> getAllEvents() {
        return eventList;
    }

    public boolean addEvent(Event... event) {
        return eventList.addAll(Arrays.asList(event));
    }

    public boolean addEvent(ArrayList<Event> events) {
        return eventList.addAll(events);
    }

    public boolean removeEvent(Event... event) {
        return eventList.removeAll(Arrays.asList(event));
    }

    public Event removeEvent(int index) {
        return eventList.remove(index);
    }

    public boolean removeEvents(List<Event> events) {
        return eventList.removeAll(events);
    }

    @Override
    public String toString() {
        return seasonName;
    }

}
