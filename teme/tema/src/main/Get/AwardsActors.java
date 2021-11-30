package main.Get;

import actor.ActorsAwards;
import main.Entities.MyActor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class that setNumberOfAwards
 */
public class AwardsActors {
    /**
     * new class AwardsActors
     */
    public AwardsActors() {

    }

    /**
     * @param actors
     * @param awards
     * @return
     */
    public ArrayList<MyActor> myAwardsActor(final List<MyActor> actors, final List<String> awards) {
        ArrayList<MyActor> copyActor = new ArrayList<>();
        for (MyActor actor : actors) {
            int sum = 0;
            int ok = 0;
            for (int i = 0; i < awards.size(); i++) {
                ok = 0;
                for (Map.Entry<ActorsAwards, Integer> entry : actor.getAwards().entrySet()) {
                    sum += entry.getValue();
                    if (((entry.getKey()).toString()).equals(awards.get(i))) {
                        ok = 1;
                    }
                }
                if (ok == 0) {
                    break;
                }
            }
            if (ok == 1) {
                actor.setNumberOfAwards(sum);
                copyActor.add(actor);
            }
        }
        return copyActor;
    }
}
