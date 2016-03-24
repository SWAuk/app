package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oliver on 12/03/2016.
 */
public class Event extends SwaObject {

    private String eventName;

    public Event(long appID, String eventName) {
        super(appID);
        this.eventName = eventName;
    }

    public Event(String eventName) {
        this(-1, eventName);
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

//    public Competition getCompetition(long id) {
//        return null;
//    }

//    public List<Competition> getAllCompetitions() {
//        return null;
//    }

//    public boolean addCompetition(Competition... competition) {
//        return false;
//    }

//    public boolean addCompetitions(List<Competition> competitions) {
//        return false;
//    }

//    public boolean removeCompetition(Competition competition) {
//        return false;
//    }

//    public Competition removeCompetition(int index) {
//        return null;
//    }

//    public boolean removeCompetitions(List<Competition> competitions) {
//        return false;
//    }

    @Override
    public String toString() {
        return this.eventName;
    }

}
