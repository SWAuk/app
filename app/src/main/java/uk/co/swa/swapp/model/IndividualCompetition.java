package uk.co.swa.swapp.model;

import uk.co.swa.swapp.God;

/**
 * Created by oliver on 12/03/2016.
 */
public class IndividualCompetition extends Competition {

    public IndividualCompetition(long id, CompetitionType competitionType) {
        super(id, competitionType);
    }

    public IndividualCompetition(CompetitionType competitionType) {
        super(competitionType);
    }

//    public Member getCompetitor(int index) {
//        return this.competitorList.get(index);
//    }

//    public List<Member> getAllCompetitors() {
//        return this.competitorList;
//    }

//    public boolean addCompetitor(Member... member) {
//        return this.competitorList.addAll(Arrays.asList(member));
//    }

//    public boolean addCompetitors(List<Member> members) {
//        return this.competitorList.addAll(members);
//    }

//    public boolean removeCompetitor(Member member) {
//        return competitorList.remove(member);
//    }

//    public Member removeCompetitor(int index) {
//        return competitorList.remove(index);
//    }

//    public boolean removeCompetitors(ArrayList<Competition> competitors) {
//        return competitorList.removeAll(competitors);
//    }

}
