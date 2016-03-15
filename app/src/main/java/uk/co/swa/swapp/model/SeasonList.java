package uk.co.swa.swapp.model;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import uk.co.swa.swapp.model.Season;

/**
 * Created by oliver on 12/03/2016.
 */
public class SeasonList {

    private List<Season> seasonList;

    public SeasonList() {
        this.seasonList = new ArrayList<Season>();
    }

    public SeasonList(Season... season) {
        this.seasonList = new ArrayList<Season>();
        this.seasonList.addAll(Arrays.asList(season));
    }

    public SeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
    }

    public Season getSeason(int index) {
        return seasonList.get(index);
    }

    public List<Season> getAllSeasons() {
        return seasonList;
    }

    public boolean addSeason(Season... season) {
        return seasonList.addAll(Arrays.asList(season));
    }

    public boolean addSeasons(ArrayList<Season> seasons) {
        return seasonList.addAll(seasons);
    }

    public boolean removeSeason(Season... season) {
        return seasonList.removeAll(Arrays.asList(season));
    }

    public Season removeSeason(int index) {
        return seasonList.remove(index);
    }

    public boolean removeSeasons(ArrayList<Season> seasons) {
        return seasonList.removeAll(seasons);
    }

}
