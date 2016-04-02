package uk.co.swa.swapp.model;

public class University implements SwaObject {

    private long appID;
    private String universityName;

    public University(long appID, String universityName) {
        this.appID = appID;
        this.universityName = universityName;
    }

    public University(String universityName) {
        this(-1, universityName);
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return this.universityName;
    }

}
