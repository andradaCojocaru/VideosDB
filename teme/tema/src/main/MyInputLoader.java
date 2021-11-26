package main;

import entertainment.Season;
import fileio.*;

import java.util.ArrayList;
import java.util.List;

public class MyInputLoader {
    List<ActorInputData> actorsData;
    List<MyUser> usersData;
    List<ActionInputData> commandsData;
    List<MyMovie> moviesData;
    List<MySerial> serialsData;

    public MyInputLoader(Input input) {
        actorsData = new ArrayList<>(input.getActors());
        List<UserInputData> users = new ArrayList<>(input.getUsers());
        usersData = new ArrayList<>();
        for (UserInputData user : users) {
            usersData.add(new MyUser(user.getUsername(),
                    user.getSubscriptionType(), user.getHistory(),
                    user.getFavoriteMovies(), 0));
        }
        commandsData = new ArrayList<>(input.getCommands());
        List<MovieInputData> movies = new ArrayList<>(input.getMovies());
        moviesData = new ArrayList<>();
        ArrayList<Double> ratings = new ArrayList<>();
        for (MovieInputData movie : movies) {
            moviesData.add(new MyMovie(movie.getTitle(), movie.getYear(),
                    movie.getCast(), movie.getGenres(), movie.getDuration(),
                    ratings));
        }
        List<SerialInputData> serials = new ArrayList<>(input.getSerials());
        serialsData = new ArrayList<>();
        for (SerialInputData serial : serials) {
            ArrayList<MySeason> seasons = new ArrayList<>();
            int i = 0;
            for (Season season : serial.getSeasons()) {
                i += 1;
                seasons.add(new MySeason(i, season.getDuration()));
            }
            serialsData.add(new MySerial(serial.getTitle(), serial.getCast(),
                    serial.getGenres(), serial.getNumberSeason(),
                    seasons, serial.getYear()));
        }
    }
}
