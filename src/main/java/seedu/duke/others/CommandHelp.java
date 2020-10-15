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
                + "\t4. Add events: event addEvent /n EVENT_NAME /d EVENT_DATE /t EVENT_TIME\n"
                + "\t5. Delete event: event delEvent EVENT_INDEX\n"
                + "\t6. View event list: event listEvent\n"
                + "\t7. Add financial log: finance addLog ITEM_NAME ITEM_VALUE\n"
                + "\t8. Delete financial log:  finance delLog ITEM_INDEX\n"
                + "\t9. View financial summary: finance summary\n"
                + "\t10. Exit the program: bye\n";
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