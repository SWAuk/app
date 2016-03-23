package uk.co.swa.swapp.model;

import uk.co.swa.swapp.God;

/**
 * Created by oliver on 14/03/2016.
 */
public abstract class Competition extends SwaObject {

    protected CompetitionType competitionType;

    protected static God god = God.getInstance();

    public Competition(long appID, CompetitionType competitionType) {
        super(appID);
        this.competitionType = competitionType;
    }

    public Competition(CompetitionType competitionType) {
        this(-1, competitionType);
    }

    public CompetitionType getCompetitionType() {
        return this.competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

//    public Heat getHeat(int index) {
//        return this.heatList.get(index);
//    }

//    public List<Heat> getAllHeats() {
//        return this.heatList;
//    }

//    public boolean addHeat(Heat... heat) {
//        return this.heatList.addAll(Arrays.asList(heat));
//    }

//    public boolean addHeats(ArrayList<Heat> heats) {
//        return this.heatList.addAll(heats);
//    }

//    public boolean removeHeat(Heat heat) {
//        return this.heatList.remove(heat);
//    }

//    public Heat removeHeat(int index){
//        return this.heatList.remove(index);
//    }

//    public boolean removeHeats(List<Heat> heats) {
//        return this.heatList.removeAll(heats);
//    }

    // TODO: implement calculateResults?

    @Override
    public String toString() {
        return this.competitionType.toString();
    }
}
