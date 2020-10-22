package seedu.lifeasier.ui;

import seedu.lifeasier.notes.NoteList;
import java.util.Scanner;

/**
 * The Ui class deals with all interactions with the user.
 */
public class Ui {

    public static final String SEPARATOR = "=========================================================================";
    public static final String PARAM_LESSON = "lesson";
    public static final String PARAM_EVENT = "event";
    public static final String PARAM_DEADLINE = "deadline";
    public static final String LOGO = "\n"
            + "  _      _  __ ______          _\n"
            + " | |    (_)/ _|  ____|        (_)\n"
            + " | |     _| |_| |__   __ _ ___ _  ___ _ __\n"
            + " | |    | |  _|  __| / _` / __| |/ _ \\ '__|\n"
            + " | |____| | | | |___| (_| \\__ \\ |  __/ |\n"
            + " |______|_|_| |______\\__,_|___/_|\\___|_|\n"
            + "\n";

    //Error messages
    public static final String ERROR_INVALID_COMMAND = "Oh no, I do not understand this command! Type 'help' for "
            + "a list of available commands";
    public static final String ERROR_INVALID_INPUT = "Oh no, I do not understand this input!";

    //General UI messages
    public static final String MESSAGE_GOODBYE = "Goodbye, hope to see you again soon!";
    public static final String MESSAGE_GREETING = "Hello [NAME], what can I do for you today?";
    public static final String MESSAGE_HELP_COMMAND = " -Type 'help' for a list the list of available commands";

    //Help message
    public static final String HELP_MESSAGE = "These are the commands that are available:\n"
            + "Notes about the command format:\n"
            + " * Words in UPPER_CASE are the parameters to be supplied by the user\n"
            + " * Items in square brackets are optional, e.g [DATE]\n"
            + "\n"
            + "COMMANDS\n"
            + "*************************************************************************\n"
            + "help ---------------------------------------- Displays available commands\n"
            + "addLesson /code MODULE_CODE /date DATE /time START /to END -- Adds lesson\n"
            + "addEvent EVENT_NAME /date DATE /time START /to END -------- Adds an event\n"
            + "addDeadline DEADLINE_NAME /by DATETIME ------------------ Adds a deadline\n"
            + "editLesson MODULE_CODE ----------------------------------- Edits a lesson\n"
            + "editEvent EVENT_NAME ------------------------------------- Edits an event\n"
            + "editDeadline DEADLINE_NAME ----------------------------- Edits a deadline\n"
            + "deleteTask /type TYPE /name NAME ------------------------- Deletes a task\n"
            + "addNotes TITLE ------------------------------------------ Adds a new note\n"
            + "showNotes TITLE ------------------------------------- Shows selected note\n"
            + "display WEEK/DAY --------------- Displays either weekly or daily schedule\n"
            + "freeTime ------------------------ Tells you when you have free time today\n"
            + "sleepTime --------------------- Tells you how much time you have to sleep\n"
            + "exit --------------------------------------- Closes the LifEasier program\n"
            + "*************************************************************************\n"
            + "For more detailed information, please visit the online user guide at:\n";

    //Input format messages
    public static final String NEW_DEADLINE_TIME_INPUT_FORMAT = "/by DATETIME";
    public static final String NEW_EVENT_TIME_INPUT_FORMAT = "/date DATE /time START /to END";
    public static final String NEW_LESSON_TIME_INPUT_FORMAT = "/date DATE /time START /to END";

    private Scanner conversation;

