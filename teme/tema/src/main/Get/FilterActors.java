package main.Get;

import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.List;

public class FilterActors {
    public FilterActors() {

    }
    public ArrayList<MyActor> myFilterActor (List<MyActor> actors, List<String> filters) {
        ArrayList<MyActor> copyActor = new ArrayList<>();
        for (MyActor actor : actors) {
            int ok = 0;
            for (int i = 0; i < filters.size(); i++) {
                if(actor.getCareerDescription().contains(filters.get(i))) {
                    ok = 1;
                }
                if (ok == 0) {
                    break;
                }
                ok = 0;
            }
            if (ok == 1) {
                copyActor.add(actor);
            }
        }
        return copyActor;
    }
}
