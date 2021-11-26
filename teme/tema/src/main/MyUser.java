package main;

import java.util.ArrayList;
import java.util.Map;

public class MyUser {
    String username;
    String subscriptionType;
    Map<String, Integer> history;
    ArrayList<String> favoriteMovies;
    int numberOfRatings;

    public MyUser(String username, String subscriptionType,
                  Map<String,Integer> history,
                  ArrayList<String> favoriteMovies,
                  int numberOfRatings) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.history = history;
        this.favoriteMovies = favoriteMovies;
        this.numberOfRatings = numberOfRatings;
    }
}