    public Ui() {
        this.conversation = new Scanner(System.in);
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public void showLogo() {
        printSeparator();
        printSeparator();
        printLogo();
        printSeparator();
        printSeparator();
    }

    public void showGreetingMessage() {
        System.out.println(MESSAGE_GREETING);
        System.out.println(MESSAGE_HELP_COMMAND);
    }

    public void showAddConfirmationMessage(String task) {
        System.out.println("Done! I've added \"" + task + "\" to your calendar");
        printSeparator();
    }

    private void printLogo() {
        System.out.println(LOGO);
    }

    public void showHelp() {
        System.out.println(SEPARATOR);
        System.out.println(HELP_MESSAGE);
        System.out.println(SEPARATOR);
    }

    /**
     * Returns read user command.
     */
    public String readCommand() {
        return conversation.nextLine();
    }

    public int readSingleIntInput() {
        return Integer.parseInt(readCommand());
    }

    public void showSelectTaskToEdit(String type) {
        System.out.println("Please select the " + type + " you want to edit.");
    }

    public void showSelectParameterToEdit() {
        System.out.println("Please select the parameter you want to edit.");
    }

    public void showSelectTaskToDelete(String type) {
        System.out.println("Please select the " + type + " you want to delete.");
    }

    public void showInputFormat(String type) {
        switch (type) {

        case (PARAM_EVENT):
            System.out.println("Please input your new time in this format: " + NEW_EVENT_TIME_INPUT_FORMAT);
            break;

        case (PARAM_DEADLINE):
            System.out.println("Please input your new time in this format: " + NEW_DEADLINE_TIME_INPUT_FORMAT);
            break;

        case (PARAM_LESSON):
            System.out.println("Please input your new time in this format: " + NEW_LESSON_TIME_INPUT_FORMAT);
            break;

        default:
            break;
        }
    }

    public void showInputMessage(String type) {
        switch (type) {

        case (PARAM_LESSON):
            System.out.println("Please input your new Module Code");
            break;

        case (PARAM_DEADLINE):
            System.out.println("Please input your new Deadline name");
            break;

        case (PARAM_EVENT):
            System.out.println("Please input your new Event name");
            break;

        default:
            break;
        }
    }

    public void showEditableParametersMessage(String type) {
        switch (type) {

        case (PARAM_LESSON):
            System.out.println("1. Module Code\n2. Time");
            break;

        case (PARAM_DEADLINE):
            System.out.println("1. Deadline Name\n2. Time");
            break;

        case (PARAM_EVENT):
            System.out.println("1. Event Name\n2. Time");
            break;

        default:
            break;
        }
    }

    public void showEditConfirmationMessage() {
        System.out.println("Your edit has been saved.");
    }

    public void showDeleteConfirmationMessage() {
        System.out.println("The task you selected has been deleted.");
    }

    public void showInvalidCommandError() {
        System.out.println(ERROR_INVALID_COMMAND);
    }

    public void showInvalidInputMessage() {
        System.out.println(ERROR_INVALID_INPUT);
    }

    public void showGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    public void showNoteTitleMessage() {
        System.out.println("Please put in a title:\n");
    }

    public void showNoteDescriptionMessage() {
        System.out.println("Alright! Please fill in your notes.\n");
    }

    public void showFileCreationError() {
        System.out.println("Something went wrong... Save file creation failed...");
    }

    public void showDataLoadingMessage() {
        System.out.println("Reading your save data. New saves will be created if no saves are found.");
    }

    public void showNoDataToArchiveMessage() {
        System.out.println("You do not have any data available for archiving.");
        printSeparator();
    }

    public void showFileArchiveError() {
        System.out.println("There was an error archiving your data");
    }

    public void showArchiveStartMessge() {
        printSeparator();
        System.out.println("Starting archiving...");
    }

    public void showArchiveEndMessage() {
        System.out.println("Archiving successful!");
        printSeparator();
    }

    public void showFileReadError() {
        System.out.println("Something went wrong, unable to read from save file...");
    }

    public void showFileWriteError() {
        System.out.println("Something went wrong while saving your data...");
    }

    public void showDirectoryCreationFailedError() {
        System.out.println("Directory creation failed...");
    }

    public void showInvalidNumberMessage() {
        System.out.println("The number you inputted is invalid!");
        printSeparator();
    }

    public void showNoTitleFoundMessage() {
        System.out.println("The title you inputted is not found...");
        printSeparator();
    }

    public void showNumberFormatMessage() {
        System.out.println("\nPlease input only a number!");
        printSeparator();
    }

    public void showInvalidCastError() {
        System.out.println("Something went wrong, mismatching task types...");
    }

    public void showSaveDataMissingError() {
        System.out.println("Encountered an error while reading from the save file - Data missing/corrupted");
    }

    public void showLocalDateTimeParseError() {
        System.out.println("Encountered a problem reading the date and time of the task...");
    }

    public void showUndeterminableTaskError() {
        System.out.println("Something went wrong while determining the tasks...");
    }

    public void showParseUnknownCommandMessage() {
        System.out.println("I'm sorry! I don't understand that command!");
        printSeparator();
    }

    public void showParseIncorrectCommandFormatMessage() {
        System.out.println("I'm sorry! Please double check the input of your format!");
        System.out.println("Use the help command for formats of all the commands!");
        printSeparator();
    }

    public void showParseIncorrectDateTimeMessage() {
        System.out.println("I'm sorry! Please ensure your dates are in the format dd-mm-yy,");
        System.out.println("and your times are in the format hh:mm!");
        printSeparator();
    }

    public void showEmptyDescriptionMessage() {
        System.out.println("Empty description! =O\n");
    }

    public void showNoMatchesMessage(String type) {
        System.out.println("Sorry! There is no " + type + " matching your query. Please re-enter your command.");
    }

    public void showNoteAddedMessage() {
        System.out.println("Ok! I've taken note of this note!");
    }

    public void showMultipleMatchesFoundMessage() {
        System.out.println("Multiple matches found! Please select the one you are looking for:\n");
    }

    public void showSelectWhichNoteToViewMessage() {
        System.out.println("Please select the notes you want to view:\n");
    }

    public void showFreeTimeMessage(int startHour, int endHour, int duration) {

        if (duration != 0) {
            System.out.println("You have " + duration + " hours of free time between " + startHour
                    + ":00 and " + endHour + ":00!");
            System.out.println("You can try scheduling something in this time!");

        } else {
            System.out.println("Unfortunately you have no free time today!");
            System.out.println("You might want to relax a little!");
        }

        printSeparator();
    }

    public void showNothingScheduledMessage() {
        System.out.println("You have nothing on for today and tomorrow!");
    }

    public void showAvailableSleepTimeMessage(int earliestSleepTime, int latestWakeTime) {
        System.out.println("You have nothing on from " + earliestSleepTime + ":00 today to " + latestWakeTime
                + ":00 tomorrow!");
    }

    public void showSleepDurationMessage(int duration) {
        System.out.println("You can sleep for up to " + duration + " hours!");
    }

    public void showExcessSleepDurationMessage() {
        System.out.println("You can sleep for the recommended 8 hours or longer!");
    }

    public void showSelectWhichNoteToDeleteMessage() {
        System.out.println("Please select the notes you want to delete:\n");
    }

    public void showConfirmDeleteMessage() {
        System.out.println("Is this the note you want to delete? (Y/N)\n");
    }

    public void showInvalidConfirmationMessage() {
        System.out.println("Y for Yes and N for No\n");
    }

    public void showNoteNotDeletedMessage() {
        System.out.println("OK! Note not deleted!");
    }

    public void showNoteDeletedMessage() {
        System.out.println("OK! Note deleted!");
    }

    public void showConfirmEditMessage() {
        System.out.println("Is this the note you want to edit? (Y/N)\n");
    }

    public void showEditWhichPartMessage() {
        System.out.println("Do you want to change the title or description? (T/D)\n");
    }

    public void showNoteNotEditedMessage() {
        System.out.println("OK! Note not edited!");
        printSeparator();
    }

    public void showInvalidTitleDescriptionConfirmationMessage() {
        System.out.println("T for title and D for Description\n");
    }

    public void showEditTitleMessage() {
        System.out.println("Please input the title you want to change to:\n");
    }

    public void showEditDescriptionMessage() {
        System.out.println("\nPlease input the description you want to change to:\n");
    }

    public void showSelectWhichNoteToEditMessage() {
        System.out.println("Please select the notes you want to edit:\n");
    }

    public void showEmptyNoteListMessage() {
        System.out.println("There's no Notes!");
        printSeparator();
    }

    public void showAddModuleCodeMessage() {
        System.out.println("Please input the module code:");
    }

    public void showAddDateMessage() {
        System.out.println("Please input the date:");
    }

    public void showAddStartTimeMessage() {
        System.out.println("Please input the start time:");
    }

    public void showAddEndTimeMessage() {
        System.out.println("Please input the end time:");
    }

    public void showAddDescriptionMessage() {
        System.out.println("Please input the description:");
    }

    public void showAddDateTimeMessage() {
        System.out.println("Please input the Date Time:");
    }

    public void printMultipleNoteMatches(NoteList notes, String title) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getTitle().contains(title)) {
                System.out.println(i + 1 + ". " + notes.get(i).getTitle() + "\n");
            }
        }
    }

    public void printAllNotes(NoteList notes) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i).getTitle() + "\n");
        }
    }

    public void showEnterUndoTypeMessage() {
        printSeparator();
        System.out.println("To undo a change in tasks, please enter: task");
        System.out.println("To undo a change in notes, please enter: note");
    }

    public void showInvalidUndoType() {
        System.out.println("Invalid undo type, please try again!");
    }

    public void showInvalidUndoAction() {
        System.out.println("Nothing to undo!");
    }

    public void showUndoTaskEditMessage() {
        System.out.println("This task has been reverted back to its previous version!");
    }

    public void showUndoTaskDeleteMessage() {
        System.out.println("This deleted task has been successfully restored!");
    }

    public void showNoteUndoMessage() {
        System.out.println("This note has been successfully reverted!");
    }
}
