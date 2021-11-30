package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySeason;
import main.Entities.MySerial;

import java.util.ArrayList;

/**
 * class for rating video
 */
public class RatingVideo {
    /**
     * @param movie
     * @return
     */
    public double myRating(final MyMovie movie) {
        if (movie.getNumberOfRatings() != 0) {
            double sum;
            int n;
            sum = 0;
            n = 0;
            for (double rating : movie.getRatings()) {
                sum += rating;
                n += 1;
            }
            return sum / n;
        }
        return 0;
    }

    /**
     * @param serial
     * @return
     */
    public double myRating(final MySerial serial) {
        if (serial.getNumberOfRatings() != 0) {
            ArrayList<Double> ratings = new ArrayList<>();
            for (MySeason season : serial.getSeasons()) {
                double sum;
                int n;
                sum = 0;
                n = 0;
                for (Double rating : season.getRatings()) {
                    sum += rating;
                    n += 1;
                }
                if (n != 0) {
                    if (serial.getRatings() != null) {
                        ratings = serial.getRatings();
                    }
                    ratings.add(sum / n);
                } else {
                    ratings.add(0.0);
                }
                serial.setRatings(ratings);
            }

            double sum;
            sum = 0;
            for (Double rating : serial.getRatings()) {
                sum += rating;
            }
            return sum / serial.getNumberOfSeasons();
        }
        return 0;
    }
}

