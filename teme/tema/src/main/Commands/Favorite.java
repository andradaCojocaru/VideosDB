package main.Commands;

import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.ArrayList;
import java.util.Map;

/**
 * function that makes the favorite command
 */
public class Favorite {
    @lombok.Getter
    private int success;
    @lombok.Getter
    private int notFavorite;

    /**
     * @param history
     * @param movie
     */
    public void isSeen(final Map<String, Integer> history,
                       final String movie) {
        if (!history.containsKey(movie)) {
            success = 1;
        }
    }

    /**
     * @param favorites
     * @param movie
     */
    public void isFavorite(final ArrayList<String> favorites,
                           final String movie) {
        for (String favorite : favorites) {
            if (favorite.equals(movie)) {
                notFavorite = 1;
            }
        }
    }

    /**
     * @param favorites
     * @param movie
     * @return
     */
    public String getMessage(final ArrayList<String> favorites,
                             final MyMovie movie) {
        if (success == 0) {
            if (notFavorite == 0) {
                favorites.add(movie.getTitle());
                int nr = movie.getNumberOfFavorites();
                movie.setNumberOfFavorites(nr + 1);
                return "success -> " + movie.getTitle() + " was added as favourite";
            } else {
                return "error -> " + movie.getTitle() + " is already in favourite list";
            }
        } else {
            return "error -> " + movie.getTitle() + " is not seen";
        }
    }

    /**
     * @param favorites
     * @param serial
     * @return
     */
    public String getMessage(final ArrayList<String> favorites,
                             final MySerial serial) {
        if (success == 0) {
            if (notFavorite == 0) {
                favorites.add(serial.getTitle());
                int nr = serial.getNumberOfFavorites();
                serial.setNumberOfFavorites(nr + 1);
                return "success -> " + serial.getTitle() + " was added as favourite";
            } else {
                return "error -> " + serial.getTitle() + " is already in favourite list";
            }
        } else {
            return "error -> " + serial.getTitle() + " is not seen";
        }
    }

}
