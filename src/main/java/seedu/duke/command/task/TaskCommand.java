package seedu.duke.command.task;

import seedu.duke.command.Command;
import seedu.duke.logger.ScrumLogger;
import seedu.duke.ui.Messages;
import seedu.duke.ui.Ui;

import java.util.Hashtable;

public abstract class TaskCommand extends Command {
    public TaskCommand(Hashtable<String, String> parameters, boolean shouldSave) {
        super(parameters, shouldSave);
    }

    public abstract void execute();

    /**
     * Handles the situation where the user enters an ID belonging to a deleted task for the view command.
     */
    public static void handleViewTaskDeletedId(String message) {
        Ui.showError("You have entered a task ID that has been deleted.");
        Ui.showError("Please do not enter deleted task IDs.");
        Ui.showError("To view subsequent tasks, please run this command "
                + "again with the subsequent IDs.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the user accesses the command without creating a project.
     */
    public static void handleMissingProject(String message) {
        Ui.showError("Please create a project first.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the user did not enter the command correctly.
     */
    public static void handleMissingParams(String message) {
        Ui.showError("Missing parameters.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the user enters an invalid ID.
     * @param taskId the invalid ID.
     */
    public static void handleBadId(int taskId, String message) {
        Ui.showError("The following task ID: " + taskId
                + " doesn't exist in backlog.\nPlease enter a"
                + " valid ID.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the user enters an ID belonging to a deleted task.
     */
    public static void handleDeletedId(String message) {
        Ui.showError("You have entered a task ID that has been deleted.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the users enters non-integers into the ID field.
     */
    public static void handleNonIntegerId(String message) {
        Ui.showError(Messages.MESSAGE_INVALID_IDTYPE);
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the users enters an ID that is out of bounds.
     */
    public static void handleInvalidId(int taskId, String message) {
        Ui.showError("The following task ID: " + taskId
                + " doesn't exist in backlog.\nPlease enter a"
                + " valid ID.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the users enters a negative number in the ID field.
     */
    public static void handleNegativeId(int taskId, String message) {
        Ui.showError("The ID: " + taskId + " is invalid. "
                + "Please enter a positive integer.");
        ScrumLogger.LOGGER.warning(message);
    }


    /**
     * Handles the situation where the users enters an invalid priority.
     */
    public static void handleInvalidPriority(String message) {
        Ui.showError("Priority entered is invalid!");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the user enters an ID belonging to a deleted or invalid task.
     */
    public static void handleIncorrectId(String message) {
        Ui.showError("You have entered a task ID that does not exist, has been deleted.");
        ScrumLogger.LOGGER.warning(message);
    }

    /**
     * Handles the situation where the users enters a duplicate title.
     */
    public static void handleDuplicateTitle(String message) {
        Ui.showError("The task title already exists! "
                + "Please enter an alternative name.");
        ScrumLogger.LOGGER.warning(message);
    }
}