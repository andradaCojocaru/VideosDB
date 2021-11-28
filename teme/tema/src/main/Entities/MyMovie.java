package main.Entities;

import java.util.ArrayList;

/**
 * video that is instance of class MyVideo
 */
public class MyMovie extends MyVideo {
    @lombok.Getter
    private int duration;
    @lombok.Setter
    @lombok.Getter
    private int numberOfRatings;
    /**
     * for new classes
     */
    public MyMovie() {

    }

    /**
     * @param title
     * @param year
     * @param cast
     * @param genres
     * @param duration
     * @param ratings
     */
    public MyMovie(final String title, final int year, final ArrayList<String> cast,
            final ArrayList<String> genres, final double rating,
                   final int numberOfFavorites, final int numberOfViews, final int duration,
            final ArrayList<Double> ratings, final int numberOfRatings) {
        super(title, year, cast, genres, rating, numberOfFavorites, numberOfViews, ratings);
        this.duration = duration;
        this.numberOfRatings = numberOfRatings;
    }

}
