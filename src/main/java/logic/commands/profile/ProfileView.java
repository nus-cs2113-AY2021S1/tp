package logic.commands.profile;

import diet.dietmanager.DietManager;
import exceptions.SchwarzeneggerException;
import exceptions.profile.InvalidSaveFormatException;
import logic.commands.Command;
import logic.commands.CommandResult;
import models.Profile;
import storage.profile.ProfileStorage;

import java.time.LocalDate;
import java.util.Objects;

import static logic.commands.ExecutionResult.FAILED;
import static profile.Constants.NORMAL_WEIGHT_AVERAGE;
import static profile.Constants.NORMAL_WEIGHT_THRESHOLD;
import static profile.Constants.UNDER_WEIGHT_THRESHOLD;
import static seedu.duke.Constants.COMMAND_WORD_VIEW;
import static seedu.duke.Constants.PATH_TO_DIET_FOLDER;
import static ui.CommonUi.EMPTY_STRING;
import static ui.profile.ProfileUi.MESSAGE_ENOUGH_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_MORE_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_PROFILE_NOT_EXIST;
import static ui.profile.ProfileUi.MESSAGE_SET_EXPECTED_WEIGHT;
import static ui.profile.ProfileUi.MESSAGE_VIEW_PROFILE;

//@@author tienkhoa16

/**
 * A representation of the command for viewing profile.
 */
public class ProfileView extends Command {
    private String pathToDietData;
    private LocalDate date;

    /**
     * Constructs ProfileView object.
     *
     * @param pathToDietData Path to diet folder to get total calories.
     * @param date Date to get total calories.
     */
    public ProfileView(String pathToDietData, LocalDate date) {
        super();
        this.pathToDietData = Objects.requireNonNullElse(pathToDietData, PATH_TO_DIET_FOLDER);
        this.date = Objects.requireNonNullElseGet(date, LocalDate::now);
    }

    /**
     * Constructs ProfileView object with default path to data file and current date.
     */
    public ProfileView() {
        this(PATH_TO_DIET_FOLDER, LocalDate.now());
    }

    /**
     * Overrides execute method of class Command to execute the view profile command requested by user's input.
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

        if (!commandArgs.isEmpty()) {
            ui.showWarning("\"view\" command does not take in parameters");
        }

        try {
            Profile profile = storage.loadData();
            assert profile != null : "profile should not be null after loading";

            return new CommandResult(String.format(MESSAGE_VIEW_PROFILE, profile.toString(),
                    getCalorieProgressMsg(profile), getExpectedWeightTip(profile)).trim());
        } catch (InvalidSaveFormatException e) {
            return new CommandResult(String.format(MESSAGE_PROFILE_NOT_EXIST, COMMAND_WORD_VIEW), FAILED);
        }
    }

    /**
     * Gets message for user's today calorie intake progress.
     *
     * @param profile User's profile.
     * @return Message for user's today calorie intake progress.
     */
    private String getCalorieProgressMsg(Profile profile) {
        double totalCalories = new DietManager().getDateTotalCalories(pathToDietData, date);
        double caloriesToGoal = profile.getCalories() - totalCalories;

        String caloriesMessage;
        if (caloriesToGoal > 0) {
            caloriesMessage = String.format(MESSAGE_MORE_CALORIES, caloriesToGoal);
        } else {
            caloriesMessage = MESSAGE_ENOUGH_CALORIES;
        }
        return caloriesMessage;
    }

    /**
     * Gets tip for setting expected weight to achieve Normal Weight classification.
     *
     * @param profile User's profile.
     * @return Tip for setting expected weight to achieve Normal Weight classification.
     */
    private String getExpectedWeightTip(Profile profile) {
        double bmiIndex = profile.calculateBmi(profile.getHeight(), profile.getExpectedWeight());

        if (bmiIndex < UNDER_WEIGHT_THRESHOLD || bmiIndex > NORMAL_WEIGHT_THRESHOLD) {
            double normalWeight = Math.pow((double) profile.getHeight() / 100, 2) * NORMAL_WEIGHT_AVERAGE;
            return String.format(MESSAGE_SET_EXPECTED_WEIGHT, normalWeight, normalWeight);
        }

        return EMPTY_STRING;
    }
}
