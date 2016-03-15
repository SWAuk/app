package uk.co.swa.swapp.model;

/**
 * Created by oliver on 14/03/2016.
 */
public class CompetitionType {
    private int competitionTypeId;
    private String competitionTypeName;

    public CompetitionType(String competitionTypeName) {
        this.competitionTypeName = competitionTypeName;
    }

    public CompetitionType(int competitionTypeId, String competitionTypeName) {
        this.competitionTypeId = competitionTypeId;
        this.competitionTypeName = competitionTypeName;
    }

    @Override
    public String toString() {
        return this.competitionTypeName;
    }
}
