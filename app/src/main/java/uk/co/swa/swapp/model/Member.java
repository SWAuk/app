package uk.co.swa.swapp.model;

/**
 * Created by oliver on 12/03/2016.
 */
public class Member extends SwaObject implements CompetitionEntrant {

    private String memberName;
    private University memberUniversity;

    public Member(long appID, String memberName, University memberUniversity) {
        super(appID);
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
