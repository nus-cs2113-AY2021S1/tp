package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandSearchMember extends Command {
    private UserInput userInput;

    /**
     * Determine which part of member information to search for the target content.
     * @return the search result
     */
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

    /**
     * Provide the help information when user has trouble typing the correct command.
     * @return the syntax for search command
     */
    @Override
    public String help() {
        return "Syntax: hr search ITEM (/n ITEM) (/p ITEM) (/e ITEM) (/r ITEM)\n";
    }

    /**
     * Determine whether the user is calling this command.
     * @param input The UserInput to Validate
     * @return it is the command the user is calling or not
     */
    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("search")
            || input.getCommand().equalsIgnoreCase("s"))) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
