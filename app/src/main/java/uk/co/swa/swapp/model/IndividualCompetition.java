package uk.co.swa.swapp.model;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by oliver on 12/03/2016.
 */
public class IndividualCompetition extends Competition {

    private List<Member> competitorList;

    public IndividualCompetition(int competitionId, CompetitionType competitionType,
                                 List<Heat> heats, List<Member> competitors) {

        super(competitionId, competitionType, heats);
        this.competitorList = competitors;

    }

    public IndividualCompetition(CompetitionType competitionType) {
        super(competitionType);
        this.competitorList = new ArrayList<>();
    }

    public IndividualCompetition(int competitionId, CompetitionType competitionType) {
        super(competitionId, competitionType);
        this.competitorList = new ArrayList<>();
    }

    public IndividualCompetition(CompetitionType competitionType, Member... member) {
        super(competitionType);
        this.competitorList = Arrays.asList(member);
    }

    public IndividualCompetition(int id, CompetitionType competitionType, Member... member) {
        super(id, competitionType);
        this.competitorList = Arrays.asList(member);
    }

    public IndividualCompetition(CompetitionType competitionType, List<Member> competitors) {
        super(competitionType);
        this.competitorList = competitors;
    }

    public IndividualCompetition(int id, CompetitionType competitionType, List<Member> competitors) {
        super(id, competitionType);
        this.competitorList = competitors;
    }

    public Member getCompetitor(int index) {
        return this.competitorList.get(index);
    }

    public List<Member> getAllCompetitors() {
        return this.competitorList;
    }

    public boolean addCompetitor(Member... member) {
        return this.competitorList.addAll(Arrays.asList(member));
    }

    public boolean addCompetitors(List<Member> members) {
        return this.competitorList.addAll(members);
    }

    public boolean removeCompetitor(Member member) {
        return competitorList.remove(member);
    }

    public Member removeCompetitor(int index) {
        return competitorList.remove(index);
    }

    public boolean removeCompetitors(ArrayList<Competition> competitors) {
        return competitorList.removeAll(competitors);
    }
}
