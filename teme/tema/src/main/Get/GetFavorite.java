package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.ArrayList;
import java.util.List;

public class GetFavorite {
    public GetFavorite() {

    }

    public void numberFavoritesMovie(List<MyUser> users, List<MyMovie> movies) {
        for (MyUser user : users) {
            for (String movie : user.getFavoriteMovies()) {
                for (MyMovie movieList : movies) {
                    if (movieList.getTitle().equals(movie)) {
                        int favorites = movieList.getNumberOfFavorites();
                        favorites += 1;
                        movieList.setNumberOfFavorites(favorites);
                    }
                }
            }
        }
    }

    public void numberFavoritesSerial(List<MyUser> users, List<MySerial> serials) {
        for (MyUser user : users) {
            for (String serial : user.getFavoriteMovies()) {
                for (MySerial serialList : serials) {
                    if (serialList.getTitle().equals(serial)) {
                        int favorites = serialList.getNumberOfFavorites();
                        favorites += 1;
                        serialList.setNumberOfFavorites(favorites);
                    }
                }
            }
        }
    }
}
