package com.scrumptious.command.help;

import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public class StorageHelpCommand extends HelpCommand {
    public StorageHelpCommand(Hashtable<String, String> parameters) {
        super(parameters, false);
    }

    @Override
    public void execute() {
        logExecution();
        Ui.showToUserLn("1. Clear all data. You will be prompted to confirm the decision.");
        Ui.showToUserLn("   Data will be cleared if \"y\" (case-insensitive) is provided.");
        Ui.showToUserLn("   Format: storage /clear");
        Ui.showToUserLn("   Example: storage /clear");
    }

    @Override
    public void logExecution() {
        ScrumLogger.LOGGER.info("Viewed storage help.");
    }
}
