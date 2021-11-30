package main.Recommendations;

import main.Entities.MyGenre;
import main.Entities.MyVideo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortGenre {
    public SortGenre() {

    }
    Comparator<MyGenre> cmp;
    public void Sort() {
        cmp = (o2, o1) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
    }

    public void mySort (List<MyGenre> genres) {
        genres.sort(cmp);
    }
}
