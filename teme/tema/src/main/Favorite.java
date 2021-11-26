package main;

import java.util.ArrayList;
import java.util.Map;

public class Favorite{
    int success = 0;
    int notFavorite = 0;

    public void isSeen(Map<String,Integer> history, String movie) {
        if (history.containsKey(movie) == false) {
            success = 1;
        }
    }

    public void isFavorite(ArrayList<String> favorites, String movie) {
        for (String favorite : favorites) {
            if (favorite.equals(movie)) {
                notFavorite = 1;
            }
        }
    }

    public String getMessage(ArrayList<String> favorites, String movie) {
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
