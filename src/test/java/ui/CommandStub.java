package ui;

import command.Command;

public class CommandStub extends Command {
    public CommandStub(boolean isExitCommand) {
        setIsExit(isExitCommand);
    }

    public void setIsExit(boolean isExitCommand) {
        this.isExitCommand = isExitCommand;
    }

    public void execute() {
    }
}
