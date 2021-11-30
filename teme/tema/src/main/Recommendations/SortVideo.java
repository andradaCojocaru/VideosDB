package main.Recommendations;

import main.Entities.MyVideo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * class for sort videos
 */
public class SortVideo {
    /**
     * new class SortVideo
     */
    public SortVideo() {

    }
    private Comparator<MyVideo> cmp;

    /**
     *
     */
    public void sortRating() {
        cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
    }

    /**
     *
     */
    public void sortFavorite() {
        cmp = (o2, o1) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
    }

    /**
     *
     */
    public void sortSearch() {
        cmp = (o1, o2) -> o1.getTitle().compareTo(o2.getTitle());
        cmp = cmp.thenComparing((o2, o1) -> Double.compare(o1.getRating(), o2.getRating()));
    }

    /**
     * @param videos
     */
    public void mySort(final ArrayList<MyVideo> videos) {
        videos.sort(cmp);
    }
}
