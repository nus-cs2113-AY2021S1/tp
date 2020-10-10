package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;
public class CommandAddMember extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("add")) {
            if (input.getNumArgs() == 4) {
                if ((input.getArg("n")!= null)&&(input.getArg("p")!= null)&&(input.getArg("e")!= null)
                        &&(input.getArg("r")!= null)) {
                    return ACCEPT;
                }
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String execute() {
        int phone = Integer.parseInt(savedInput.getArg("p"));
        Member m = new Member(savedInput.getArg("n"),phone, savedInput.getArg("e"), savedInput.getArg("r"));
        MemberList.addToList(m);
        String output = "Got it. I've added this member: \n" + m.toString() + "\n"
                + "Now you have " + Member.numOfMembers + " member" + ((Member.numOfMembers == 1) ? "" : "s")
                + " in the list.\n";
        return output;
    }

    @Override
    public String help() {
        return "";
    }
}
