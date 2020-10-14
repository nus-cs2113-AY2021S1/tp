package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;

import java.text.ParseException;

public class AddUserCommand extends Command {
    String name = null;
    String dob = null;
    String gender = null;

    public AddUserCommand(String description) {
        String[] descriptionSplit = description.split(" ", 7);

        for (int i = 0; i < descriptionSplit.length - 1; i++) {
            switch (descriptionSplit[i]) {
            case "-n":
                name = descriptionSplit[i + 1];
                break;
            case "-dob":
                dob = descriptionSplit[i + 1];
                break;
            case "-g":
                gender = descriptionSplit[i + 1];
                break;
            default:
                // Continue!
            }
        }
    }


    @Override
    public String execute(AnimeData animeData, UserManagement userManagement) throws AniException {
        if (name.isEmpty() || dob.isEmpty() || gender.isEmpty()) {
            throw new AniException("Invalid parameters detected!");
        }

        try {
            return "Successfully added new user: " + userManagement.addUser(name, dob, gender);
        } catch (ParseException e) {
            throw new AniException(e.getMessage());
        }
    }
}
