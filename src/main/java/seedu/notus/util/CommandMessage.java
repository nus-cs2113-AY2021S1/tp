package seedu.notus.util;

import com.diogonunes.jcolor.Attribute;
import seedu.notus.command.AddEventCommand;
import seedu.notus.command.AddNoteCommand;
import seedu.notus.command.ArchiveNoteCommand;
import seedu.notus.command.CreateTagCommand;
import seedu.notus.command.DeleteEventCommand;
import seedu.notus.command.DeleteNoteCommand;
import seedu.notus.command.DeleteTagCommand;
import seedu.notus.command.EditEventCommand;
import seedu.notus.command.EditNoteCommand;
import seedu.notus.command.ExitCommand;
import seedu.notus.command.FindCommand;
import seedu.notus.command.ListEventCommand;
import seedu.notus.command.ListNoteCommand;
import seedu.notus.command.ListTagCommand;
import seedu.notus.command.PinCommand;
import seedu.notus.command.RemindCommand;
import seedu.notus.command.TagEventCommand;
import seedu.notus.command.TagNoteCommand;
import seedu.notus.command.UnarchiveNoteCommand;
import seedu.notus.command.ViewNoteCommand;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.RecurringEvent;

import static com.diogonunes.jcolor.Ansi.colorize;

import static seedu.notus.util.PrefixSyntax.PREFIX_ADD_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_CONTENT;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_PIN;
import static seedu.notus.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_ADD;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_CLEAR;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_DROP;
import static seedu.notus.util.PrefixSyntax.PREFIX_STOP_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Contains Command Line Interface (CLI) messages the commands will display.
 */
public class CommandMessage {
    static final Attribute CYAN = Attribute.BRIGHT_CYAN_TEXT();
    static final Attribute WHITE = Attribute.BRIGHT_WHITE_TEXT();
    static final Attribute GREEN = Attribute.GREEN_TEXT();
    static final Attribute RED = Attribute.RED_TEXT();

