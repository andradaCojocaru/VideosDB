package main.Queries.Actors;

import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.Comparator;

public class SortActors {
    public SortActors() {

    }
    Comparator<MyActor> cmp;
    public void SortAverage(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Double.compare(o1.getAverage(), o2.getAverage());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else {
            cmp = (o2, o1) -> Double.compare(o1.getAverage(), o2.getAverage());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        }
    }

    public void SortAwards(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfAwards(), o2.getNumberOfAwards());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfAwards(), o2.getNumberOfAwards());
            cmp = cmp.thenComparing((o1, o2) -> o1.getName().compareTo(o2.getName()));
        }
    }

    public void SortFilters(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> o1.getName().compareTo(o2.getName());
        } else {
            cmp = (o2, o1) -> o1.getName().compareTo(o2.getName());
        }
    }

    public String mySort (ArrayList<MyActor> actors, int number) {
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
