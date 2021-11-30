package main.Get;

import main.Entities.MySeason;
import main.Entities.MySerial;

import java.util.List;

/**
 * class for sum of time serial
 */
public class TimeSerial {
    /**
     * new class TimeSerial
     */
    public TimeSerial() {

    }

    /**
     * @param serials
     */
    public void getTimeSerial(final List<MySerial> serials) {
        for (MySerial serial : serials) {
            int sum;
            sum = 0;
            for (MySeason season : serial.getSeasons()) {
                sum += season.getDuration();
            }
            serial.setTimeSum(sum);
        }
    }
}
