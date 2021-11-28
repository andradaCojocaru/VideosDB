package main.Get;

import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilterActors {
    public FilterActors() {

    }
    public ArrayList<MyActor> myFilterActor (List<MyActor> actors, List<String> filters) {
        ArrayList<MyActor> copyActor = new ArrayList<>();
        for (MyActor actor : actors) {
            int ok = 0;
            if (actor.getCareerDescription() != "") {
                String[] arr = actor.getCareerDescription().split(" ");
                for (int i = 0; i < filters.size(); i++) {
                    ok = 0;
                    for (String word : arr) {
                        if (word.equalsIgnoreCase(filters.get(i))) {
                            ok = 1;
                        }
                    }
                    if (ok == 0) {
                        break;
                    }
                }
                if (ok == 1) {
                    copyActor.add(actor);
                }
            }
        }
        return copyActor;
    }
}
