package seedu.duke.others;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandHelp extends Command {
    @Override
    public String execute() {
        return "Hello! Here is a list of commands you can try:\n"
                + "\t1. Add members:  hr add /n NAME /p PHONE_NUMBER /e EMAIL /r ROLE\n"
                + "\t2. Delete member: hr delMember MEMBER_INDEX\n"
                + "\t3. View hr list: hr listMember\n"
                + "\t4. Change member info: hr changeInfo /n NAME (/p ITEM) (/e ITEM) (/r ITEM)\n"
                + "\t5. Search for members: hr search ITEM (/n ITEM) (/p ITEM) (/e ITEM) (/r ITEM)\n"
                + "\t6. view contacts of prof/admin: hr list prof&admin\n"
                + "\t7. view contacts of connections: hr list connections\n"
                + "\t8. Add events: event addEvent /n EVENT_NAME /d EVENT_DATE <YYYY-MM-dd> /t EVENT_TIME <HH-mm>\n"
                + "\t9. Delete event: event delEvent EVENT_INDEX\n"
                + "\t10. View event list: event listEvent\n"
                + "\t11. View countdown to events: event countdown\n"
                + "\t12. Search for an event by NAME or DATE<YYYY-MM-DD>: event search /s KEYWORD\n"
                + "\t13. Mark an event as done: event done EVENT_INDEX\n"
                + "\t14. Add a participant to an event: event addAttendance /n EVENT_NAME /m MEMBER_NAME\n"
                + "\t15. Delete a participant from an event: event delAttendance /n EVENT_NAME /m MEMBER_NAME\n"
                + "\t16. List participants in an event: event listAttendance /n EVENT_NAME\n"
                + "\t17. Add financial log: finance addLog ITEM_NAME ITEM_VALUE\n"
                + "\t18. Delete financial log:  finance delLog ITEM_INDEX\n"
                + "\t19. Change finance log entry information: finance changeLog /i INDEX /n ITEM_NAME ITEM_VALUE\n"
                + "\t20. View financial summary: finance summary\n"
                + "\t21. Import data: import FILENAME /c finance /name HEADER_NAME /value HEADER_NAME\n"
                + "\t22. Exit the program: bye\n"
                +" To access our User Guide: https://ay2021s1-cs2113t-f14-1.github.io/tp/UserGuide.html";
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