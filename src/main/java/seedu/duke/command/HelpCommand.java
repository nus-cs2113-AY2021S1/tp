package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.human.UserManagement;

public class HelpCommand extends Command {
    private static String output;
    
    public HelpCommand() {
        this.output = buildHelpOutput();
    }
    /**
     * Shows help function.
     */
    @Override
    public String execute(AnimeData animeData, UserManagement userManagement) {
        return output;
    }
    
    private String buildHelpOutput() {
        StringBuilder result = new StringBuilder();
        
        result.append("Create a new user profile:");
        result.append(System.lineSeparator());
        result.append(" adduser -n NAME -dob DATE_FORMAT -g GENDER");

        result.append(System.lineSeparator());
        result.append(System.lineSeparator());
        
        result.append(" Switch to another user:");
        result.append(System.lineSeparator());
        result.append(" switchuser -n NAME");
        
        return result.toString();
    }
}
