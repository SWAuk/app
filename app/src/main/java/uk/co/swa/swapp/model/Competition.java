package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.co.swa.swapp.GodScrappyDog;

/**
 * Created by oliver on 14/03/2016.
 */
public abstract class Competition {

    protected int competitionId;
    protected CompetitionType competitionType;
    protected List <Heat> heatList;

    protected static GodScrappyDog puppy = GodScrappyDog.letMeAtEm();

    public Competition(int competitionId, CompetitionType competitionType, List<Heat> heats) {
        this.competitionId = competitionId;
        this.competitionType = competitionType;
        this.heatList = heats;
    }

    public Competition(int competitionId, CompetitionType competitionType) {
        this(competitionId, competitionType, new ArrayList<Heat>());
    }

    public Competition(CompetitionType competitionType) {
        this(-1, competitionType, new ArrayList<Heat>());
    }

    public Competition(CompetitionType competitionType, List<Heat> heats) {
        this(-1, competitionType, heats);
    }

    public CompetitionType getCompetitionType() {
        return this.competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Heat getHeat(int index) {
        return this.heatList.get(index);
    }

    public List<Heat> getAllHeats() {
        return this.heatList;
    }

    public boolean addHeat(Heat... heat) {
        return this.heatList.addAll(Arrays.asList(heat));
    }

    public boolean addHeats(ArrayList<Heat> heats) {
        return this.heatList.addAll(heats);
    }

    public boolean removeHeat(Heat heat) {
        return this.heatList.remove(heat);
    }

    public Heat removeHeat(int index){
        return this.heatList.remove(index);
    }

    public boolean removeHeats(List<Heat> heats) {
        return this.heatList.removeAll(heats);
    }

    // TODO: implement calculateResults?

    @Override
    public String toString() {
        return this.competitionType.toString();
    }
}
