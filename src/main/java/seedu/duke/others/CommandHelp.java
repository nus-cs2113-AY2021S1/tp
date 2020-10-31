package seedu.duke.others;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandHelp extends Command {
    @Override
    public String execute() {
        return "Hello! Here is a list of commands you can try:\n"
                + "\t1. Add members:  hr add /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE\n"
                + "\t2. Delete member: hr delete index\n"
                + "\t3. View hr list: hr list\n"
                + "\t4. Add events: event addEvent /n EVENT_NAME /d EVENT_DATE <YYYY-MM-dd> /t EVENT_TIME <HH-mm>\n"
                + "\t5. Delete event: event delEvent EVENT_INDEX\n"
                + "\t6. View event list: event listEvent\n"
                + "\t7. View countdown to events: event countdown\n"
                + "\t8. Search for an event by NAME or DATE<YYYY-MM-DD>: event search /s KEYWORD\n"
                + "\t9. Mark an event as done: event done EVENT_INDEX\n"
                + "\t10. Add financial log: finance addLog ITEM_NAME ITEM_VALUE\n"
                + "\t11. Delete financial log:  finance delLog ITEM_INDEX\n"
                + "\t12. View financial summary: finance summary\n"
                + "\t13. Exit the program: bye\n";
    }

    @Override
    public int validate(UserInput input) {
        if (input.getCategory().equals("") && input.getCommand().equalsIgnoreCase("help")) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String help() {

        return "";
    }
}