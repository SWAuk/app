package uk.co.swa.swapp.model;

/**
 * Created by oliver on 23/03/2016.
 */
public interface CompetitionEntrant extends SwaObject {

    String getName();
    void setName(String name);
    University getUniversity();
    void setUniversity(University university);
}
