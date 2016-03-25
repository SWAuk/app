package uk.co.swa.swapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uk.co.swa.swapp.God;

/**
 * Created by oliver on 14/03/2016.
 */
public class Competition extends SwaObject {
    protected God god;

    protected CompetitionType competitionType;

    public Competition(long appID, CompetitionType competitionType) {
        super(appID);
        this.competitionType = competitionType;

        this.god = God.getInstance();
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

    public void createCompetitionHeats(int maxEntrants) {
        Log.d("swapp.model.Competition",
                "createCompetitionHeats(maxEntrants = " + maxEntrants + ").");

        if (maxEntrants < 1) {
            throw new IllegalArgumentException("There has to be more than 1 entrant in a heat.");
        }

        List<? extends CompetitionEntrant> entrants = this.god.getAppStore().getCompetitionEntrants(Competition.this);
        Log.d("swapp.model.Competition", "Size entrants: " + entrants.size());

        final int numberOfHeats = (int) Math.ceil((double) entrants.size() / maxEntrants);
        int avgEntrantsPerHeat = entrants.size() / numberOfHeats;

        /*Collections.sort(entrants, new Comparator<CompetitionEntrant>() {
            @Override
            public int compare(CompetitionEntrant lhs, CompetitionEntrant rhs) {
                // TODO: implement comparison, first by points, then by university.
                int pointDifference = lhs.getPoints() - rhs.getPoints;
                if (pointDifference != 0) {
                    return pointDifference;
                }

                return  lhs.getUniversity().compareTo(rhs.getUniversity());
            }
        });*/

        List<List<CompetitionEntrant>> heatEntrants = new ArrayList() {{
            for (int i = 0; i < numberOfHeats; i++) {
                add(new ArrayList<CompetitionEntrant>());
            }
        }};

        Iterator<? extends CompetitionEntrant> entrantsIterator = entrants.iterator();

        for (int i = 0; i <= avgEntrantsPerHeat; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < numberOfHeats; j++) {
                    if (entrantsIterator.hasNext()) {
                        heatEntrants.get(j).add(entrantsIterator.next());
                    } else {
                        break;
                    }
                }
            } else {
                for (int j = numberOfHeats - 1; j >= 0; j--) {
                    if (entrantsIterator.hasNext()) {
                        heatEntrants.get(j).add(entrantsIterator.next());
                    } else {
                        break;
                    }
                }
            }
        }

        int i = 0;
        for (List<CompetitionEntrant> heats : heatEntrants) {
            System.out.println("===== Heat " + ++i + " =====");
            System.out.println(heats);
        }
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
