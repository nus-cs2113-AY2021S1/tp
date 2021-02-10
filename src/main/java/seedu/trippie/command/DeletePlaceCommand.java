package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Place;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.util.List;

public class DeletePlaceCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "You typed in the incorrect format for [delete /p] command! "
            + "Please try the following:\nFormat: delete /p PLACE_INDEX\nExample: delete /p 1";
    private static final String PARAMETER_ERROR_MESSAGE = "Please check that the index keyed in is a number.";
    private static final String NULL_ERROR_MESSAGE = "Sorry I can't find the place. Please enter a valid index.";

    private final int placeIndex;

    /**
     * Deletes the place based on user input.
     *
     * @param userInput Command inputted by the user.
     * @throws TrippieInvalidArgumentException If index is not found and if input has incorrect format.
     */
    public DeletePlaceCommand(String userInput) throws TrippieInvalidArgumentException {
        try {
            String index = userInput.split(" /p ")[1];
            placeIndex = Integer.parseInt(index) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            throw new TrippieInvalidArgumentException(PARAMETER_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out successful delete place message and removes place from the placeList.
     *
     * @param ui          User Interface of the program.
     * @param trippieData The current trip placeList that would updated.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        List<Place> places = trippieData.getCurrentTrip().getPlaceListObject().getPlaceList();
        if (placeIndex >= 0 && placeIndex < places.size()) {
            System.out.println("Noted. I've removed this place from the place list.");
            System.out.println(places.get(placeIndex).toString());
            places.remove(placeIndex);
            System.out.printf("Now you have %d %s in the list.%n", places.size(), places.size() > 1 ? "places" :
                    "place");
        } else {
            System.out.println(NULL_ERROR_MESSAGE);
        }
        trippieData.getCurrentTrip().getPlaceListObject().setPlaceList(places);
    }
}
