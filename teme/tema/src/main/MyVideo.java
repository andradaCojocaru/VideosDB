package main;

import java.util.ArrayList;

public class MyVideo {
    String title;
    int year;
    ArrayList<String> cast;
    ArrayList<String> genres;

    MyVideo(String title, int year, ArrayList<String> cast,
            ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
    }

    public MyVideo() {
    }
}
