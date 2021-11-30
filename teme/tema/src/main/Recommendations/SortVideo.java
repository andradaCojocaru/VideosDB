package main.Recommendations;

import main.Entities.MyMovie;
import main.Entities.MyVideo;

import java.util.ArrayList;
import java.util.Comparator;

public class SortVideo {
    public SortVideo() {

    }
    Comparator<MyVideo> cmp;
    public void SortRating() {
        cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
    }

    public void SortFavorite() {
        cmp = (o2, o1) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
    }

    public void SortSearch() {
        cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
        cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
    }
    public void mySort (ArrayList<MyVideo> videos) {
        videos.sort(cmp);
    }
}
