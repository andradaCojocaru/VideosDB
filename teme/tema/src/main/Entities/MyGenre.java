package main.Entities;

import java.util.ArrayList;

public class MyGenre {
    String name;
    ArrayList<MyVideo> videos;
    int numberOfViews;

    public MyGenre() {

    }

    public MyGenre(final String name, final ArrayList<MyVideo> videos, final int numberOfViews) {
        this.name = name;
        this.videos = videos;
        this.numberOfViews = numberOfViews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MyVideo> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<MyVideo> videos) {
        this.videos = videos;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }
}
