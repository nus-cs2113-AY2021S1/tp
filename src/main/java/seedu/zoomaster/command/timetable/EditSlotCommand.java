package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Parser;
import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditSlotCommand extends Command {
    public static final String EDIT_KW = "edit";

    public static final String FIELD_KW_MODULE = "module";
    public static final String FIELD_KW_TITLE = "title";
    public static final String FIELD_KW_DAY = "day";
    public static final String FIELD_KW_TIME = "time";

    private String fieldToEdit;
    private String details;

    public EditSlotCommand(String command) throws ZoomasterException {
        assert command.startsWith(EDIT_KW);

        // details: "time 10:00 12:00"
        details = command.substring(EDIT_KW.length()).trim();

        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, EDIT_KW);
        }

        Pattern parser = Pattern.compile(
                "(?<fieldToEdit>[a-zA-Z]+) (?<details>.+)"
        );
        Matcher matcher = parser.matcher(details);

        if (!matcher.matches()) {
            // TODO: Make new exception type for this
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_SLOT_INPUT);
        }

        fieldToEdit = matcher.group("fieldToEdit");
        details = matcher.group("details");
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        Pattern parser = Pattern.compile(
                "(?<day>[a-zA-Z]+) (?<indexInDay>\\d+) (?<firstArgument>[\\w:]+)( (?<secondArgument>[\\w:]*))?"
        );
        Matcher matcher = parser.matcher(details);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_SLOT_INPUT);
        }

        String day = matcher.group("day");
        int indexInDay = Integer.parseInt(matcher.group("indexInDay"));

        Slot slot = timetable.getSlotByIndexInDay(day, indexInDay);

        switch (fieldToEdit) {
        case FIELD_KW_MODULE:
            timetable.moveSlotToAnotherModule(day, indexInDay, matcher.group("firstArgument"));
            break;
        case FIELD_KW_TITLE:
            slot.setTitle(matcher.group("firstArgument"));
            break;
        case FIELD_KW_DAY:
            slot.setDay(matcher.group("firstArgument"));
            break;
        case FIELD_KW_TIME:
            changeSlotTime(slot, matcher.group("firstArgument"), matcher.group("secondArgument"));
            break;
        default:
            break;
        }

        ui.print("Slot has been edited: " + slot.toString() + System.lineSeparator());
    }

    /**
     * Change a slot's start and end time with the given user input.
     *
     * @param slot The slot object with the day we want to change.
     * @param startTimeInput The new start time as string.
     * @param endTimeInput The new end time as string.
     */
    private void changeSlotTime(Slot slot, String startTimeInput, String endTimeInput) throws ZoomasterException {
        LocalTime startTime;
        LocalTime endTime;

        try {
            startTime = LocalTime.parse(startTimeInput);
            endTime = LocalTime.parse(endTimeInput);
        } catch (DateTimeParseException e) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIME_FORMAT);
        }

        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
    }
}
