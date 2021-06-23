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
import static logic.commands.ExecutionResult.SKIPPED;
import static logic.parser.ProfileParser.extractCalories;
import static logic.parser.ProfileParser.extractCommandTagAndInfo;
import static logic.parser.ProfileParser.extractExpectedWeight;
import static logic.parser.ProfileParser.extractHeight;
import static logic.parser.ProfileParser.extractName;
import static logic.parser.ProfileParser.extractWeight;
import static logic.parser.ProfileParser.findInvalidTags;
import static profile.Constants.CALORIES_UPPER_BOUND;
import static seedu.duke.Constants.COMMAND_WORD_EDIT;
import static ui.profile.ProfileUi.MESSAGE_ADJUST_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_EDIT_NOTHING;
import static ui.profile.ProfileUi.MESSAGE_EDIT_PROFILE_ACK;
import static ui.profile.ProfileUi.MESSAGE_PROFILE_NOT_EXIST;

//@@author tienkhoa16

/**
 * A representation of the command for editing profile.
 */
public class ProfileEdit extends Command {

    /**
     * Overrides execute method of class Command to execute the edit profile command requested by user's input.
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

        try {
            Profile profile = storage.loadData();
            assert profile != null : "profile should not be null after loading";

            HashMap<String, String> parsedParams = extractCommandTagAndInfo(COMMAND_WORD_EDIT, commandArgs);

            String invalidTags = findInvalidTags(parsedParams);
            if (!invalidTags.isEmpty()) {
                ui.showWarning("\"edit\" command does not take in the following parameter(s): " + invalidTags);
            }

            Profile editedProfile = createEditedProfile(parsedParams, profile);

            if (profile.equals(editedProfile)) {
                return new CommandResult(MESSAGE_EDIT_NOTHING, SKIPPED);
            }

            storage.saveData(editedProfile);

            return new CommandResult(String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString()), OK);
        } catch (InvalidSaveFormatException e) {
            return new CommandResult(String.format(MESSAGE_PROFILE_NOT_EXIST, COMMAND_WORD_EDIT), FAILED);
        }
    }

    /**
     * Creates a new Profile object from edited information.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @param profile User's existing profile.
     * @return Edited Profile object.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    private Profile createEditedProfile(HashMap<String, String> parsedParams, Profile profile)
            throws SchwarzeneggerException {
        String name = parsedParams.containsKey("/n") ? extractName(parsedParams) : profile.getName();
        int height = parsedParams.containsKey("/h") ? extractHeight(parsedParams) : profile.getHeight();
        double weight = parsedParams.containsKey("/w") ? extractWeight(parsedParams) : profile.getWeight();
        double expectedWeight = parsedParams.containsKey("/e")
                ? extractExpectedWeight(parsedParams) : profile.getExpectedWeight();
        double calories = parsedParams.containsKey("/c") ? extractCalories(parsedParams) : profile.getCalories();
        double adjustedCalories = Math.min(calories, CALORIES_UPPER_BOUND);

        if (calories > CALORIES_UPPER_BOUND) {
            ui.showWarning(MESSAGE_ADJUST_CALORIES);
        }

        return new Profile(name, height, weight, expectedWeight, adjustedCalories);
    }
}
