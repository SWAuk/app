package uk.co.swa.swapp.model;

import java.util.List;

import uk.co.swa.swapp.God;

/**
 * Created by oliver on 12/03/2016.
 */
public class Season extends SwaObject {

    private String seasonName;
    private God god;

    public Season(long appID, String seasonName) {
        super(appID);
        this.seasonName = seasonName;
        this.god = God.getInstance();
    }

    public Season(String seasonName) {
        this(-1, seasonName);
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

//    public Event getEvent(int index) {
//        return this.god.getEventList().get(index);
//        return eventList.get(index);
//    }

    public List<Event> getAllEvents() {
        return this.god.getStore().getEvents(this);
    }

//    public boolean addEvent(Event... event) {
//        return eventList.addAll(Arrays.asList(event));
//    }
//
//    public boolean addEvent(ArrayList<Event> events) {
//        return eventList.addAll(events);
//    }
//
//    public boolean removeEvent(Event... event) {
//        return eventList.removeAll(Arrays.asList(event));
//    }
//
//    public Event removeEvent(int index) {
//        return eventList.remove(index);
//    }
//
//    public boolean removeEvents(List<Event> events) {
//        return eventList.removeAll(events);
//    }

    @Override
    public String toString() {
        return seasonName;
    }

}
