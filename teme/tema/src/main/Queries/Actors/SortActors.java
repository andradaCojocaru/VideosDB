package main.Queries.Actors;

import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * class for sort actors
 */
public class SortActors {
    /**
     * new class sort actors
     */
    public SortActors() {

    }
    @lombok.Setter
    @lombok.Getter
    private Comparator<MyActor> cmp;

    /**
     * @param ordonation
     */
    public void sortAverage(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Double.compare(o1.getAverage(), o2.getAverage());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else {
            cmp = (o2, o1) -> Double.compare(o1.getAverage(), o2.getAverage());
            cmp = cmp.thenComparing((o2, o1) -> o1.getName().compareTo(o2.getName()));
        }
    }

    /**
     * @param ordonation
     */
    public void sortAwards(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfAwards(), o2.getNumberOfAwards());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfAwards(), o2.getNumberOfAwards());
            cmp = cmp.thenComparing((o2, o1) -> o1.getName().compareTo(o2.getName()));
        }
    }

    /**
     * @param ordonation
     */
    public void sortFilters(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> o1.getName().compareTo(o2.getName());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else {
            cmp = (o2, o1) -> o1.getName().compareTo(o2.getName());
            cmp = cmp.thenComparing((o2, o1) -> o1.getName().compareTo(o2.getName()));
        }
    }

    /**
     * @param actors
     * @param number
     * @return
     */
    public String mySort(final ArrayList<MyActor> actors, int number) {
        ArrayList<String> names = new ArrayList<>();

        actors.sort(cmp);
        for (MyActor actor : actors) {
            if (number != 0) {
                names.add(actor.getName());
                number -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }

}
