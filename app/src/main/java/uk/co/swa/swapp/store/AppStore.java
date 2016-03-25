package uk.co.swa.swapp.store;

import java.util.List;
import uk.co.swa.swapp.model.*;

/**
 * This interface is or the storage of and maintenance of data within the application.
 * Data would first be retrieved from the MainStore and then stored here.
 * The data could then be manipulated within the AppStore.
 */
public interface AppStore {

    List<Season> getSeasons();
    Season getSeason( long id );
    List<Event> getEvents( Season season );
    Event getEvent( long id );
    List<CompetitionType> getCompetitionTypes();
    CompetitionType getCompetitionType( long id );
    List<Competition> getCompetitions( Event event );
    Competition getCompetition( long id );

    List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition );
    List<Heat> getCompetitionHeats(Competition competition);

    List<Member> getMembers();
    Member getMember( long id );
    List<University> getUniversities();
    University getUniversity( long id );
    List<Member> getEventAttendees( Event event );

}
