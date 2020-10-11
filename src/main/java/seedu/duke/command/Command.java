package seedu.duke.command;

import seedu.duke.exception.AniException;

public abstract class Command {
    protected String description;
    
    public Command(String description) {
        setDescription(description);
    }
    
    public Command() {
        
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void execute() throws AniException {
        throw new AniException("This method should be implemented in the child class");
    };

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
