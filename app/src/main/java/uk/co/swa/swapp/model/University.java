package uk.co.swa.swapp.model;

/**
 * Created by oliver on 15/03/2016.
 */
public class University extends SwaObject {

    private String universityName;

    public University(long appID, String universityName) {
        super(appID);
        this.universityName = universityName;
    }

    public University(String universityName) {
        this(-1, universityName);
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
