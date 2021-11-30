package main.Entities;

import java.util.ArrayList;

/**
 * class for my genre
 */
public class MyGenre {
    @lombok.Setter
    @lombok.Getter
    private String name;
    @lombok.Setter
    @lombok.Getter
    private ArrayList<MyVideo> videos;
    @lombok.Setter
    @lombok.Getter
    private int numberOfViews;

    /**
     * new class MyGenre
     */
    public MyGenre() {

    }

    /**
     * @param name
     * @param videos
     * @param numberOfViews
     */
    public MyGenre(final String name, final ArrayList<MyVideo> videos, final int numberOfViews) {
        this.name = name;
        this.videos = videos;
        this.numberOfViews = numberOfViews;
    }

}
