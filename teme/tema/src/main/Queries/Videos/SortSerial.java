package main.Queries.Videos;

import main.Entities.MySerial;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * class for sort serial
 */
public class SortSerial {
    /**
     * new class SortSerial
     */
    public SortSerial() {

    }
    private Comparator<MySerial> cmp;

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
            cmp = (o1, o2) -> Integer.compare(o1.getTimeSum(), o2.getTimeSum());
            cmp = cmp.thenComparing((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getTimeSum(), o2.getTimeSum());
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
     * @param serials
     * @param number
     * @return
     */
    public String mySort(final ArrayList<MySerial> serials, final int number) {
        ArrayList<String> names = new ArrayList<>();

        serials.sort(cmp);
        int i = number;
        for (MySerial serial : serials) {
            if (i != 0) {
                names.add(serial.getTitle());
                i -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
