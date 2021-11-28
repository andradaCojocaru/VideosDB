package main.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * class for Seasons
 */
public class MySeason {
    @lombok.Getter
    private int currentSeason;
    @lombok.Setter
    @lombok.Getter
    private int duration;
    @lombok.Setter
    @lombok.Getter
    private ArrayList<Double> ratings;

    /**
     * @param currentSeason
     * @param duration
     */
    public MySeason(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
    }
}
