package main.Recommendations;

import fileio.ActionInputData;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;
import main.Entities.MyVideo;
import main.Get.RatingVideo;
import main.MyInput;

import java.util.ArrayList;

/**
 * class for recommendation search
 */
public class RecommendationArray {
    /**
     * new class for RecommendationArray
     */
    public RecommendationArray() {

    }

    /**
     * @param myUser
     * @param command
     * @param myInput
     * @return
     */
    public String getRecommendationArray(final MyUser myUser, final ActionInputData command,
                                        final MyInput myInput) {
        String message = new String();
        if (!myUser.getSubscriptionType().equals("PREMIUM")) {
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
                if (!myUser.getHistory().containsKey(video.getTitle())) {
                    recommendation.add(video.getTitle());
                }
            }
            if (recommendation.size() != 0) {
                message = "SearchRecommendation result: " + recommendation;
            } else {
                message = "SearchRecommendation cannot be applied!";
            }
        }
        return message;
    }
}
