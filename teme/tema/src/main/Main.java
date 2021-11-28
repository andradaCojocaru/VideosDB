package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import main.Commands.Favorite;
import main.Commands.Rating;
import main.Commands.View;
import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;
import main.Get.*;
import main.Queries.Actors.SortActors;
import main.Queries.Users.SortUsers;
import main.Queries.Videos.SortMovie;
import main.Queries.Videos.SortSerial;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH,
                Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        MyInputLoader myInputLoader = new MyInputLoader(input);
        MyInput myInput = new MyInput(myInputLoader.getActorsData(),
                myInputLoader.getUsersData(), myInputLoader.getCommandsData(),
                myInputLoader.getMoviesData(), myInputLoader.getSerialsData());

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();
        List<ActionInputData> commands = new ArrayList<>();
        String message = new String();
        commands = input.getCommands();
        for (ActionInputData command : commands) {
            String username = command.getUsername();
            MyUser newUser = new MyUser();
            for (MyUser user : myInput.getUsersData()) {
                if (user.getUsername().equals(username)) {
                    newUser = user;
                    break;
                }
            }
            String actionType = command.getActionType();
            String title = command.getTitle();
            if (actionType.equals("command")) {
                String action = command.getType();
                if (action.equals("favorite")) {
                    Favorite favorite1 = new Favorite();
                    favorite1.isSeen(newUser.getHistory(), title);
                    favorite1.isFavorite(newUser.getFavoriteMovies(),
                            title);
                    message = favorite1.getMessage(
                            newUser.getFavoriteMovies(), title);
                } else if (action.equals("view")) {
                    View view1 = new View();
                    message = view1.getMessage(newUser.getHistory(), title);
                } else if (action.equals("rating")) {
                    Rating rating = new Rating();
                    rating.isSeen(newUser.getHistory(), title);
                    if (command.getSeasonNumber() == 0) {
                        MyMovie newMovie = new MyMovie();
                        for (MyMovie movie : myInput.getMoviesData()) {
                            if ((movie.getTitle()).equals(title)) {
                                newMovie = movie;
                                break;
                            }
                        }
                        message = rating.myRating(newMovie, newUser,
                                command.getGrade());
                    } else {
                        MySerial newSerial = new MySerial();
                        for (MySerial serial : myInput.getSerialsData()) {
                            if (serial.getTitle().equals(title)) {
                                newSerial = serial;
                                break;
                            }
                        }
                        message = rating.myRating(newSerial, newUser,
                                command.getGrade(), command.getSeasonNumber());
                    }

                }
            } else if (actionType.equals("query")) {
                String action = command.getObjectType();
                String criteria = command.getCriteria();
                if (action.equals("actors")) {
                    if (criteria.equals("average")) {
                        RatingVideo newRating = new RatingVideo();
                        RatingActors newRatingActor = new RatingActors();
                        ArrayList<MyActor> newActors = new ArrayList<>();
                        for (MyMovie movie : myInputLoader.getMoviesData()) {
                            movie.setRating(newRating.myRating(movie));
                        }
                        for (MySerial serial : myInputLoader.getSerialsData()) {
                            serial.setRating(newRating.myRating(serial));
                        }
                        for (MyActor actor : myInputLoader.getActorsData()) {
                            actor.setAverage(newRatingActor.myRating(actor,
                                    myInputLoader.getMoviesData(),
                                    myInputLoader.getSerialsData()));
                        }
                        for (MyActor actor : myInputLoader.getActorsData()) {
                            if (actor.getAverage() != 0) {
                                newActors.add(actor);
                            }
                        }
                        ArrayList<String> names = new ArrayList<>();
                        SortActors sort = new SortActors();
                        sort.SortAverage(command.getSortType());
                        message = sort.mySort(newActors,
                                command.getNumber());
                    } else if (criteria.equals("awards")) {
                        List<String> awards = command.getFilters().get(3);
                        ArrayList<MyActor> newActors = new ArrayList<>();
                        AwardsActors awardsActors = new AwardsActors();
                        newActors = awardsActors.myAwardsActor
                                (myInput.getActorsData(), awards);
                        SortActors sort = new SortActors();
                        sort.SortAwards(command.getSortType());
                        message = sort.mySort(newActors, command.getNumber());
                    } else if (criteria.equals("filter_description")) {
                        List<String> filters = command.getFilters().get(2);
                        ArrayList<MyActor> newActors = new ArrayList<>();
                        FilterActors filterActors = new FilterActors();
                        newActors = filterActors.myFilterActor
                                (myInput.getActorsData(), filters);
                        SortActors sort = new SortActors();
                        sort.SortFilters(command.getSortType());
                        message = sort.mySort(newActors, command.getNumber());
                    }
                } else if (action.equals("shows") || action.equals("movies")) {
                    int filterOne;
                    int filterTwo;
                    filterOne = 0;
                    filterTwo = 0;
                    String year = new String();
                    String genre = new String();
                    if (command.getFilters().get(0).get(0) != null) {
                        year = command.getFilters().get(0).get(0);
                        filterOne = 1;
                    }
                    if (command.getFilters().get(1).get(0) != null) {
                        genre = command.getFilters().get(1).get(0);
                        filterTwo = 1;
                    }
                    if (action.equals("shows")) {
                        ArrayList<MySerial> copySerials = new ArrayList<>();
                        GetVideoFilters getVideoFilters = new GetVideoFilters();
                        copySerials = getVideoFilters.serialFilteres
                                (myInput.getSerialsData(), filterOne,
                                        filterTwo, year, genre);
                        if (criteria.equals("ratings")) {
                            RatingVideo ratingVideo = new RatingVideo();
                            for (MySerial serial : copySerials) {
                                ratingVideo.myRating(serial);
                            }
                            SortSerial sort = new SortSerial();
                            sort.SortRating(command.getSortType());
                            if (copySerials.size() >= command.getNumber() && copySerials.size() > 1) {
                                message = sort.mySort(copySerials, command.getNumber());
                            } else {
                                message = "Query result: []";
                            }
                        } else if (criteria.equals("favorite")) {
                            GetFavorite getFavorite = new GetFavorite();
                            getFavorite.numberFavoritesSerial(myInput.getUsersData(), copySerials);
                            SortSerial sort = new SortSerial();
                            copySerials.removeIf((v) -> v.getNumberOfFavorites() == 0);
                            sort.sortFavorites(command.getSortType());
                            message = sort.mySort(copySerials, command.getNumber());
                        } else if (criteria.equals("longest")) {
                            SortSerial sort = new SortSerial();
                            TimeSerial timeSerial = new TimeSerial();
                            timeSerial.getTimeSerial(copySerials);
                            sort.Longest(command.getSortType());
                            message = sort.mySort(copySerials, command.getNumber());
                        } else if (criteria.equals("most_viewed")) {
                            GetViews getViews = new GetViews();
                            getViews.numberViewsSerial(myInput.getUsersData(), copySerials);
                            copySerials.removeIf((v) -> v.getNumberOfViews() == 0);
                            SortSerial sort = new SortSerial();
                            sort.mostViewed(command.getSortType());
                            message = sort.mySort(copySerials, command.getNumber());
                        }
                    } else if (action.equals("movies")) {
                        ArrayList<MyMovie> copyMovies = new ArrayList<>();
                        GetVideoFilters getVideoFilters = new GetVideoFilters();
                        copyMovies = getVideoFilters.movieFilteres
                                (myInput.getMoviesData(), filterOne,
                                        filterTwo, year, genre);
                        if (criteria.equals("ratings")) {
                            RatingVideo ratingVideo = new RatingVideo();
                            for (MyMovie movie : copyMovies) {
                                ratingVideo.myRating(movie);
                            }
                            SortMovie sort = new SortMovie();
                            sort.SortRating(command.getSortType());
                            if (copyMovies.size() >= command.getNumber() && copyMovies.size() > 1) {
                                message = sort.mySort(copyMovies, command.getNumber());
                            } else {
                                message = "Query result: []";
                            }
                        } else if (criteria.equals("favorite")) {
                            GetFavorite getFavorite = new GetFavorite();
                            getFavorite.numberFavoritesMovie(myInput.getUsersData(), copyMovies);
                            SortMovie sort = new SortMovie();
                            copyMovies.removeIf((v) -> v.getNumberOfFavorites() == 0);
                            sort.sortFavorites(command.getSortType());
                            message = sort.mySort(copyMovies, command.getNumber());
                        } else if (criteria.equals("longest")) {
                            SortMovie sort = new SortMovie();
                            sort.Longest(command.getSortType());
                            message = sort.mySort(copyMovies, command.getNumber());
                        } else if (criteria.equals("most_viewed")) {
                            GetViews getViews = new GetViews();
                            getViews.numberViewsMovie(myInput.getUsersData(), copyMovies);
                            copyMovies.removeIf((v) -> v.getNumberOfViews() == 0);
                            SortMovie sort = new SortMovie();
                            sort.mostViewed(command.getSortType());
                            message = sort.mySort(copyMovies, command.getNumber());
                        }
                    }
                } else if (action.equals("users")) {
                    ArrayList<MyUser> copyUsers = new ArrayList<>();
                    for (MyUser user : myInput.getUsersData()) {
                        copyUsers.add(user);
                    }
                    SortUsers sort = new SortUsers();
                    copyUsers.removeIf((v) -> v.getNumberOfRatings() == 0);
                    sort.SortNumberOfRatings(command.getSortType());
                    message = sort.mySort(copyUsers, command.getNumber());
                }
            }
                arrayResult.add(fileWriter.writeFile(command.getActionId(),
                        message));
        }

            fileWriter.closeJSON(arrayResult);
    }
}

