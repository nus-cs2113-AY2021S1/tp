package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import exceptions.SchwarzeneggerException;

import static ui.CommonUi.helpFormatter;

//@@author wgzesg
/**
 * A representation of the command for help commands in workout manager.
 */
public class HelpWS extends Command {

    /**
     * Executes help command in Workout Menu.
     *
     * @param args User's input.
     * @return Status OK and information to be printed.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        String helpMessage = helpFormatter("New", "new </t [TAG]...>",
                "Create a new workout session and tags. Multiple tags are separated by ','")
                + helpFormatter("List", "list </s [START_DATE]> </e [END_DATE]>",
                "Show all past sessions. Can display sessions between a certain period")
                + helpFormatter("Delete", "delete [INDEX]",
                "Delete the record indexed at x")
                + helpFormatter("Edit", "edit [INDEX]",
                "Edit the record indexed at x")
                + helpFormatter("Clear", "clear",
                "Clear all past results")
                + helpFormatter("Search", "search </t [TAG]...> </d [DATE]>",
                "Search records based on tags and dates. Multiple tags are seperated by ','")
                + helpFormatter("End", "end",
                "Go back to Main Menu");
        return new CommandResult(helpMessage.trim(), ExecutionResult.OK);
    }
}
