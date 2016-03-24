package uk.co.swa.swapp.store;

import java.util.List;
import java.util.Map;

import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.University;

/**
 * Interface for the main store of the SWA system.
 * Upon app creation this is the joomla website.
 *
 * This interface will initially just handel getting information from the website such as
 * lists of events, attendees of those event etc for the app to use.
 *
 * In the future this may also be used to send data after an event back to the website storage.
 *
 * Created by addshore on 23/03/2016.
 */
public interface MainStore {

    //TODO events have event dates!
    Map<Integer,Event> getEvents();

    Map<Integer,CompetitionType> getCompetitionTypes();

    Map<Integer,University> getUniversities();

    Map<Integer,Member> getMembers();

    Map<Integer,List<Integer>> getEventAttendees( Event event );

}
