package uk.co.swa.swapp.store;

import java.util.List;
import uk.co.swa.swapp.model.*;

public interface Store {

    List<Season> getSeasons();
    Season getSeason( long id );
    List<Event> getEvents( Season season );
    Event getEvent( long id );
    List<CompetitionType> getCompetitionTypes();
    CompetitionType getCompetitionType( long id );
    List<Competition> getCompetitions( Event event );
    Competition getCompetition( long id );


    List<Member> getIndividualCompetitors( IndividualCompetition competition );
    List<Team> getTeamCompetitors( TeamCompetition competition );

    List<Member> getMembers();
    Member getMember( long id );
    List<University> getUniversities();
    University getUniversity( long id );
    List<Member> getEventAttendees( Event event );

}
