package main;

import entertainment.Season;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySeason;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.ArrayList;
import java.util.List;

/**
 * class for MyInputLoader
 */
public class MyInputLoader {
    @lombok.Getter
    private List<MyActor> actorsData;
    @lombok.Getter
    private List<MyUser> usersData;
    @lombok.Getter
    private List<ActionInputData> commandsData;
    @lombok.Getter
    private List<MyMovie> moviesData;
    @lombok.Getter
    private List<MySerial> serialsData;

    /**
     * @param input
     */
    public MyInputLoader(final Input input) {
        actorsData = new ArrayList<>();
        List<ActorInputData> actors = new ArrayList<>(input.getActors());
        for (ActorInputData actor : actors) {
            actorsData.add(new MyActor(actor.getName(),
                    actor.getCareerDescription(), actor.getFilmography(),
                    actor.getAwards(), 0, 0));
        }
        List<UserInputData> users = new ArrayList<>(input.getUsers());
        usersData = new ArrayList<>();
        for (UserInputData user : users) {
            usersData.add(new MyUser(user.getUsername(),
                    user.getSubscriptionType(), user.getHistory(),
                    user.getFavoriteMovies(), 0, null));
        }
        commandsData = new ArrayList<>(input.getCommands());
        List<MovieInputData> movies = new ArrayList<>(input.getMovies());
        moviesData = new ArrayList<>();
        ArrayList<Double> ratings = new ArrayList<>();
        ratings = null;
        for (MovieInputData movie : movies) {
            moviesData.add(new MyMovie(movie.getTitle(), movie.getYear(),
                    movie.getCast(), movie.getGenres(), 0, 0, 0, movie.getDuration(),
                    ratings, 0));
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
                    serial.getGenres(), 0, 0, 0, serial.getNumberSeason(),
                    seasons, serial.getYear(), 0, 0, ratings));
        }
    }
}
