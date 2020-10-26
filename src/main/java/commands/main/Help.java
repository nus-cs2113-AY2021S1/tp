package commands.main;

import commands.Command;
import commands.CommandResult;
import commands.ExecutionResult;
import org.apache.commons.lang3.StringUtils;

import static seedu.duke.Constant.COMMAND_WORD_DIET;
import static seedu.duke.Constant.COMMAND_WORD_END;
import static seedu.duke.Constant.COMMAND_WORD_PROFILE;
import static seedu.duke.Constant.COMMAND_WORD_WORKOUT;
import static ui.CommonUi.helpFormatter;

public class Help extends Command {

    /**
     * Executes help command in Main Menu.
     *
     * @param args Array of user's input.
     * @return Help message.
     */
    @Override
    public CommandResult execute(String args) {

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
