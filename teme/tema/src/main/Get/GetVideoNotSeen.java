package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

public class GetVideoNotSeen {
    public GetVideoNotSeen() {

    }

    public String standard(MyUser user, List<MyMovie> movies, List<MySerial> serials) {
        for (MyMovie movie : movies) {
            if (user.getHistory().containsKey(movie.getTitle()) == false) {
                return movie.getTitle();
            }
        }
        for (MySerial serial : serials) {
            if (user.getHistory().containsKey(serial.getTitle()) == false) {
                return serial.getTitle();
            }
        }
        return " ";
    }
}
