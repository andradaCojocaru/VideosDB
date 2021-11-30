package main.Queries;

import fileio.ActionInputData;
import main.Entities.MyUser;
import main.MyInput;
import main.Queries.Users.SortUsers;

import java.util.ArrayList;

/**
 * class for query users
 */
public class QueryUsers {
    /**
     * new class for QueryUsers
     */
    public QueryUsers() {

    }

    /**
     * @param myInput
     * @param command
     * @return
     */
    public String getQueryusers(final MyInput myInput,
                                final ActionInputData command) {
        String message = new String();
        ArrayList<MyUser> copyUsers = new ArrayList<>();
        for (MyUser user : myInput.getUsersData()) {
            copyUsers.add(user);
        }
        SortUsers sort = new SortUsers();
        copyUsers.removeIf((v) -> v.getNumberOfRatings() == 0);
        sort.sortNumberOfRatings(command.getSortType());
        message = sort.mySort(copyUsers, command.getNumber());
        return message;
    }
}
