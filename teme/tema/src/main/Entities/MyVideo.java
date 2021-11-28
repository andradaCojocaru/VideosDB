package main.Entities;

import java.util.ArrayList;

/**
 * class MyVideo
 */
public class MyVideo {
    @lombok.Getter
    private String title;
    @lombok.Getter
    private int year;
    @lombok.Getter
    private ArrayList<String> cast;
    @lombok.Getter
    private ArrayList<String> genres;
    @lombok.Setter
    @lombok.Getter
    private double rating;
    @lombok.Setter
    @lombok.Getter
    private int numberOfFavorites;
    @lombok.Setter
    @lombok.Getter
    private int numberOfViews;
    @lombok.Getter
    @lombok.Setter
    private ArrayList<Double> ratings;

    /**
     * for new classes
     */
    public MyVideo() {
    }


    /**
     * @param title
     * @param year
     * @param cast
     * @param genres
     * @param rating
     * @param numberOfFavorites
     * @param numberOfViews
     */
    public MyVideo(final String title, final int year,
                   final ArrayList<String> cast,
                   final ArrayList<String> genres, final double rating,
                   final int numberOfFavorites, final int numberOfViews,
                   final ArrayList<Double> ratings) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.rating = rating;
        this.numberOfFavorites = numberOfFavorites;
        this.numberOfViews = numberOfViews;
        this.ratings = ratings;
    }
}
