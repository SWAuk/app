package uk.co.swa.swapp.model;

public class CompetitionType implements SwaObject {

    long appID;
    private String competitionTypeName;

    public CompetitionType(long appID, String competitionTypeName) {
        this.appID = appID;
        this.competitionTypeName = competitionTypeName;
    }

    public CompetitionType(String competitionTypeName) {
        this(-1, competitionTypeName);
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    @Override
    public String toString() {
        return this.competitionTypeName;
    }

}
