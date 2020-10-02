package seedu.duke.tysPackage;

import java.time.LocalTime;

public class Timetable {
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String day;

    public Timetable(LocalTime startTimeInput, LocalTime endTimeInput, String dayInput, String titleInput) {
        startTime = startTimeInput;
        endTime = endTimeInput;
        title = titleInput;
        day = dayInput;
    }

    public void setStartTime(LocalTime timeInput) {
        startTime = timeInput;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalTime timeInput) {
        endTime = timeInput;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setTitle(String titleInput) {
        title = titleInput;
    }

    public String getTitle() {
        return title;
    }

    public void setDay(String dayInput) {
        day = dayInput;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString(){
        return String.format(startTime.toString() + "-" + endTime.toString() + " " + title);
    }
}
