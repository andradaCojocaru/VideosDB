package main.Get;

import main.Entities.MyMovie;
import main.Entities.MySerial;
import main.Entities.MyUser;

import java.util.List;

public class GetViews {
    public GetViews () {

    }
    public void numberViewsMovie(List<MyUser> users, List<MyMovie> movies) {
        for (MyUser user : users) {
            for (MyMovie movie : movies) {
                int sum;
                int previewsValue;
                sum = 0;
                previewsValue = 0;
                if (user.getHistory().containsKey(movie.getTitle())) {
                    sum += user.getHistory().get(movie.getTitle());
                }
                previewsValue = movie.getNumberOfViews();
                movie.setNumberOfViews(previewsValue + sum);
            }
        }
    }

    public void numberViewsSerial(List<MyUser> users, List<MySerial> serials) {
        for (MyUser user : users) {
            for (MySerial serial : serials) {
                int sum;
                int previewsValue;
                sum = 0;
                previewsValue = 0;
                if (user.getHistory().containsKey(serial.getTitle())) {
                    sum += user.getHistory().get(serial.getTitle());
                }
                previewsValue = serial.getNumberOfViews();
                serial.setNumberOfViews(previewsValue + sum);
            }
        }
    }
}
