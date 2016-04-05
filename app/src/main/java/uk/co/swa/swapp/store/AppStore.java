package uk.co.swa.swapp.store;

import java.util.List;
import uk.co.swa.swapp.model.*;

/**
 * This interface is or the storage of and maintenance of data within the application.
 * Data would first be retrieved from the MainStore and then stored here.
 * The data could then be manipulated within the AppStore.
 */
public interface AppStore {

    Event getEvent( long id );
    List<Event> getEvents();
    List<Event> getEvents(int limit);

    CompetitionType getCompetitionType( long id );
    List<CompetitionType> getCompetitionTypes();

    Competition getCompetition( long id );
    List<Competition> getCompetitions( Event event );

    CompetitionEntrant getCompetitionEntrant(Competition competition, long id );
    List<? extends CompetitionEntrant> getCompetitionEntrants(Competition competition );
    boolean addCompetitionEntrant(Competition competition,
                                  CompetitionEntrant competitionEntrant);
    boolean addCompetitionEntrants(Competition competition,
                                   List<CompetitionEntrant> competitionEntrants);
    boolean removeCompetitionEntrant(Competition competition,
                                     CompetitionEntrant competitionEntrant);
    boolean removeCompetitionEntrants(Competition competition,
                                      List<CompetitionEntrant> competitionEntrants);

    Heat getCompetitionHeat(long id);
    List<Heat> getCompetitionHeats(Competition competition);

    Member getMember( long id );
    List<Member> getMembers();

    University getUniversity( long id );
    List<University> getUniversities();

    Team getTeam( long id );
    List<Team> getTeams();

    List<Member> getEventAttendees( Event event );
    List<Member> getEventAttendees( Competition competition );

}
