package uk.co.swa.swapp;

import java.util.ArrayList;
import java.util.List;

import uk.co.swa.swapp.model.CompetitionType;
import uk.co.swa.swapp.model.Event;
import uk.co.swa.swapp.model.IndividualCompetition;
import uk.co.swa.swapp.model.Member;
import uk.co.swa.swapp.model.Season;
import uk.co.swa.swapp.model.SeasonList;
import uk.co.swa.swapp.model.TeamCompetition;
import uk.co.swa.swapp.model.University;

/**
 * Created by oliver on 14/03/2016.
 */
public class GodScrappyDog {

    private static GodScrappyDog puppy = null;
    private SeasonList seasonList;

    private List<CompetitionType> competitionTypeList;
    private Store store;

    private GodScrappyDog() {
        this.createMockData();
        this.store = new SqliteStore();
    }

    public static GodScrappyDog letMeAtEm() {
        if (GodScrappyDog.puppy != null) {
            return GodScrappyDog.puppy;
        }

        GodScrappyDog.puppy = new GodScrappyDog();
        return GodScrappyDog.puppy;
    }

    public Store getStore() {
        return this.store;
    }

    public SeasonList getSeasonList() {
        return this.seasonList;
    }

    public CompetitionType getCompetitionType(int index) {
        return this.competitionTypeList.get(index);
    }

    private void createMockData() {

        CompetitionType beginnerType = new CompetitionType("Beginner");
        CompetitionType intermediateType = new CompetitionType("Intermediate");
        CompetitionType advancedType = new CompetitionType("Advanced");
        CompetitionType freestyleType = new CompetitionType("Freestyle");
        CompetitionType teamType = new CompetitionType("Team");
        CompetitionType waveType = new CompetitionType("Wave");

        University bristolUni = new University("Bristol");
        University uweUni = new University("UWE");
        University liverpoolUni = new University("Liverpool");
        University cardiffUni = new University("Cardiff");
        University southamptonUni = new University("Southampton");
        University brumUni = new University("Birmingham");

        Member sophieHarvey = new Member("Sophie Harvey", brumUni);
        Member alexParker = new Member("Alex Parker", cardiffUni);
        Member amyRobinson = new Member("Amy Robinson", cardiffUni);
        Member louisMorris = new Member("Louis Morris", bristolUni);
        Member isobelMitchell = new Member("Isobel Mitchell", bristolUni);
        Member scrappy = new Member("Scrappy!", uweUni);
        Member adamShorland = new Member("Adam Shoreland", uweUni);
        Member saraKellet = new Member("Sara Kellet", uweUni);
        Member olivierAyache = new Member("Olivier Ayache", liverpoolUni);
        Member janePaddison = new Member("Jane Paddison", liverpoolUni);
        Member benPage = new Member("Ben Page", southamptonUni);

        this.seasonList = new SeasonList(
                new Season(15, "2015 - 2016",
                        new Event("Disney Presents Cardiff Wave",
                                new IndividualCompetition(waveType,
                                        louisMorris,
                                        alexParker,
                                        benPage)),
                        new Event("Nottingham Pondlife",
                                new IndividualCompetition(beginnerType,
                                        scrappy,
                                        new Member("Ben Thompson", uweUni)),
                                new IndividualCompetition(intermediateType,
                                        olivierAyache,
                                        isobelMitchell,
                                        sophieHarvey),
                                new IndividualCompetition(advancedType,
                                        adamShorland,
                                        janePaddison),
                                new IndividualCompetition(freestyleType,
                                        janePaddison),
                                new TeamCompetition(teamType)),
                        new Event("BrUWE Wet Dreams",
                                new IndividualCompetition(beginnerType),
                                new IndividualCompetition(intermediateType),
                                new IndividualCompetition(advancedType),
                                new IndividualCompetition(freestyleType),
                                new TeamCompetition(teamType)),
                        new Event("Bangor",
                                new IndividualCompetition(waveType,
                                        louisMorris,
                                        saraKellet,
                                        alexParker,
                                        benPage)),
                        new Event("Up the Brum!"),
                        new Event("PlymEx"),
                        new Event("Aussie Kiss 14")),
                new Season(14, "2014 - 2015",
                        new Event("BUCS Nationals",
                                new IndividualCompetition(beginnerType),
                                new IndividualCompetition(intermediateType),
                                new IndividualCompetition(advancedType),
                                new IndividualCompetition(freestyleType),
                                new TeamCompetition(teamType)),
                        new Event("Nottingham",
                                new IndividualCompetition(beginnerType),
                                new IndividualCompetition(intermediateType),
                                new IndividualCompetition(advancedType),
                                new IndividualCompetition(freestyleType),
                                new TeamCompetition(teamType)),
                        new Event("Cardiff",
                                new IndividualCompetition(waveType)),
                        new Event("Bangor"),
                        new Event("PlymEx"),
                        new Event("BrUWE"),
                        new Event("Liverpool Northern Monkey"),
                        new Event("Aussie Kiss 13")));

        this.competitionTypeList = new ArrayList<>();
        this.competitionTypeList.add(beginnerType);
        this.competitionTypeList.add(intermediateType);
        this.competitionTypeList.add(advancedType);
        this.competitionTypeList.add(freestyleType);
        this.competitionTypeList.add(teamType);
        this.competitionTypeList.add(waveType);

    }
}
