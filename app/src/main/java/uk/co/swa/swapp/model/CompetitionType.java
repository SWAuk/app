package uk.co.swa.swapp.model;

import java.lang.reflect.Type;

/**
 * Created by oliver on 14/03/2016.
 */
public class CompetitionType extends SwaObject {
    private String competitionTypeName;
    private Class<? extends Competition> competitionTypeClassification;

    public CompetitionType(long appID, String competitionTypeName,
                           Class<? extends Competition> competitionTypeClassification) {
        super(appID);
        this.competitionTypeName = competitionTypeName;
        this.competitionTypeClassification = competitionTypeClassification;
    }

    public CompetitionType(String competitionTypeName,
                           Class<? extends Competition> competitionTypeClassification) {
        this(-1, competitionTypeName, competitionTypeClassification);
    }

    @Override
    public String toString() {
        return this.competitionTypeName;
    }
}
