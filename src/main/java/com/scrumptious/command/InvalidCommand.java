package com.scrumptious.command;

import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public class InvalidCommand extends Command {

    public InvalidCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        ScrumLogger.LOGGER.warning("Invalid action!");
        Ui.showToUserLn("Invalid action!");
    }
}
