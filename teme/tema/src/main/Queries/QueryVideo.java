package main.Queries;

import fileio.ActionInputData;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Get.GetFavorite;
import main.Get.GetVideoFilters;
import main.Get.RatingVideo;
import main.Get.TimeSerial;
import main.MyInput;
import main.Queries.Videos.SortMovie;
import main.Queries.Videos.SortSerial;

import java.util.ArrayList;

/**
 * class for query video
 */
public class QueryVideo {
    /**
     * new class for query video
     */
    public QueryVideo() {

    }

    /**
     * @param command
     * @param action
     * @param myInput
     * @param criteria
     * @return
     */
    public String getQueryVideo(final ActionInputData command, final String action,
                                final MyInput myInput, final String criteria) {
        int filterOne;
        int filterTwo;
        filterOne = 0;
        filterTwo = 0;
        String year = new String();
        String genre = new String();
        String message = new String();
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
                copyMovies.removeIf((v) -> v.getNumberOfViews() == 0);
                SortMovie sort = new SortMovie();
                sort.mostViewed(command.getSortType());
                message = sort.mySort(copyMovies, command.getNumber());
            }
        }
        return message;
    }
}
