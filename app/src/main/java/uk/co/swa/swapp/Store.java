package uk.co.swa.swapp;

import java.util.List;
import uk.co.swa.swapp.model.*;

public interface Store {

    public List<Season> getSeasons();
    public Season getSeason( long id );
    public List<Event> getEvents( Season season );
    public Event getEvent( long id );
    public List<CompetitionType> getCompetitionTypes();
    public CompetitionType getCompetitionType( long id );
    public List<Competition> getCompetitions( Event event );
    public Competition getCompetition( long id );
    public List<Member> getMembers();
    public Member getMember( long id );
    public List<University> getUniversities();
    public University getUniversity( long id );
    public List<Member> getEventAttendees( Event event );

}
