package main.Queries.Videos;

import main.Entities.MyMovie;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * class for sort movies
 */
public class SortMovie {
    /**
     * new class SortMovie
     */
    public SortMovie() {

    }
    private Comparator<MyMovie> cmp;

    /**
     * @param ordonation
     */
    public void sortRating(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Double.compare(o1.getRating(), o2.getRating());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    /**
     * @param ordonation
     */
    public void sortFavorites(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    /**
     * @param ordonation
     */
    public void longest(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getDuration(), o2.getDuration());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getDuration(), o2.getDuration());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    /**
     * @param ordonation
     */
    public void mostViewed(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    /**
     * @param movies
     * @param number
     * @return
     */
    public String mySort(final ArrayList<MyMovie> movies, final int number) {
        ArrayList<String> names = new ArrayList<>();

        movies.sort(cmp);
        int i = number;
        for (MyMovie movie : movies) {
            if (i != 0) {
                names.add(movie.getTitle());
                i -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
