package uk.co.swa.swapp.model;

public interface CompetitionEntrant extends SwaObject {

    String getName();
    University getUniversity();
    void setUniversity(University university);

}
