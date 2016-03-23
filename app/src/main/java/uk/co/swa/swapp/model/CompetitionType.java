package uk.co.swa.swapp.model;

/**
 * Created by oliver on 14/03/2016.
 */
public class CompetitionType extends SwaObject {
    private String competitionTypeName;

    public CompetitionType(long appID, String competitionTypeName) {
        super(appID);
        this.competitionTypeName = competitionTypeName;
    }

    public CompetitionType(String competitionTypeName) {
        this(-1, competitionTypeName);
    }

    @Override
    public String toString() {
        return this.competitionTypeName;
    }
}
