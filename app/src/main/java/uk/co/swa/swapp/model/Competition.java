package uk.co.swa.swapp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Competition implements SwaObject {

    private long appID;
    private CompetitionType competitionType;
    private Event event;

    public Competition(long appID, CompetitionType competitionType, Event event) {
        this.appID = appID;
        this.competitionType = competitionType;
        this.event = event;
    }

    public Competition(CompetitionType competitionType, Event event) {
        this(-1, competitionType, event);
    }

    @Override
    public long getAppID() {
        return appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }

    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    // TODO: This doesn't belong here
    public void createCompetitionHeats(int maxEntrants) {
        Log.d("swapp.model.Competition",
                "createCompetitionHeats(maxEntrants = " + maxEntrants + ").");

        if (maxEntrants < 1) {
            throw new IllegalArgumentException("There has to be more than 1 entrant in a heat.");
        }

        List<? extends CompetitionEntrant> entrants = new ArrayList<>();
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

        List<List<CompetitionEntrant>> heatEntrants = new ArrayList<List<CompetitionEntrant>>() {{
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

    @Override
    public String toString() {
        return this.competitionType.toString();
    }

}
