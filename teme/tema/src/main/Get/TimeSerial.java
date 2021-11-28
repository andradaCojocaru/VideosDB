package main.Get;

import main.Entities.MySeason;
import main.Entities.MySerial;

import java.io.Serial;
import java.util.List;

public class TimeSerial {
    public TimeSerial() {

    }
    public void getTimeSerial(List<MySerial> serials) {
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
