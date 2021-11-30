package main.Commands;

import fileio.ActionInputData;
import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;
import main.MyInput;

/**
 * class for commands
 */
public class Command {
    /**
     * new class for command
     */
    public Command() {

    }

    /**
     * @param command
     * @param newUser
     * @param myInput
     * @return
     */
    public String getCommand(final ActionInputData command,
                             final MyUser newUser, final MyInput myInput) {
        String action = command.getType();
        String title = command.getTitle();
        String message = new String();
        MyMovie movieFound = new MyMovie();
        MySerial serialFound = new MySerial();
        int okMovie = 0;
        for (MyMovie movie : myInput.getMoviesData()) {
            if (movie.getTitle().equals(title)) {
                movieFound = movie;
                okMovie = 1;
                break;
            }
        }
        if (okMovie == 0) {
            for (MySerial serial : myInput.getSerialsData()) {
                if (serial.getTitle().equals(title)) {
                    serialFound = serial;
                    break;
                }
            }
        }
        if (action.equals("favorite")) {
            Favorite favoriteNew = new Favorite();
            favoriteNew.isSeen(newUser.getHistory(), title);
            favoriteNew.isFavorite(newUser.getFavoriteMovies(),
                    title);
            if (okMovie == 0) {
                message = favoriteNew.getMessage(
                        newUser.getFavoriteMovies(), serialFound);
            } else {
                message = favoriteNew.getMessage(
                        newUser.getFavoriteMovies(), movieFound);
            }
        } else if (action.equals("view")) {
            View viewNew = new View();
            if (okMovie == 0) {
                message = viewNew.getMessage(
                        newUser.getHistory(), serialFound);
            } else {
                message = viewNew.getMessage(
                        newUser.getHistory(), movieFound);
            }
        } else if (action.equals("rating")) {
            Rating rating = new Rating();
            rating.isSeen(newUser.getHistory(), title);
            if (okMovie == 0) {
                message = rating.myRating(serialFound, newUser, command.getGrade(),
                        command.getSeasonNumber());
            } else {
                message = rating.myRating(movieFound, newUser, command.getGrade());
            }
        }
        return message;
    }
}
