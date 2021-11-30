package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entertainment.Genre;
import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import main.Commands.Favorite;
import main.Commands.Rating;
import main.Commands.View;
import main.Entities.MyActor;
import main.Entities.MyGenre;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;
import main.Entities.MyVideo;
import main.Get.AwardsActors;
import main.Get.FilterActors;
import main.Get.GetFavorite;
import main.Get.GetVideoFilters;
import main.Get.GetVideoNotSeen;
import main.Get.GetViews;
import main.Get.RatingActors;
import main.Get.RatingVideo;
import main.Get.TimeSerial;
import main.Queries.Actors.SortActors;
import main.Queries.Users.SortUsers;
import main.Queries.Videos.SortMovie;
import main.Queries.Videos.SortSerial;
import main.Recommendations.SortGenre;
import main.Recommendations.SortVideo;
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
                        sort.sortAverage(command.getSortType());
                        message = sort.mySort(newActors,
                                command.getNumber());
                    } else if (criteria.equals("awards")) {
                        List<String> awards = command.getFilters().get(3);
                        ArrayList<MyActor> newActors = new ArrayList<>();
                        AwardsActors awardsActors = new AwardsActors();
                        newActors = awardsActors.myAwardsActor(myInput.getActorsData(), awards);
                        SortActors sort = new SortActors();
                        sort.sortAwards(command.getSortType());
                        message = sort.mySort(newActors, command.getNumber());
                    } else if (criteria.equals("filter_description")) {
                        List<String> filters = command.getFilters().get(2);
                        ArrayList<MyActor> newActors = new ArrayList<>();
                        FilterActors filterActors = new FilterActors();
                        newActors = filterActors.myFilterActor(myInput.getActorsData(), filters);
                        SortActors sort = new SortActors();
                        sort.sortFilters(command.getSortType());
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
                        copySerials = getVideoFilters.serialFilteres(myInput.getSerialsData(),
                                filterOne, filterTwo, year, genre);
                        if (criteria.equals("ratings")) {
                            RatingVideo ratingVideo = new RatingVideo();
                            for (MySerial serial : copySerials) {
                                serial.setRating(ratingVideo.myRating(serial));
                            }
                            SortSerial sort = new SortSerial();
                            copySerials.removeIf((v) -> v.getRating() == 0);
                            sort.sortRating(command.getSortType());
                            message = sort.mySort(copySerials, command.getNumber());

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
                            sort.longest(command.getSortType());
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
                        copyMovies = getVideoFilters.movieFilteres(myInput.getMoviesData(),
                                filterOne, filterTwo, year, genre);
                        if (criteria.equals("ratings")) {
                            RatingVideo ratingVideo = new RatingVideo();
                            for (MyMovie movie : copyMovies) {
                                movie.setRating(ratingVideo.myRating(movie));
                            }
                            SortMovie sort = new SortMovie();
                            copyMovies.removeIf((v) -> v.getRating() == 0);
                            sort.sortRating(command.getSortType());
                            message = sort.mySort(copyMovies, command.getNumber());
                        } else if (criteria.equals("favorite")) {
                            GetFavorite getFavorite = new GetFavorite();
                            getFavorite.numberFavoritesMovie(myInput.getUsersData(), copyMovies);
                            SortMovie sort = new SortMovie();
                            copyMovies.removeIf((v) -> v.getNumberOfFavorites() == 0);
                            sort.sortFavorites(command.getSortType());
                            message = sort.mySort(copyMovies, command.getNumber());
                        } else if (criteria.equals("longest")) {
                            SortMovie sort = new SortMovie();
                            sort.longest(command.getSortType());
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
                    sort.sortNumberOfRatings(command.getSortType());
                    message = sort.mySort(copyUsers, command.getNumber());
                }
            } else if (actionType.equals("recommendation")) {
                String action = new String();
                action = command.getType();
                String usernamer = new String();
                usernamer = command.getUsername();
                MyUser newUserr = new MyUser();
                int ok = 0;
                for (MyUser user : myInput.getUsersData()) {
                    if (user.getUsername().equals(usernamer)) {
                        newUserr = user;
                        ok = 1;
                        break;
                    }
                }
                if (ok == 1) {
                    if (action.equals("standard")) {
                        GetVideoNotSeen getVideoNotSeen = new GetVideoNotSeen();
                        String recommendation = getVideoNotSeen.standard(newUserr,
                                myInputLoader.getMoviesData(), myInputLoader.getSerialsData());
                        if (recommendation != " ") {
                            message = "StandardRecommendation result: " + recommendation;
                        } else {
                            message = "StandardRecommendation cannot be applied!";
                        }
                    } else if (action.equals("best_unseen")) {
                        RatingVideo ratingVideo = new RatingVideo();
                        ArrayList<MyVideo> newVideosRating = new ArrayList<>();
                        ArrayList<MyVideo> newVideos = new ArrayList<>();
                        SortVideo sortVideo = new SortVideo();
                        sortVideo.sortRating();
                        String recommendation = new String();
                        recommendation = " ";
                        for (MyMovie movie : myInput.getMoviesData()) {
                            movie.setRating(ratingVideo.myRating(movie));
                            newVideos.add(movie);
                            if (movie.getRating() != 0) {
                                newVideosRating.add(movie);
                            }
                        }
                        for (MySerial serial : myInput.getSerialsData()) {
                            serial.setRating(ratingVideo.myRating(serial));
                            newVideos.add(serial);
                            if (serial.getRating() != 0) {
                                newVideosRating.add(serial);
                            }
                        }
                        sortVideo.mySort(newVideosRating);
                        for (MyVideo video : newVideosRating) {
                            if (!newUserr.getHistory().containsKey(video.getTitle())) {
                                recommendation = video.getTitle();
                                break;
                            }
                        }
                        if (recommendation == " ") {
                            for (MyVideo video : newVideos) {
                                if (!newUserr.getHistory().containsKey(video.getTitle())) {
                                    recommendation = video.getTitle();
                                    break;
                                }
                            }
                        }
                        if (recommendation != " ") {
                            message = "BestRatedUnseenRecommendation result: " + recommendation;
                        } else {
                            message = "BestRatedUnseenRecommendation cannot be applied!";
                        }
                    } else if (action.equals("popular")) {
                        if (!newUserr.getSubscriptionType().equals("PREMIUM")) {
                            message = "PopularRecommendation cannot be applied!";
                        } else {
                            String recommendation = new String();
                            recommendation = " ";
                            ArrayList<MyVideo> newVideosViews = new ArrayList<>();
                            GetViews getViews = new GetViews();
                            getViews.numberViewsMovie(myInput.getUsersData(),
                                    myInput.getMoviesData());
                            for (MyMovie movie : myInput.getMoviesData()) {
                                newVideosViews.add(movie);
                            }
                            getViews.numberViewsSerial(myInput.getUsersData(),
                                    myInput.getSerialsData());
                            for (MySerial serial : myInput.getSerialsData()) {
                                newVideosViews.add(serial);
                            }
                            int i = 0;
                            List<MyGenre> newGenre = new ArrayList<>();
                            for (Genre genre : Genre.values()) {
                                MyGenre myGenre = new MyGenre(genre.name(),
                                        null, 0);
                                newGenre.add(myGenre);
                                ArrayList<MyVideo> videos = new ArrayList<>();
                                int numberOfViews = 0;
                                for (MyVideo video : newVideosViews) {
                                    for (String genreVideoName : video.getGenres()) {
                                        if (genreVideoName.equalsIgnoreCase(genre.name())) {
                                            numberOfViews += video.getNumberOfViews();
                                            videos.add(video);
                                        }
                                    }
                                }
                                newGenre.get(i).setNumberOfViews(numberOfViews);
                                newGenre.get(i).setVideos(videos);
                                i++;
                            }
                            SortGenre sortGenre = new SortGenre();
                            sortGenre.sort();
                            sortGenre.mySort(newGenre);
                            newGenre.removeIf((v) -> v.getNumberOfViews() == 0);
                            for (MyGenre genre : newGenre) {
                                for (MyVideo video : genre.getVideos()) {
                                    if (!newUserr.getHistory().containsKey(video.getTitle())
                                            && video.getNumberOfViews() != 0) {
                                        recommendation = video.getTitle();
                                        break;
                                    }
                                }
                            }
                            if (recommendation != " ") {
                                message = "PopularRecommendation result: " + recommendation;
                            } else {
                                message = "PopularRecommendation cannot be applied!";
                            }
                        }
                    } else if (action.equals("favorite")) {
                        if (!newUserr.getSubscriptionType().equals("PREMIUM")) {
                            message = "FavoriteRecommendation cannot be applied!";
                        } else {
                            String recommendation = new String();
                            recommendation = " ";
                            ArrayList<MyVideo> newVideosFavorite = new ArrayList<>();
                            GetFavorite getFavorite = new GetFavorite();
                            getFavorite.numberFavoritesMovie(myInput.getUsersData(),
                                    myInput.getMoviesData());
                            for (MyMovie movie : myInput.getMoviesData()) {
                                if (movie.getNumberOfFavorites() != 0) {
                                    newVideosFavorite.add(movie);
                                }
                            }
                            getFavorite.numberFavoritesSerial(myInput.getUsersData(),
                                    myInput.getSerialsData());
                            for (MySerial serial : myInput.getSerialsData()) {
                                if (serial.getNumberOfFavorites() != 0) {
                                    newVideosFavorite.add(serial);
                                }
                            }
                            SortVideo sortVideo = new SortVideo();
                            sortVideo.sortFavorite();
                            sortVideo.mySort(newVideosFavorite);
                            for (MyVideo video : newVideosFavorite) {
                                if (!newUserr.getHistory().containsKey(video.getTitle())) {
                                    recommendation = video.getTitle();
                                    break;
                                }
                            }
                            if (recommendation != " ") {
                                message = "FavoriteRecommendation result: " + recommendation;
                            } else {
                                message = "FavoriteRecommendation cannot be applied!";
                            }
                        }
                    } else if (action.equals("search")) {
                        if (!newUserr.getSubscriptionType().equals("PREMIUM")) {
                            message = "SearchRecommendation cannot be applied!";
                        } else {
                            String genre = new String();
                            genre = command.getGenre();
                            final String getGendre = genre;
                            RatingVideo ratingVideo = new RatingVideo();
                            ArrayList<MyVideo> videoSearch = new ArrayList<>();
                            for (MyMovie movie : myInput.getMoviesData()) {
                                movie.setRating(ratingVideo.myRating(movie));
                                videoSearch.add(movie);
                            }
                            for (MySerial serial : myInput.getSerialsData()) {
                                serial.setRating(ratingVideo.myRating(serial));
                                videoSearch.add(serial);
                            }
                            videoSearch.removeIf((v) -> !v.getGenres().contains(getGendre));
                            ArrayList<String> recommendation = new ArrayList<>();
                            SortVideo sortVideo = new SortVideo();
                            sortVideo.sortSearch();
                            sortVideo.mySort(videoSearch);
                            for (MyVideo video : videoSearch) {
                                if (!newUserr.getHistory().containsKey(video.getTitle())) {
                                    recommendation.add(video.getTitle());
                                }
                            }
                            if (recommendation.size() != 0) {
                                message = "SearchRecommendation result: " + recommendation;
                            } else {
                                message = "SearchRecommendation cannot be applied!";
                            }
                        }
                    }
                } else {
                    if (action.equals("standard")) {
                        message = "StandardRecommendation cannot be applied!";
                    } else if (action.equals("best_unseen")) {
                        message = "BestRatedUnseenRecommendation cannot be applied!";
                    } else if (action.equals("popular")) {
                        message = "PopularRecommendation cannot be applied!";
                    } else if (action.equals("favorite")) {
                        message = "FavoriteRecommendation cannot be applied!";
                    } else if (action.equals("search")) {
                        message = "SearchRecommendation cannot be applied!";
                    }
                }
            }
            arrayResult.add(fileWriter.writeFile(command.getActionId(),
                    message));
        }

        fileWriter.closeJSON(arrayResult);
    }
}
