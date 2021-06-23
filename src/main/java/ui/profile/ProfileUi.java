package ui.profile;

import ui.CommonUi;

import static profile.Constants.CALORIES_LOWER_BOUND;
import static profile.Constants.CALORIES_UPPER_BOUND;
import static profile.Constants.HEIGHT_LOWER_BOUND;
import static profile.Constants.HEIGHT_UPPER_BOUND;
import static profile.Constants.WEIGHT_LOWER_BOUND;
import static profile.Constants.WEIGHT_UPPER_BOUND;

//@@author tienkhoa16

/**
 * A class that contains templates of message to show to user.
 */
public class ProfileUi extends CommonUi {

    public static final String MESSAGE_ADJUST_CALORIES = "Your expected daily calorie intake seems a little "
            + "high, so I've set it to 200,000";
    public static final String MESSAGE_CREATE_PROFILE_ACK = "Got it. Here's a confirmation of your profile:"
            + LS + "%s";
    public static final String MESSAGE_DELETE_PROFILE = String.format(MESSAGE_CLEAR_FORMAT, "profile has");
    public static final String MESSAGE_EDIT_PROFILE_ACK = "Yay! Your profile is edited successfully. "
            + "Here's your new profile:" + LS + "%s";
    public static final String MESSAGE_END = "Returning to Main Menu...";
    public static final String MESSAGE_ENOUGH_CALORIES = "you've achieved your target calories intake for today!";
    public static final String MESSAGE_HELP_FOR_MORE_INFO = "For more information on command syntax, please type "
            + "\"help\".";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Wrong format, please enter in the format:" + LS
            + "%s" + LS + MESSAGE_HELP_FOR_MORE_INFO;
    public static final String MESSAGE_MORE_CALORIES = "take %.1f more calories to achieve your target for today!";
    public static final String MESSAGE_SET_EXPECTED_WEIGHT = "TIP: Edit your expected weight to %.1f kg to have "
            + "Normal Weight BMI classification." + LS + "     Just type \"edit /e %.1f\"!";
    public static final String MESSAGE_PROFILE_EXIST = "There's currently a profile in the database. " + LS
            + "Please delete it using \"delete\" command before adding a new one.";
    public static final String MESSAGE_PROFILE_NOT_EXIST = "There's no profile to %s. "
            + "Please add a new one using \"add\" command :D";
    public static final String MESSAGE_VIEW_PROFILE = "Here's your profile:" + LS + "%s" + LS + "By the way, %s"
            + LS + "%s";
    public static final String MESSAGE_WELCOME_EXISTING_USER = "Welcome back to The Schwarzenegger, %s!" + LS
            + "How can I help you today?";
    public static final String MESSAGE_WELCOME_NEW_USER = "Welcome new user to The Schwarzenegger! :D" + LS
            + "Please add your profile under Profile Menu." + LS + MESSAGE_HELP_FOR_MORE_INFO;
    public static final String MESSAGE_WELCOME_WITH_INVALID_SAVE_FORMAT = "Welcome back to The Schwarzenegger!" + LS
            + "It seems like your profile data is corrupted!" + LS
            + "Please add your profile again under Profile Menu." + LS + MESSAGE_HELP_FOR_MORE_INFO;
    public static final String SAD_FACE = ":(  OOPS!!! ";
    public static final String MESSAGE_INVALID_WEIGHT = String.format(
            SAD_FACE + "Please input current weight/ expected weight as a number from %.1f to %.1f.",
            WEIGHT_LOWER_BOUND, WEIGHT_UPPER_BOUND);
    public static final String MESSAGE_EDIT_NOTHING = SAD_FACE
            + "The information you entered is the same as your current profile!";
    public static final String MESSAGE_DELETE_NOTHING = SAD_FACE + "You haven't added any profile yet!";
    public static final String MESSAGE_SAVING_ERROR = SAD_FACE + "An error has occurred while saving data."
            + LS + "%s";
    public static final String MESSAGE_LOADING_ERROR = SAD_FACE + "An error has occurred while loading data."
            + LS + "%s";
    public static final String MESSAGE_INVALID_HEIGHT = String.format(
            SAD_FACE + "Please input height as an integer from %d to %d.", HEIGHT_LOWER_BOUND, HEIGHT_UPPER_BOUND);
    public static final String MESSAGE_INVALID_CALORIES = SAD_FACE
            + String.format("Please input expected daily calorie intake as a number from %.1f to %.1f",
            CALORIES_LOWER_BOUND, CALORIES_UPPER_BOUND);
    public static final String MESSAGE_INVALID_NAME = SAD_FACE + "Name cannot be empty.";
    public static final String MESSAGE_INVALID_SAVE_FORMAT = SAD_FACE + "Save format in %s is invalid.";
    public static final String MESSAGE_INVALID_COMMAND_WORD = SAD_FACE + "Sorry, but I don't know what that means."
            + LS + "Please enter \"help\" for a list of available commands.";
}
