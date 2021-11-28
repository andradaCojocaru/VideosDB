package main.Commands;

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
                             final String movie) {
        if (success == 0) {
            if (notFavorite == 0) {
                favorites.add(movie);
                return "success -> " + movie + " was added as favourite";
            } else {
                return "error -> " + movie + " is already in favourite list";
            }
        } else {
            return "error -> " + movie + " is not seen";
        }
    }

}
