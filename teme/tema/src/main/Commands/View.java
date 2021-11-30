package main.Commands;

import main.Entities.MyMovie;
import main.Entities.MySerial;

import java.util.Map;

/**
 * class that makes the view command
 */
public class View {

    /**
     * for new classes
     */
    public View() {

    }

    /**
     * @param history
     * @param movie
     * @return
     */
    public String getMessage(final Map<String, Integer> history,
                             final MyMovie movie) {
        int value = 0;
        if (history.containsKey(movie.getTitle())) {
            value = history.get(movie.getTitle());
        }
        value += 1;
        history.put(movie.getTitle(), value);
        int nr = movie.getNumberOfViews();
        movie.setNumberOfViews(nr + 1);
        return "success -> " + movie.getTitle() + " was viewed with total views of " + value;
    }

    /**
     * @param history
     * @param serial
     * @return
     */
    public String getMessage(final Map<String, Integer> history,
                             final MySerial serial) {
        int value = 0;
        if (history.containsKey(serial.getTitle())) {
            value = history.get(serial.getTitle());
        }
        value += 1;
        history.put(serial.getTitle(), value);
        int nr = serial.getNumberOfViews();
        serial.setNumberOfViews(nr + 1);
        return "success -> " + serial.getTitle() + " was viewed with total views of " + value;
    }

}
