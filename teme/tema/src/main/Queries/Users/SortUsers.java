package main.Queries.Users;

import main.Entities.MyUser;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * class for sort users
 */
public class SortUsers {
    /**
     * new class SortUsers
     */
    public SortUsers() {

    }
    private Comparator<MyUser> cmp;

    /**
     * @param ordonation
     */
    public void sortNumberOfRatings(final String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfRatings(), o2.getNumberOfRatings());
            cmp = cmp.thenComparing((o1, o2) -> o1.getUsername().compareTo(o2.getUsername()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfRatings(), o2.getNumberOfRatings());
            cmp = cmp.thenComparing((o2, o1) -> o1.getUsername().compareTo(o2.getUsername()));
        }
    }

    /**
     * @param users
     * @param number
     * @return
     */
    public String mySort(final ArrayList<MyUser> users, final int number) {
        ArrayList<String> names = new ArrayList<>();

        users.sort(cmp);
        int i = number;
        for (MyUser user : users) {
            if (i != 0) {
                names.add(user.getUsername());
                i -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
