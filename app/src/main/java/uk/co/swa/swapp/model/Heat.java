package uk.co.swa.swapp.model;

import java.util.ArrayList;
import java.util.List;

public class Heat implements SwaObject {

    long appID;
    Competition competition;
    List<? extends CompetitionEntrant> competitionEntrantList;
    int duration;

    public Heat(long appID,
                Competition competition,
                int duration,
                List<? extends CompetitionEntrant> competitionEntrants) {
        this.appID = appID;
        this.competition = competition;
        this.duration = duration;
        this.competitionEntrantList = competitionEntrants;

    }

    public Heat (Competition competition,
                 int duration,
                 List<? extends CompetitionEntrant> competitionEntrants) {
        this(-1, competition, duration, competitionEntrants);
    }

    public Heat (Competition competition, int duration) {
        this(-1, competition, duration, new ArrayList<CompetitionEntrant>());
    }

    @Override
    public long getAppID() {
        return this.appID;
    }

    @Override
    public void setAppID(long appID) {
        this.appID = appID;
    }
}
