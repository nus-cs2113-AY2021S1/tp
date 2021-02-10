package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Place;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.util.List;

public class SearchCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "You typed in the incorrect format for [search KEYWORD] command!"
            + " Please try the following:\nFormat: search KEYWORD\nExample: search disneyland";

    private final String keyword;

    /**
     * Parses user input to initialize specified keyword.
     *
     * @param command Command input by the user.
     * @throws TrippieInvalidArgumentException if user inputs command with formatting issues.
     */
    public SearchCommand(String command) throws TrippieInvalidArgumentException {
        String parsedResponse;
        try {
            parsedResponse = command.split(" ", 2)[1];
            this.keyword = parsedResponse;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out search result.
     *
     * @param ui User interface of the program.
     * @param trippieData The current trip placeList to fetch data from.
     */
    public void execute(Ui ui, TrippieData trippieData) {
        List<Place> list = trippieData.getCurrentTrip().getPlaceListObject().getPlaceList();
        int size = list.size();
        if (size == 0) {
            System.out.println("There is currently nothing in your place list. Why not add one?");
        } else {
            System.out.printf("Here is your search result:%n");
            int startingNumber = 1;
            for (Place place : list) {
                if (place.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println(startingNumber + "." + place.toString() + " on DAY " + place.getPlaceDay());
                    startingNumber++;
                }
            }
            if (startingNumber == 1) {
                System.out.println("Sorry I can't find any place with the keyword \"" + keyword + "\"");
            }
        }
    }
}
