package main.Queries;

import fileio.ActionInputData;
import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Get.AwardsActors;
import main.Get.FilterActors;
import main.Get.RatingActors;
import main.Get.RatingVideo;
import main.MyInput;
import main.Queries.Actors.SortActors;

import java.util.ArrayList;
import java.util.List;

/**
 * class for query actors
 */
public class QueryActors {
    /**
     * new class for QueryActors
     */
    public QueryActors() {

    }

    /**
     * @param criteria
     * @param myInput
     * @param command
     * @return
     */
    public String getQueryActors(final String criteria, final MyInput myInput,
                                 final ActionInputData command) {
        String message = new String();
        if (criteria.equals("average")) {
            RatingVideo newRating = new RatingVideo();
            RatingActors newRatingActor = new RatingActors();
            ArrayList<MyActor> newActors = new ArrayList<>();
            for (MyMovie movie : myInput.getMoviesData()) {
                movie.setRating(newRating.myRating(movie));
            }
            for (MySerial serial : myInput.getSerialsData()) {
                serial.setRating(newRating.myRating(serial));
            }
            for (MyActor actor : myInput.getActorsData()) {
                actor.setAverage(newRatingActor.myRating(actor,
                        myInput.getMoviesData(),
                        myInput.getSerialsData()));
            }
            for (MyActor actor : myInput.getActorsData()) {
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
        return message;
    }
}
