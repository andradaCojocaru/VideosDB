package main.Queries.Videos;

import main.Entities.MyActor;
import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.ArrayList;
import java.util.Comparator;

public class SortSerial {
    public SortSerial() {

    }
    Comparator<MySerial> cmp;
    public void SortRating(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Double.compare(o1.getRating(), o2.getRating());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Double.compare(o1.getRating(), o2.getRating());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    public void sortFavorites(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfFavorites(), o2.getNumberOfFavorites());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    public void Longest(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getTimeSum(), o2.getTimeSum());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getTimeSum(), o2.getTimeSum());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    public void mostViewed(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
            cmp = cmp.thenComparing((o2, o1) -> o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    public String mySort (ArrayList<MySerial> serials, int number) {
        ArrayList<String> names = new ArrayList<>();

        serials.sort(cmp);
        for (MySerial serial : serials) {
            if (number != 0) {
                names.add(serial.getTitle());
                number -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
