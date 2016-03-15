package uk.co.swa.swapp.model;

/**
 * Created by oliver on 12/03/2016.
 */
public class Member {

    private int id;
    private String memberName;
    private University memberUniversity;

    public Member(int id, String memberName, University memberUniversity) {
        this.id = id;
        this.memberName = memberName;
        this.memberUniversity = memberUniversity;
    }

    public Member(String memberName, University university) {
        this(-1, memberName, university);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public University getMemberUniversity() {
        return memberUniversity;
    }

    public void setMemberUniversity(University memberUniversity) {
        this.memberUniversity = memberUniversity;
    }
}
