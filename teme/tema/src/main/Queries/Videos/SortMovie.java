package main.Queries.Videos;

import main.Entities.MyMovie;

import java.util.ArrayList;
import java.util.Comparator;

public class SortMovie {
    public SortMovie() {

    }
    Comparator<MyMovie> cmp;
    public void SortRating(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Double.compare(o1.getRating(), o2.getRating());
        } else {
            cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
        }
    }

    public void sortFavorites(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
        }
    }

    public void Longest(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getDuration(), o2.getDuration());
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getDuration(), o2.getDuration());
        }
    }

    public void mostViewed(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
        }
    }
    public String mySort (ArrayList<MyMovie> movies, int number) {
        ArrayList<String> names = new ArrayList<>();

        movies.sort(cmp);
        for (MyMovie movie : movies) {
            if (number != 0) {
                names.add(movie.getTitle());
                number -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
