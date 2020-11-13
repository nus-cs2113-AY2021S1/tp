package seedu.ravi.command;

import static seedu.ravi.command.PromptType.NONE;

public abstract class Command {
    protected PromptType promptType = NONE;

    public PromptType getPromptType() {
        return promptType;
    }

    public void setPromptType(PromptType promptType) {
        this.promptType = promptType;
    }

    /**
     * Executes the command.
     *
     * @return
     *  The result of the execution
     */
    public abstract CommandResult execute();


}