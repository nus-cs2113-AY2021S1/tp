package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandEventAdd extends Command {
    private UserInput userInput;
    private Event cachedEvent;
    private static final String PATTERN =
            "([01]?[0-9]|2[0-3])-([0-5][0-9])";
    public static final String WRONG_TIME_FORMAT =
            "Please enter time in HH-mm format. Note that it is 24 hour clock format.";
    public static final String DUPLICATE_EVENT = "This event already exists!";

    @Override
    public String execute()  {

        String output;
        if (cachedEvent == null) {
            return "Unable to create event! Please check your inputs again!";
        }
        String eventName = userInput.getArg("n");
        String eventDate = userInput.getArg("d");
        String eventTime = userInput.getArg("t");

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(eventTime);
        boolean matchFound = matcher.matches();

        if (!(matchFound)) {
            output = WRONG_TIME_FORMAT;
        } else if ((EventList.checkEventNameMatch(eventName)) && (EventList.checkEventDateMatch(eventDate))) {
            output = DUPLICATE_EVENT;
        } else {
            output = EventList.addEvent(cachedEvent);
        }
        return output;
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (userInput.getCategory().equals("event") && (userInput.getCommand().equalsIgnoreCase("addEvent")
                || userInput.getCommand().equalsIgnoreCase("add")
                || userInput.getCommand().equalsIgnoreCase("a"))) {
            if (ui.getNumArgs() >= 3) {
                if ((ui.getArg("n") == null) || (ui.getArg("d") == null) || (ui.getArg("t") == null)) {
                    return ARGUMENT_ERR;
                }
                if ((ui.getArg("n").equals("")) || (ui.getArg("d").equals("")) || (ui.getArg("t").equals(""))) {
                    return ARGUMENT_ERR;
                }
                cachedEvent = new Event(userInput.getArg("n"), userInput.getArg("d"), userInput.getArg("t"));
                if (cachedEvent.date != null) {
                    return ACCEPT;
                }
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }


    @Override
    public String help() {
        return "Syntax: event addEvent /n EVENT_NAME /d EVENT_DATE <YYYY-MM-dd> /t EVENT_TIME <HH-mm>";
    }
}
