package main.Recommendations;

import main.Entities.MyGenre;
import java.util.Comparator;
import java.util.List;

/**
 * class for sort genre
 */
public class SortGenre {
    /**
     * new class SortGenre
     */
    public SortGenre() {

    }
    private Comparator<MyGenre> cmp;

    /**
     *
     */
    public void sort() {
        cmp = (o2, o1) -> Integer.compare(o1.getNumberOfViews(), o2.getNumberOfViews());
    }

    /**
     * @param genres
     */
    public void mySort(final List<MyGenre> genres) {
        genres.sort(cmp);
    }
}
