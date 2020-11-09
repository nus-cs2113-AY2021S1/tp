package com.scrumptious.command.help;

import com.scrumptious.command.Command;

import java.util.Hashtable;

public abstract class HelpCommand extends Command {

    /**
     * Creates a new Help command with arguments.
     */
    public HelpCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();

    public abstract void logExecution();
}
