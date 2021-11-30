package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

/**
 * search for videos not seen
 */
public class GetVideoNotSeen {
    /**
     * new GetVideoNotSeen class
     */
    public GetVideoNotSeen() {

    }

    /**
     * @param user
     * @param movies
     * @param serials
     * @return
     */
    public String standard(final MyUser user, final List<MyMovie> movies,
                           final List<MySerial> serials) {
        for (MyMovie movie : movies) {
            if (!user.getHistory().containsKey(movie.getTitle())) {
                return movie.getTitle();
            }
        }
        for (MySerial serial : serials) {
            if (!user.getHistory().containsKey(serial.getTitle())) {
                return serial.getTitle();
            }
        }
        return " ";
    }
}
