package uk.co.swa.swapp.model;

public class Member implements CompetitionEntrant{

    private long appID;
    private String memberName;
    private University memberUniversity;

    public Member(long appID, String memberName, University memberUniversity) {
        this.appID = appID;
        this.memberName = memberName;
        this.memberUniversity = memberUniversity;
    }

    public Member(String memberName, University university) {
        this(-1, memberName, university);
    }

    @Override
    public String getName() {
        return this.memberName;
    }

    public void setName(String memberName) {
        this.memberName = memberName;
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
    public University getUniversity() {
        return memberUniversity;
    }

    @Override
    public void setUniversity(University memberUniversity) {
        this.memberUniversity = memberUniversity;
    }

    @Override
    public String toString() {
        return this.memberName;
    }

}
