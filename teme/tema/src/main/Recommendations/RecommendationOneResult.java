package main.Recommendations;
import entertainment.Genre;
import main.Entities.MyGenre;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;
import main.Entities.MyVideo;
import main.Get.GetVideoNotSeen;
import main.Get.RatingVideo;
import main.MyInput;

import java.util.ArrayList;
import java.util.List;

/**
 * class for recommendation that gives one entity as result
 */
public class RecommendationOneResult {
    /**
     * new class for RecommendationResult
     */
    public RecommendationOneResult() {

    }

    /**
     * @param myInput
     * @param action
     * @param myUser
     * @return
     */
    public String getRecommendationOneResult(final MyInput myInput, final String action,
                                             final MyUser myUser) {
        String message = new String();
        int ok = 0;
        String type = new String();
        String recommendation = new String();
        if (action.equals("standard")) {
            type = "StandardRecommendation ";
            GetVideoNotSeen getVideoNotSeen = new GetVideoNotSeen();
            recommendation = getVideoNotSeen.standard(myUser,
                    myInput.getMoviesData(), myInput.getSerialsData());
        } else if (action.equals("best_unseen")) {
            type = "BestRatedUnseenRecommendation ";
            RatingVideo ratingVideo = new RatingVideo();
            ArrayList<MyVideo> newVideosRating = new ArrayList<>();
            ArrayList<MyVideo> newVideos = new ArrayList<>();
            SortVideo sortVideo = new SortVideo();
            sortVideo.sortRating();
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
                if (!myUser.getHistory().containsKey(video.getTitle())) {
                    recommendation = video.getTitle();
                    break;
                }
            }
            if (recommendation == " ") {
                for (MyVideo video : newVideos) {
                    if (!myUser.getHistory().containsKey(video.getTitle())) {
                        recommendation = video.getTitle();
                        break;
                    }
                }
            }
        } else if (action.equals("popular")) {
            if (!myUser.getSubscriptionType().equals("PREMIUM")) {
                message = "PopularRecommendation cannot be applied!";
                ok = 1;
            } else {
                type = "PopularRecommendation ";
                recommendation = " ";
                ArrayList<MyVideo> newVideosViews = new ArrayList<>();
                for (MyMovie movie : myInput.getMoviesData()) {
                    newVideosViews.add(movie);
                }
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
                        if (!myUser.getHistory().containsKey(video.getTitle())
                                && video.getNumberOfViews() != 0) {
                            recommendation = video.getTitle();
                            break;
                        }
                    }
                    if (recommendation != " ") {
                        break;
                    }
                }
            }
        } else if (action.equals("favorite")) {
            if (!myUser.getSubscriptionType().equals("PREMIUM")) {
                message = "FavoriteRecommendation cannot be applied!";
                ok = 1;
            } else {
                type = "FavoriteRecommendation ";
                recommendation = " ";
                ArrayList<MyVideo> newVideosFavorite = new ArrayList<>();
                for (MyMovie movie : myInput.getMoviesData()) {
                    if (movie.getNumberOfFavorites() != 0) {
                        newVideosFavorite.add(movie);
                    }
                }
                for (MySerial serial : myInput.getSerialsData()) {
                    if (serial.getNumberOfFavorites() != 0) {
                        newVideosFavorite.add(serial);
                    }
                }
                SortVideo sortVideo = new SortVideo();
                sortVideo.sortFavorite();
                sortVideo.mySort(newVideosFavorite);
                for (MyVideo video : newVideosFavorite) {
                    if (!myUser.getHistory().containsKey(video.getTitle())) {
                        recommendation = video.getTitle();
                        break;
                    }
                    if (recommendation != " ") {
                        break;
                    }
                }
            }
        }
        if (ok != 1) {
            if (recommendation != " ") {
                message = type + "result: " + recommendation;
            } else {
                message = type + "cannot be applied!";
            }
        }
        return message;
    }
}
