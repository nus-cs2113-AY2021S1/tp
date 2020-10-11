package seedu.duke.timeTable;

import java.util.ArrayList;
import java.util.List;

public class Lesson extends Event{


    public Lesson(String moduleCode, String linkOrVenue, boolean isOnline){
        super(moduleCode, isOnline,linkOrVenue);
    }

}
