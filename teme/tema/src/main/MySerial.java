package main;

import entertainment.Season;

import java.util.ArrayList;

public class MySerial extends MyVideo {
    int numberOfSeasons;
    ArrayList<MySeason> seasons;

    public MySerial() {

    }

    public MySerial(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<MySeason> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<MySeason> getSeasons() {
        return seasons;
    }
}
