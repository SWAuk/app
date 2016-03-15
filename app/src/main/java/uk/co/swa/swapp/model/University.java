package uk.co.swa.swapp.model;

/**
 * Created by oliver on 15/03/2016.
 */
public class University {

    private int id;
    private String universityName;

    public University(int id, String universityName) {
        this.id = id;
        this.universityName = universityName;
    }

    public University(String universityName) {
        this(-1, universityName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
