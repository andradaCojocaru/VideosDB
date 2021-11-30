package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

/**
 * class that sets number of vies
 */
public class GetViews {
    /**
     * new class GetViews
     */
    public GetViews() {

    }

    /**
     * @param users
     * @param movies
     */
    public void numberViewsMovie(final List<MyUser> users,
                                 final List<MyMovie> movies) {
        for (MyUser user : users) {
            for (MyMovie movie : movies) {
                int sum;
                int previewsValue;
                sum = 0;
                if (user.getHistory().containsKey(movie.getTitle())) {
                    sum += user.getHistory().get(movie.getTitle());
                }
                previewsValue = movie.getNumberOfViews();
                movie.setNumberOfViews(previewsValue + sum);
            }
        }
    }

    /**
     * @param users
     * @param serials
     */
    public void numberViewsSerial(final List<MyUser> users,
                                  final List<MySerial> serials) {
        for (MyUser user : users) {
            for (MySerial serial : serials) {
                int sum;
                int previewsValue;
                sum = 0;
                if (user.getHistory().containsKey(serial.getTitle())) {
                    sum += user.getHistory().get(serial.getTitle());
                }
                previewsValue = serial.getNumberOfViews();
                serial.setNumberOfViews(previewsValue + sum);
            }
        }
    }
}
