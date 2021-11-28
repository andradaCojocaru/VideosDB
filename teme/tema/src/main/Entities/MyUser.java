package main.Entities;

import java.util.ArrayList;
import java.util.Map;

/**
 * class for MyUser
 */
public class MyUser {
    @lombok.Getter
    private String username;
    @lombok.Getter
    private String subscriptionType;
    @lombok.Getter
    private Map<String, Integer> history;
    @lombok.Getter
    private ArrayList<String> favoriteMovies;
    @lombok.Setter
    @lombok.Getter
    private int numberOfRatings;
    @lombok.Setter
    @lombok.Getter
    private ArrayList<String> rated;

    /**
     * for new classes
     */
    public MyUser() {

    }

    /**
     * @param username
     * @param subscriptionType
     * @param history
     * @param favoriteMovies
     * @param numberOfRatings
     */
    public MyUser(final String username, final String subscriptionType,
                  final Map<String, Integer> history,
                  final ArrayList<String> favoriteMovies,
                  final int numberOfRatings, final ArrayList<String> rated) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.history = history;
        this.favoriteMovies = favoriteMovies;
        this.numberOfRatings = numberOfRatings;
        this.rated = rated;
    }

}
