package main.Commands;

import main.Entities.MyMovie;
import main.Entities.MySeason;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.ArrayList;
import java.util.Map;

/**
 * class that gives the rating
 */
public class Rating {
    @lombok.Getter
    private int success = 0;

    /**
     * for new classes
     */
    public Rating() {

    }

    /**
     * @param history
     * @param movie
     */
    public void isSeen(final Map<String, Integer> history,
                       final String movie) {
        if (history.containsKey(movie)) {
            success = 1;
        }
    }

    /**
     * @param movie
     * @param user
     * @param grade
     */
    public String myRating(final MyMovie movie, final MyUser user,
                           final Double grade) {
        ArrayList<String> rated = new ArrayList<>();
        if (success == 1) {
            if (user.getRated() != null) {
                for (String name : user.getRated()) {
                    if (name.equals(movie.getTitle())) {
                        return "error -> " + movie.getTitle() + " has been already rated";
                    }
                }
            }
            ArrayList<Double> ratings = new ArrayList<>();
            if (movie.getRatings() != null) {
                ratings = movie.getRatings();
            }
            ratings.add(grade);
            movie.setRatings(ratings);
            int noRatings = user.getNumberOfRatings();
            noRatings += 1;
            user.setNumberOfRatings(noRatings);
            int numberOfRatings = movie.getNumberOfRatings();
            numberOfRatings += 1;
            movie.setNumberOfRatings(numberOfRatings);
            if (user.getRated() != null) {
                rated = user.getRated();
            }
            rated.add(movie.getTitle());
            user.setRated(rated);
            return "success -> " + movie.getTitle() + " was rated with " + grade + " by "
                    + user.getUsername();
        }
        return "error -> " + movie.getTitle() + " is not seen";
    }

    /**
     * @param serial
     * @param grade
     * @param seasonNumber
     */
    public String myRating(final MySerial serial, final MyUser user,
                           final Double grade, final int seasonNumber) {
        ArrayList<String> rated = new ArrayList<>();
        if (success == 1) {
            if (user.getRated() != null) {
                for (String name : user.getRated()) {
                    if (name.equals((serial.getTitle() + seasonNumber))) {
                        return "error -> " + serial.getTitle() + " has been already rated";
                    }
                }
            }
            ArrayList<Double> ratings = new ArrayList<>();
            for (MySeason season : serial.getSeasons()) {
                ratings = season.getRatings();
                if (season.getCurrentSeason() == seasonNumber) {
                    ratings.add(grade);
                    season.setRatings(ratings);
                    break;
                }
            }
            int noRatings = user.getNumberOfRatings();
            noRatings += 1;
            user.setNumberOfRatings(noRatings);
            int numberOfRatings = serial.getNumberOfRatings();
            numberOfRatings += 1;
            serial.setNumberOfRatings(numberOfRatings);
            if (user.getRated() != null) {
                rated = user.getRated();
            }
            String newTitle = new String();
            newTitle = serial.getTitle() + seasonNumber;
            rated.add(newTitle);
            user.setRated(rated);
            return "success -> " + serial.getTitle() + " was rated with " + grade + " by "
                    + user.getUsername();
        }
        return "error -> " + serial.getTitle() + " is not seen";
    }
}

