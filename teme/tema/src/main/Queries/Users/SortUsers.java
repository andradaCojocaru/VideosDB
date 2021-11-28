package main.Queries.Users;

import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.ArrayList;
import java.util.Comparator;

public class SortUsers {
    public SortUsers() {

    }
    Comparator<MyUser> cmp;

    public void SortNumberOfRatings(String ordonation) {
        if (ordonation.equals("asc")) {
            cmp = (o1, o2) -> Integer.compare(o1.getNumberOfRatings(), o2.getNumberOfRatings());
            cmp = cmp.thenComparing((o1, o2) -> o1.getUsername().compareTo(o2.getUsername()));
        } else {
            cmp = (o2, o1) -> Integer.compare(o1.getNumberOfRatings(), o2.getNumberOfRatings());
            cmp = cmp.thenComparing((o2, o1) -> o1.getUsername().compareTo(o2.getUsername()));
        }
    }

    public String mySort (ArrayList<MyUser> users, int number) {
        ArrayList<String> names = new ArrayList<>();

        users.sort(cmp);
        for (MyUser user : users) {
            if (number != 0) {
                names.add(user.getUsername());
                number -= 1;
            } else {
                break;
            }
        }
        return "Query result: " + names;
    }
}
