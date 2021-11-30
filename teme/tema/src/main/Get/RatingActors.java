package main.Get;

import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.List;

/**
 * class for rating actors
 */
public class RatingActors {
    /**
     * @param actor
     * @param movies
     * @param serials
     * @return
     */
    public double myRating(final MyActor actor,
                            final List<MyMovie> movies,
                            final List<MySerial> serials) {
        double sum;
        int n;
        sum = 0;
        n = 0;
        for (String name : actor.getFilmography()) {
            for (MyMovie movie : movies) {
                if (movie.getTitle().equals(name)) {
                    if (movie.getRating() != 0) {
                        sum += movie.getRating();
                        n += 1;
                    }
                    break;
                }
            }
            for (MySerial serial : serials) {
                if (serial.getTitle().equals(name)) {
                    sum += serial.getRating();
                    n += 1;
                    break;
                }
            }
        }
        if (n != 0) {
            return sum / n;
        }
        return 0;
    }
}
