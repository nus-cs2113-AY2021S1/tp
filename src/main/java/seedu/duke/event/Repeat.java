package seedu.duke.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Repeat {
    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";
    public static final String MONTHLY = "MONTHLY";

    private ArrayList<DateStatusPair> repeatList = new ArrayList<>();
    private String repeatType;

    public Repeat(LocalDate startDate, LocalTime startTime, String repeatType, int count) {
        for (int i = 1; i <= count; i++) {
            LocalDate repeatDate;
            if (repeatType.equals(MONTHLY)) {
                repeatDate = startDate.plusMonths(i);
            } else if (repeatType.equals(WEEKLY)) {
                repeatDate = startDate.plusWeeks(i);
            } else {
                repeatDate = startDate.plusDays(i);
            }
            DateStatusPair pair = new DateStatusPair(repeatDate, startTime);
            repeatList.add(pair);
        }
        this.repeatType = repeatType;
    }

    public ArrayList<DateStatusPair> getRepeatList() {
        return repeatList;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public int getRepeatCount() {
        return repeatList.size();
    }
}
