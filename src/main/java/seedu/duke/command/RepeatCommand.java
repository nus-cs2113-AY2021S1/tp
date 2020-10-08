package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.DateStatusPair;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    /**
     * Constructor for repeating events seedu.duke
     *
     * @param command from user input
     */

    private int eventIndex;
    private String eventType;
    private int timeInterval;
    private int numberOfIterations;
    private boolean isValid;

    private static final int WEEKLY = 1;
    private static final int MONTHLY = 2;


    /**
     * Set up for the repeat command.
     * @param command user input with the format eventIndex; eventType; timeInterval; NumberofIterations
     */
    public RepeatCommand(String command) {
        this.isExit = false;
        this.command = command;
        parseUserCommand(command);
    }

    private void parseUserCommand(String command) {

        int argumentNumber = 4;
        //Split up into various components and remove trailing spaces
        String[] words = command.split(";");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        if (words.length != argumentNumber) {
            this.isValid = false;
            return;
        }


        eventIndex = Integer.parseInt(words[0]) - 1;
        eventType = capitaliseFirstLetter(words[1]);
        numberOfIterations = Integer.parseInt(words[3]);

        String intervalWord = words[2];

        switch (intervalWord) {
        case "weekly":
            timeInterval = 1;
            break;
        case "monthly":
            timeInterval = 2;
            break;
        default:
            timeInterval = 0;
            isValid = false;
            return;
        }
        isValid = true;

    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {

        if (!isValid) {
            System.out.println("Error! invalid input given to repeat.");
            return;
        }

        EventList information = data.getEventList(this.eventType);
        Event toChange = information.getEventByIndex(this.eventIndex);
        if (toChange.getDate() == null) {
            System.out.println("Error! cannot repeat with no date.");
            return;
        }
        createDateList(toChange, this.timeInterval, this.numberOfIterations);
        toChange.setIsRepeat(true);
        toChange.setRepeatUnit(this.timeInterval);
        toChange.setRepeatCount(this.numberOfIterations);


        System.out.println("All done, the program is set to repeat.");
    }

    private void createDateList(Event eventToRepeat, int repeatUnit, int repeatCount) {

        LocalDate startDate;
        LocalTime startTime;
        boolean isStartEventDone;

        startDate = eventToRepeat.getDate();
        startTime = eventToRepeat.getTime();
        isStartEventDone = eventToRepeat.getStatus().equals("✓");

        eventToRepeat.addRepeatDateStatusPair(startDate, startTime, isStartEventDone);

        for (int i = 0; i < repeatCount; i++) {

            switch(repeatUnit) {
            case 1:
                startDate = startDate.plusWeeks(1);
                break;
            case 2:
                startDate = startDate.plusMonths(1);
                break;
            default:
                continue;
            }

            eventToRepeat.addRepeatDateStatusPair(startDate, startTime, false);

        }

    }

    /**
     * Function accept a word string and capitalise the first letter.
     *
     * @param word String containing the word to have the first letter capitalised
     * @return String of the word with first letter capitalised
     */
    private String capitaliseFirstLetter(String word) {
        char firstLetter = word.charAt(0);
        firstLetter = Character.toUpperCase(firstLetter);
        String remaining = word.substring(1);
        String capitaliseFirstLetter = Character.toString(firstLetter);
        return capitaliseFirstLetter + remaining;

    }
}
