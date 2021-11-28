package main.Entities;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Map;

/**
 * Information about an actor, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MyActor {
    /**
     * actor name
     */
    @lombok.Setter
    @lombok.Getter
    private String name;
    /**
     * description of the actor's career
     */
    @lombok.Setter
    @lombok.Getter
    private String careerDescription;
    /**
     * videos starring actor
     */
    @lombok.Setter
    @lombok.Getter
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    @lombok.Getter
    private Map<ActorsAwards, Integer> awards;

    @lombok.Setter
    @lombok.Getter
    private double average;

    @lombok.Setter
    @lombok.Getter
    private int numberOfAwards;

    public MyActor(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards,
                          final double average, final int numberOfAwards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.average = average;
        this.numberOfAwards = numberOfAwards;
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }

}

