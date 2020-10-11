package seedu.duke.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    public String eventName;
    public String eventTime;
    protected LocalDate date;


    public Event(String name, String Date, String time){
        this.eventName = name;
        this.eventTime = time;
        this.setEventDate(LocalDate.parse(Date));
    }


    public void setEventDate(LocalDate date) {
        this.date = date;
    }


    /**
     * Returns the string format of the event.
     *
     * @return String format of event.
     */
    public String printEvent() {
        return "Event Name: " + this.eventName +  "\nDate: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "\nTime: " + this.eventTime +"\n";
    }

}
