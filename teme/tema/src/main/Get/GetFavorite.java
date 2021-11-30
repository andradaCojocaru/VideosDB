package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

/**
 * class that sets number of appearences in Favorite list
 */
public class GetFavorite {
    /**
     * new GetFavorite class
     */
    public GetFavorite() {

    }

    /**
     * @param users
     * @param movies
     */
    public void numberFavoritesMovie(final List<MyUser> users, final List<MyMovie> movies) {
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

    /**
     * @param users
     * @param serials
     */
    public void numberFavoritesSerial(final List<MyUser> users, final List<MySerial> serials) {
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
