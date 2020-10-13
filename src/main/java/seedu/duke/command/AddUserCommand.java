package seedu.duke.command;

import seedu.duke.anime.AnimeData;
import seedu.duke.exception.AniException;
import seedu.duke.human.UserManagement;
import seedu.duke.watchlist.Watchlist;

import java.text.ParseException;
import java.util.ArrayList;

public class AddUserCommand extends Command {
    String name = null;
    String dob = null;
    String gender = null;

    public AddUserCommand(String userInput) throws AniException {
        try {
            String[] parametersSplit = userInput.split("-");

            for (String s : parametersSplit) {
                String[] parameterTextSplit = s.split(" ", 2);

                if (parameterTextSplit.length == 2 && !parameterTextSplit[0].isEmpty()) {
                    switch (parameterTextSplit[0]) {
                    case "n":
                        name = parameterTextSplit[1];
                        break;
                    case "dob":
                        dob = parameterTextSplit[1];
                        break;
                    case "g":
                        gender = parameterTextSplit[1];
                        break;
                    default:
                        // Continue!
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AniException("Invalid parameters detected!");
        }
    }


    @Override
    public String execute(AnimeData animeData, ArrayList<Watchlist> activeWatchlistList, Watchlist activeWatchlist,
                          UserManagement userManagement) throws AniException {
        if (name != null || dob != null || gender != null) {
            throw new AniException("Invalid parameters detected!");
        }

        try {
            return "Successfully added new user: " + userManagement.addUser(name, dob, gender);
        } catch (ParseException e) {
            throw new AniException(e.getMessage());
        }
    }
}
