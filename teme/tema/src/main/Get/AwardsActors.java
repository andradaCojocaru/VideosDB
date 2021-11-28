package main.Get;

import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.List;

public class AwardsActors {
    public AwardsActors() {

    }
    public ArrayList<MyActor> myAwardsActor(List<MyActor> actors, List<String> awards) {
        ArrayList<MyActor> copyActor = new ArrayList<>();
        for (MyActor actor : actors) {
            int sum = 0;
            int ok = 0;
            for (int i = 0; i < awards.size(); i++) {
                if (actor.getAwards().containsKey(awards.get(i))) {
                    sum += actor.getAwards().get(awards.get(i));
                    ok = 1;
                }
                if (ok == 0) {
                    break;
                }
                ok = 0;
            }
            if (ok == 1) {
                actor.setNumberOfAwards(sum);
                copyActor.add(actor);
            }
        }
        return copyActor;
    }
}
