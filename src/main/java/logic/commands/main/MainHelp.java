package logic.commands.main;

import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import org.apache.commons.lang3.StringUtils;

import static seedu.duke.Constants.COMMAND_WORD_DIET;
import static seedu.duke.Constants.COMMAND_WORD_END;
import static seedu.duke.Constants.COMMAND_WORD_PROFILE;
import static seedu.duke.Constants.COMMAND_WORD_WORKOUT;
import static ui.CommonUi.helpFormatter;

//@@author tienkhoa16

/**
 * A representation of the command for showing help message in Main Menu.
 */
public class MainHelp extends Command {

    /**
     * Executes help command in Main Menu.
     *
     * @param args User's input.
     * @return Help message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);

        if (!args.isEmpty()) {
            ui.showWarning("\"help\" command does not take in parameters");
        }

        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_PROFILE), COMMAND_WORD_PROFILE,
                "Go to Profile Menu to manage your profile"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_WORKOUT), COMMAND_WORD_WORKOUT,
                "Go to Workout Menu to manage your workout records and create new workout records"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_DIET), COMMAND_WORD_DIET,
                "Go to Diet Menu to manage your past diet records"
                        + " and create new diet records"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_END), "end",
                "Exit The Schwarzenegger"));
        ui.showToUser(helpMessage.toString().trim());
        return new CommandResult("", ExecutionResult.OK);
    }
}
