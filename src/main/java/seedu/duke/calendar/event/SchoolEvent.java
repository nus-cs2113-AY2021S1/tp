package seedu.duke.calendar.event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a school based event.
 */
public abstract class SchoolEvent extends Event {

    protected String moduleCode;

    public SchoolEvent(String moduleCode, LocalDate date, LocalTime time, String venue) {
        super(date, time, venue);
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the status of the task and the description of the task.
     */
    @Override
    public String toString() {
        return moduleCode + " " + super.toString();
    }

    @Override
    public String getDescription() {
        return moduleCode + " " + super.getDescription();
    }

    @Override
    public String getRecurringDescription() {
        return moduleCode + " " + super.getRecurringDescription();
    }

    public abstract String printIntoFile();
}
