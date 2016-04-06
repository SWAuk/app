package uk.co.swa.swapp.model;

import java.util.Date;

public class Event implements SwaObject {

    private long appID;
    private String eventName;
    private Date eventDate;

    public Event(long appID, String eventName, Date eventDate) {
        this.appID = appID;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public Event(String eventName, Date eventDate) {
        this(-1, eventName, eventDate);
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return this.eventName;
    }

}
