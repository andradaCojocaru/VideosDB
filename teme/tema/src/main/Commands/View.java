package main.Commands;

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
                             final String movie) {
        int value = 0;
        if (history.containsKey(movie)) {
            value = history.get(movie);
        }
        value += 1;
        history.put(movie, value);
        return "success -> " + movie + " was viewed with total views of " + value;
    }

}
