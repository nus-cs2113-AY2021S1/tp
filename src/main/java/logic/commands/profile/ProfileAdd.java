package logic.commands.profile;

import exceptions.SchwarzeneggerException;
import exceptions.profile.InvalidSaveFormatException;
import logic.commands.Command;
import logic.commands.CommandResult;
import models.Profile;
import storage.profile.ProfileStorage;

import java.util.HashMap;

import static logic.commands.ExecutionResult.FAILED;
import static logic.commands.ExecutionResult.OK;
import static logic.parser.ProfileParser.extractCalories;
import static logic.parser.ProfileParser.extractCommandTagAndInfo;
import static logic.parser.ProfileParser.extractExpectedWeight;
import static logic.parser.ProfileParser.extractHeight;
import static logic.parser.ProfileParser.extractName;
import static logic.parser.ProfileParser.extractWeight;
import static logic.parser.ProfileParser.findInvalidTags;
import static profile.Constants.CALORIES_UPPER_BOUND;
import static seedu.duke.Constants.COMMAND_WORD_ADD;
import static ui.profile.ProfileUi.MESSAGE_ADJUST_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_CREATE_PROFILE_ACK;
import static ui.profile.ProfileUi.MESSAGE_PROFILE_EXIST;

//@@author tienkhoa16

/**
 * A representation of the command for adding profile.
 */
public class ProfileAdd extends Command {

    /**
     * Overrides execute method of class Command to execute the add profile command requested by user's input.
     *
     * @param commandArgs User's input arguments.
     * @param storage Profile Storage to load and save data.
     * @return Result of command execution.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String commandArgs, ProfileStorage storage) throws SchwarzeneggerException {
        assert commandArgs != null : "command args cannot be null";
        assert storage != null : "profile storage cannot be null";

        super.execute(commandArgs, storage);

        Profile profile;
        try {
            profile = storage.loadData();
            return new CommandResult(MESSAGE_PROFILE_EXIST, FAILED);
        } catch (InvalidSaveFormatException e) {
            HashMap<String, String> parsedParams = extractCommandTagAndInfo(COMMAND_WORD_ADD, commandArgs);

            String invalidTags = findInvalidTags(parsedParams);
            if (!invalidTags.isEmpty()) {
                ui.showWarning("\"add\" command does not take in the following parameters: " + invalidTags);
            }

            String name = extractName(parsedParams);
            int height = extractHeight(parsedParams);
            double weight = extractWeight(parsedParams);
            double expectedWeight = extractExpectedWeight(parsedParams);
            double calories = extractCalories(parsedParams);
            double adjustedCalories = Math.min(calories, CALORIES_UPPER_BOUND);

            profile = new Profile(name, height, weight, expectedWeight, adjustedCalories);
            storage.saveData(profile);

            if (calories > CALORIES_UPPER_BOUND) {
                ui.showWarning(MESSAGE_ADJUST_CALORIES);
            }

            return new CommandResult(String.format(MESSAGE_CREATE_PROFILE_ACK, profile.toString()), OK);
        }
    }
}
