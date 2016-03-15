package uk.co.swa.swapp;

import java.util.List;
import uk.co.swa.swapp.model.*;

public interface Store {

    public List<Season> getSeasons();
    public Season getSeason( int id );
    public List<Event> getEvents( Season season );
    public Event getEvent( int id );
    public List<CompetitionType> getCompetitionTypes();
    public CompetitionType getCompetitionType( int id );
    public List<Competition> getCompetitions( Event event );
    public Competition getCompetition( int id );
    public List<Member> getMembers();
    public Member getMember( int id );
    public List<University> getUniversities();
    public University getUniversity( int id );
    public List<Member> getEventAttendees( Event event );

}
