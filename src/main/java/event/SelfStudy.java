package event;

import exception.EndBeforeStartEventException;
import location.Location;
import location.OnlineLocation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelfStudy extends PersonalEvent {
    public SelfStudy(String description, Location location, LocalDateTime at) {
        super(description, location, at);
    }

    public SelfStudy(String description, Location location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location, at, end);
    }

    public SelfStudy(String description, OnlineLocation location, LocalDateTime at) {
        super(description, location, at);
    }

    public SelfStudy(String description, OnlineLocation location, LocalDateTime at, LocalDateTime end)
            throws EndBeforeStartEventException {
        super(description, location, at, end);
    }


}