    /** Command Usages. */
    public static final String ADD_E_COMMAND_USAGE = AddEventCommand.COMMAND_WORD
            + ": Adds an event to the timetable. Parameters:"
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + PREFIX_DELIMITER + PREFIX_TIMING + " TIMING (Format: " + DateTimeManager.DATE_FORMAT + ") "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING
            + String.format(" Frequency (%s, %s, %s, %s)] ",
            RecurringEvent.DAILY_RECURRENCE_TYPE, RecurringEvent.WEEKLY_RECURRENCE_TYPE,
            RecurringEvent.MONTHLY_RECURRENCE_TYPE, RecurringEvent.YEARLY_RECURRENCE_TYPE)
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND + " [Days before (Default: 1)]" + "] "
            + "[" + PREFIX_DELIMITER + PREFIX_STOP_RECURRING + " TIMING (Format: " + DateTimeManager.DATE_FORMAT + ")]";
    public static final String ADD_N_COMMAND_USAGE = AddNoteCommand.COMMAND_WORD
            + ": Adds a note to notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...] "
            + "[" + PREFIX_DELIMITER + PREFIX_PIN + " PIN]";
    public static final String ARCHIVE_N_COMMAND_USAGE = ArchiveNoteCommand.COMMAND_WORD
            + ": Archives a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX]";
    public static final String CREATE_T_COMMAND_USAGE = CreateTagCommand.COMMAND_WORD
            + ": Creates a tag. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG NAME [TAG COLOR] (Available colors: "
            + colorize(Tag.COLOR_WHITE_STRING, Attribute.BRIGHT_WHITE_TEXT()) + ", "
            + colorize(Tag.COLOR_RED_STRING, Attribute.BRIGHT_RED_TEXT()) + ", "
            + colorize(Tag.COLOR_GREEN_STRING, Attribute.BRIGHT_GREEN_TEXT()) + ", "
            + colorize(Tag.COLOR_BLUE_STRING, Attribute.BRIGHT_BLUE_TEXT()) + ", "
            + colorize(Tag.COLOR_YELLOW_STRING, Attribute.BRIGHT_YELLOW_TEXT()) + ", "
            + colorize(Tag.COLOR_MAGENTA_STRING, Attribute.BRIGHT_MAGENTA_TEXT()) + ", "
            + colorize(Tag.COLOR_CYAN_STRING, Attribute.BRIGHT_CYAN_TEXT())
            + colorize(")", Attribute.BRIGHT_WHITE_TEXT());
    public static final String DELETE_E_COMMAND_USAGE = DeleteEventCommand.COMMAND_WORD
            + ": Deletes an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";
    public static final String DELETE_N_COMMAND_USAGE = DeleteNoteCommand.COMMAND_WORD
            + ": Deletes a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE or "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";
    public static final String DELETE_T_COMMAND_USAGE = DeleteTagCommand.COMMAND_WORD
            + ": Deletes a tag. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...]";
    public static final String EDIT_E_COMMAND_USAGE = EditEventCommand.COMMAND_WORD
            + ": Edits an event in the timetable. "
            + "Parameters: " + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_TIMING + " DATE_TIME] "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING + " RECURRING] "
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_ADD + " REMIND]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_DROP + " REMIND]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_CLEAR + "]"
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING + "]"
            + "[" + PREFIX_DELIMITER + PREFIX_STOP_RECURRING + "](Only works when event is / set to a recurring type.)";
    public static final String EDIT_N_COMMAND_USAGE = EditNoteCommand.COMMAND_WORD
            + ": Edits a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "([" + PREFIX_DELIMITER + PREFIX_ADD_LINE + " INDEX STRING] OR "
            + "[" + PREFIX_DELIMITER + PREFIX_LINE + " LINE_INDEX CONTENTS] OR "
            + "[" + PREFIX_DELIMITER + PREFIX_DELETE_LINE + " INDEX]) "
            + "[" + PREFIX_DELIMITER + PREFIX_CONTENT + " CONTENT] "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...] "
            + "Only use one type of /add, /ln or /del per edit.";
    public static final String EXIT_COMMAND_USAGE = ExitCommand.COMMAND_WORD
            + ": Exits the program.";
    public static final String FIND_N_COMMAND_USAGE = FindCommand.COMMAND_WORD
            + ": Finds a note. Parameters: KEYWORDS";
    public static final String LIST_E_COMMAND_USAGE = ListEventCommand.COMMAND_WORD
            + ": List all the events in the Timetable. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TIMING + " YYYY or YYYY-MM]";
    public static final String LIST_N_COMMAND_USAGE = ListNoteCommand.COMMAND_WORD
            + ": Lists all the notes in the Notebook. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1...] "
            + "[/sort up OR down]";
    public static final String LIST_T_COMMAND_USAGE = ListTagCommand.COMMAND_WORD
            + ": Lists all the tags.";
    public static final String PIN_N_COMMAND_USAGE = PinCommand.COMMAND_WORD
            + ": Pins or unpins a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX] "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE]";
    public static final String REMIND_E_COMMAND_USAGE = RemindCommand.COMMAND_WORD
            + ": Shows the reminders for today.";
    public static final String TAG_E_COMMAND_USAGE = TagEventCommand.COMMAND_WORD
            + ": Tags or untags an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";
    public static final String TAG_N_COMMAND_USAGE = TagNoteCommand.COMMAND_WORD
            + ": Tags or untags a note. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";
    public static final String UNARCHIVE_N_COMMAND_USAGE = UnarchiveNoteCommand.COMMAND_WORD
            + ": Un-archives a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX]";
    public static final String VIEW_N_COMMAND_USAGE = ViewNoteCommand.COMMAND_WORD
            + ": Views a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX] "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE]";
    public static final String[] HELP_COMMAND_USAGE = {"The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ], represent optional inputs.",
            colorize(ADD_E_COMMAND_USAGE, WHITE),
            colorize(ADD_N_COMMAND_USAGE, CYAN),
            colorize(ARCHIVE_N_COMMAND_USAGE, WHITE),
            colorize(CREATE_T_COMMAND_USAGE, CYAN),
            colorize(DELETE_E_COMMAND_USAGE, WHITE),
            colorize(DELETE_N_COMMAND_USAGE, CYAN),
            colorize(DELETE_T_COMMAND_USAGE, WHITE),
            colorize(EDIT_E_COMMAND_USAGE, CYAN),
            colorize(EDIT_N_COMMAND_USAGE, WHITE),
            colorize(EXIT_COMMAND_USAGE, CYAN),
            colorize(FIND_N_COMMAND_USAGE, WHITE),
            colorize(LIST_E_COMMAND_USAGE, CYAN),
            colorize(LIST_N_COMMAND_USAGE, WHITE),
            colorize(LIST_T_COMMAND_USAGE, CYAN),
            colorize(PIN_N_COMMAND_USAGE, WHITE),
            colorize(REMIND_E_COMMAND_USAGE, CYAN),
            colorize(TAG_E_COMMAND_USAGE, WHITE),
            colorize(TAG_N_COMMAND_USAGE, CYAN),
            colorize(UNARCHIVE_N_COMMAND_USAGE, WHITE),
            colorize(VIEW_N_COMMAND_USAGE, CYAN)
    };

    /** General successful messages. */
    public static final String EXIT_MESSAGE = "Bye!";

    /** General unsuccessful messages. */
    public static final String INDEX_OUT_OF_RANGE_MESSAGE = colorize("The index you specified is out of range. "
            + "Please check and specify a valid index value.", RED);

    /** Event related messages. */
    public static final String PROCESSING_EDIT_MESSAGE = "Editing event:";
    public static final String REMINDERS_MESSAGE = "Here are the reminders for today!";
    public static final String NO_REMINDERS_MESSAGE = "No reminders today!";
    public static final String LIST_EVENT_SUCCESSFUL_MESSAGE = "These are the stored events: ";
    public static final String LIST_EVENT_SUCCESSFUL_TIME_PERIOD_MESSAGE = "These are the events "
            + "in the specified time period: ";

    public static final String ADD_EVENT_SUCCESSFUL_MESSAGE = colorize("Added the following event:", GREEN);
    public static final String DELETE_EVENT_SUCCESSFUL_MESSAGE = colorize("Event deleted", GREEN);
    public static final String EDIT_TITLE_MESSAGE = colorize("Title edited!", GREEN);
    public static final String EDIT_START_DATE_MESSAGE = colorize("Start Date edited!", GREEN);
    public static final String EDIT_REMINDER_MESSAGE = colorize("Reminders edited!", GREEN);
    public static final String EDIT_RECURRENCE_MESSAGE = colorize("Recurrence type edited!", GREEN);
    public static final String EDIT_RECURRENCE_DATE_MESSAGE = colorize("End recurrence date edited!", GREEN);

    public static final String DELETE_EVENT_UNSUCCESSFUL_MESSAGE = colorize("Event failed to delete", RED);
    public static final String EDIT_EVENT_UNSUCCESSFUL_MESSAGE = colorize("Perhaps try editing something!", RED);
    public static final String EDIT_WARNING_REMINDER_MESSAGE = colorize("There was no changes made. "
            + "Perhaps you tried to add a reminder that already exists or delete reminders that do not exist.", RED);
    public static final String EDIT_WARNING_RECURRENCE_MESSAGE = colorize("The event is of the same recurrence type. "
            + "No changes are made to it's recurrence type.", RED);
    public static final String EDIT_WARNING_RECURRENCE_ON_NON_RECURRENCE_MESSAGE = colorize("You attempted to put a "
            + "recurrence date on a non-recurring event. No recurrence date was set.", RED);
    public static final String LIST_EVENT_UNSUCCESSFUL_MESSAGE = colorize("There are no events stored in the timetable!",
            RED);
    public static final String LIST_EVENT_UNSUCCESSFUL_TIME_PERIOD_MESSAGE = colorize("Failed to find any events "
            + "in the specified time period.", RED);

    /** Note related messages. */
    public static final String PINNED_NOTES_MESSAGE = "Here are the list of pinned notes:";
    public static final String UNPINNED_NOTES_MESSAGE = "Here are the list of unpinned notes:";
    public static final String ARCHIVE_NOTES_MESSAGE = "Here are the list of archived notes:";
    public static final String LIST_NOTES_MESSAGE = "Here are the list of notes:";

    public static final String ADD_NOTE_SUCCESSFUL_MESSAGE = colorize("New note added: ", GREEN);
    public static final String DELETE_NOTE_SUCCESSFUL_MESSAGE = colorize("Note deleted: ", GREEN);
    public static final String ARCHIVE_NOTE_SUCCESSFUL_MESSAGE =
            colorize("The following note has been archived: ", GREEN);
    public static final String FIND_NOTE_SUCCESSFUL_MESSAGE = colorize("Here are the matching notes:", GREEN);
    public static final String EDIT_NOTE_SUCCESSFUL_MESSAGE = colorize("Edited note successfully: ", GREEN);
    public static final String VIEW_NOTE_SUCCESSFUL_MESSAGE = colorize("Here's the note you're looking for: ",
            GREEN);
    public static final String UNARCHIVE_NOTE_SUCCESSFUL_MESSAGE = colorize("The following note has been unarchived: ",
            GREEN);

    public static final String NOTE_UNSUCCESSFUL_MESSAGE = colorize("This note already exists in the notebook! ", RED);
    public static final String NOTE_DOES_NOT_EXIST_MESSAGE =
            colorize("This note does not exist in the notebook! ", RED);
    public static final String FIND_NOTE_UNSUCCESSFUL_MESSAGE = colorize("There are no matching notes. "
            + "Please try another search query.", RED);
    public static final String SAME_NOTE_TITLE_UNSUCCESSFUL_MESSAGE = colorize("This note has the same title"
            + " as the new title! ", RED);
    public static final String INVALID_LINE_UNSUCCESSFUL_MESSAGE = colorize("Invalid line! ", RED);
    public static final String INVALID_TAG_MESSAGE = colorize("Your tags return no result."
            + " Please try an alternative tag or check your spellings", RED);
    public static final String EMPTY_NOTEBOOK_MESSAGE = colorize("The notebook is empty!", RED);


    /** Storage related messages. */
    public static final String FILE_WRITE_UNSUCCESSFUL_MESSAGE = colorize("Unable to write to file", RED);

    /** Tag related messages. */
    public static final String HEADER_CREATE_TAG = "Creating tag...";
    public static final String HEADER_DELETE_TAG = "Deleting tags...";
    public static final String TAG_HEADER = "Tagging...";

    public static final String CREATE_TAG_SUCCESSFUL_MESSAGE = colorize("Created a tag! ", GREEN);
    public static final String DELETE_TAG_SUCCESSFUL_MESSAGE = colorize("Deleted the tag! ", GREEN);
    public static final String TAG_MESSAGE = colorize("Added the tag! ", GREEN);
    public static final String UNTAG_MESSAGE = colorize("Removed the tag! ", GREEN);
    public static final String LIST_TAG_MESSAGE = colorize("Here are the list of tags!", GREEN);

    public static final String CREATE_TAG_UNSUCCESSFUL_MESSAGE = colorize("Tag already exists! ", RED);
    public static final String DELETE_TAG_UNSUCCESSFUL_MESSAGE = colorize("The tag does not exist! ", RED);
    public static final String NO_TAG_MESSAGE = colorize("There are no tags!", RED);
}