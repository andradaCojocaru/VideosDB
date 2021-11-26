package main;

import java.util.ArrayList;
import java.util.Map;

public class Rating {
    int success = 0;

    public Rating() {

    }

    public void isSeen(Map<String,Integer> history, String movie) {
        if (history.containsKey(movie)) {
            success = 1;
        }
    }

    public void myRating(MyMovie movie, MyUser user, Double grade) {
        if (success == 1) {
            ArrayList<Double> ratings = new ArrayList<>();
            if (movie.getRatings() != null) {
                ratings = movie.getRatings();
            }
            ratings.add(grade);
            user.numberOfRatings += 1;
        }
    }

    public void myRating(MySerial serial, MyUser user, Double grade, int seasonNumber) {
        if (success == 1) {
            ArrayList<MySeason> seasons = new ArrayList<>();
            if (serial.getSeasons() != null) {
                seasons = serial.getSeasons();
            }
            for (MySeason season : seasons) {
                if (season.currentSeason == seasonNumber) {
                    season.ratings.add(grade);
                }
            }
        }
    }

    public String getMessage(String movie, Double grade, MyUser user) {
        if (success == 1) {
            return "success -> " + movie + " was rated with " + grade + " by "
                    + user.username;
        }
        return "error -> " + movie + " is not seen";
    }

}
