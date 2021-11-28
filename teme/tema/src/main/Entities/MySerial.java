package main.Entities;

import java.util.ArrayList;

/**
 * class that extands class MyVideo for MySerial
 */
public class MySerial extends MyVideo {
    @lombok.Getter
    private int numberOfSeasons;
    @lombok.Getter
    private ArrayList<MySeason> seasons;

    @lombok.Setter
    @lombok.Getter
    private int numberOfRatings;

    @lombok.Setter
    @lombok.Getter
    private int timeSum;
    /**
     * for new classes
     */
    public MySerial() {

    }


    /**
     * @param title
     * @param cast
     * @param genres
     * @param numberOfSeasons
     * @param seasons
     * @param year
     * @param numberOfRatings
     */
    public MySerial(final String title, final ArrayList<String> cast,
                    final ArrayList<String> genres, final double rating,
                    final int numberOfFavorites, final int numberOfViews,
                    final int numberOfSeasons,
                    final ArrayList<MySeason> seasons, final int year,
                    final int numberOfRatings, final int timeSum,
                    final ArrayList<Double> ratings) {
        super(title, year, cast, genres, rating, numberOfFavorites, numberOfViews, ratings);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
        this.numberOfRatings = numberOfRatings;
        this.timeSum = timeSum;
    }
}
