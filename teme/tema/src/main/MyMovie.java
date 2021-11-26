package main;

import java.util.ArrayList;

public class MyMovie extends MyVideo {
    int duration;
    ArrayList<Double> ratings;

    public MyMovie() {

    }

    public MyMovie(String title, int year, ArrayList<String> cast,
            ArrayList<String> genres, int duration,
            ArrayList<Double> ratings) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratings = ratings;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }
}
