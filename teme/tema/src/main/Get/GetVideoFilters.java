package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.ArrayList;
import java.util.List;

public class GetVideoFilters {
    public GetVideoFilters() {
    }
    public ArrayList<MySerial> serialFilteres (List<MySerial> serials,
                                               int filterOne, int filterTwo,
                                               String year, String genre) {
        ArrayList<MySerial> copySerials = new ArrayList<>();
        for (MySerial serial : serials) {
            int ok1;
            int ok2;
            ok1 = 1;
            ok2 = 1;
            if (filterTwo == 1) {
                ok2 = 0;
                for (String genreCurr : serial.getGenres()) {
                    if (genreCurr.equals(genre)) {
                        ok2 = 1;
                    }
                }
            }
            if (filterOne == 1) {
                ok1 = 0;
                String string = Integer.toString(serial.getYear());
                if (year.equals(string)) {
                    ok1 = 1;
                }
            }
            if (ok1 == 1 && ok2 == 1) {
                copySerials.add(serial);
            }
        }
        return copySerials;
    }

    public ArrayList<MyMovie> movieFilteres (List<MyMovie> movies,
                                               int filterOne, int filterTwo,
                                               String year, String genre) {
        ArrayList<MyMovie> copyMovies = new ArrayList<>();
        for (MyMovie movie : movies) {
            int ok1;
            int ok2;
            ok1 = 1;
            ok2 = 1;
            if (filterTwo == 1) {
                ok2 = 0;
                for (String genreCurr : movie.getGenres()) {
                    if (genreCurr.equals(genre)) {
                        ok2 = 1;
                    }
                }
            }
            if (filterOne == 1) {
                ok1 = 0;
                String string = Integer.toString(movie.getYear());
                if (year.equals(string)) {
                    ok1 = 1;
                }
            }
            if (ok1 == 1 && ok2 == 1) {
                copyMovies.add(movie);
            }
        }
        return copyMovies;
    }
}
