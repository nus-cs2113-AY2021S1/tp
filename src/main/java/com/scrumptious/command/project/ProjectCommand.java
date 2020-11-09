package com.scrumptious.command.project;

import com.scrumptious.command.Command;
import com.scrumptious.logger.ScrumLogger;
import com.scrumptious.ui.Ui;

import java.util.Hashtable;

public abstract class ProjectCommand extends Command {


    /**
     * Creates a new Project command with arguments.
     */
    public ProjectCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();

    /**
     * Handles the situation where the users tries to create a duplicate project.
     */
    public static void handleDuplicateProject(String message) {
        Ui.showError("\tThe project already exists! "
                + "View projects added using \'project/list\'.");
        ScrumLogger.LOGGER.warning(message);
    }

    public abstract void logExecution();

}
