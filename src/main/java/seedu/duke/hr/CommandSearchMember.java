package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandSearchMember extends Command {
    private UserInput userInput;

    @Override
    public String execute() {
        boolean name = false;
        boolean email = false;
        boolean phone = false;
        boolean role = false;
        boolean any = false;
        String anyS = "";
        String nameS = "";
        String emailS = "";
        String phoneS = "";
        String roleS = "";
        if (userInput.getArg("") != null && !userInput.getArg("").equals("")) {
            any = true;
            anyS = userInput.getArg("");
        }
        if (userInput.getArg("n") != null && !userInput.getArg("n").equals("")) {
            name = true;
            nameS = userInput.getArg("n");
        }
        if (userInput.getArg("e") != null && !userInput.getArg("e").equals("")) {
            email = true;
            emailS = userInput.getArg("e");
        }
        if (userInput.getArg("p") != null && !userInput.getArg("p").equals("")) {
            phone = true;
            phoneS = userInput.getArg("p");
        }
        if (userInput.getArg("r") != null && !userInput.getArg("r").equals("")) {
            role = true;
            roleS = userInput.getArg("r");
        }
        String output = MemberList.search(any, name, phone, email, role, anyS,
                nameS, phoneS, emailS, roleS);
        return output;
    }

    @Override
    public String help() {
        return null;
    }

    // /n search name, /p search phone, /e search email, /r search role
    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("search")) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
