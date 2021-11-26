package main;

import java.util.ArrayList;
import java.util.Map;

public class View {
    public View() {

    }

    public String getMessage(Map<String,Integer> history, String movie) {
        int value = 0;
        if (history.containsKey(movie) == true) {
            value = history.get(movie);
        }
        value += 1;
        history.put(movie, value);
        return "success -> " + movie + " was viewed with total views of " + value;
    }

}
