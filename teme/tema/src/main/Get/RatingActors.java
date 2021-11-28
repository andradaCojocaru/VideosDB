package main.Get;

import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.List;


public class RatingActors {
    public double myRating (MyActor actor, List<MyMovie> movies, List<MySerial> serials) {
        double sum;
        int n;
        int okMovie;
        sum = 0;
        n = 0;
        okMovie = 0;
        for (String name : actor.getFilmography()) {
            for (MyMovie movie : movies) {
                if (movie.getTitle().equals(name)) {
                    if (movie.getRating() != 0) {
                        sum += movie.getRating();
                        n += 1;
                        okMovie = 1;
                    }
                    break;
                }
            }
           if (okMovie == 0) {
               for (MySerial serial : serials) {
                   if (serial.getTitle().equals(name)) {
                       sum += serial.getRating();
                       n += 1;
                       break;
                   }
               }
           }
        }
        if (n != 0) {
            return sum/n;
        }
        return 0;
    }
}
