package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.isNumber;
import static seedu.duke.hr.MemberList.findMemberByName;
import static seedu.duke.hr.MemberList.standardizeMemberName;

/**
 * Represents add member command.
 */

public class CommandAddMember extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("addMember")
                || (input.getCommand().equalsIgnoreCase("add")
                || input.getCommand().equalsIgnoreCase("a"))) {
            if (input.getNumArgs() >= 4) {
                if ((input.getArg("n") == null) || (input.getArg("p") == null) || (input.getArg("e") == null)
                        || (input.getArg("r") == null)) {
                    return ARGUMENT_ERR;
                }
                if ((input.getArg("n").equals("")) || (input.getArg("p").equals(""))
                        || (input.getArg("e").equals("")) || (input.getArg("r").equals(""))) {
                    return ARGUMENT_ERR;
                }
                return ACCEPT;
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String execute() {
        String output;
        if (!isNumber(savedInput.getArg("p"))) {
            output = "OOPS!!! The format of the phone number given is incorrect.\n";
            output = output.concat("The phone number should be a whole number less than 19 digits.");
            return output;
        }
        long phone = Long.parseLong(savedInput.getArg("p"));
        if (phone < 0) {
            output = "OOPS!!! The phone number should be a positive number.";
            return output;
        }
        String standardName = standardizeMemberName(savedInput.getArg("n"));
        Member test = findMemberByName(standardName);
        if (test == null) {
            Member m = new Member(standardName, phone, savedInput.getArg("e"), savedInput.getArg("r"));
            output = MemberList.addToList(m);
        } else {
            output = "OOPS!!! This member already exists. You may want to modify the member's information instead.";
        }
        return output;
    }

    @Override
    public String help() {

        return "You can use 'hr add' command this way:\n"
                + "hr add /n NAME /p PHONE_NUMBER (INTEGER) /e EMAIL /r ROLE\n";
    }
}
