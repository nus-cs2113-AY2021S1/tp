package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Place;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.util.Collections;
import java.util.List;

public class AddPlaceCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "Incorrect format for [add] command! Please try the following:\n"
            + "Format: add /n PLACE_NAME /d DAY /t TIME\n"
            + "Example: add /n Dinner at Marina Bay Sands /d 2 /t 1800 to 2000";
    private static final String PARAMETER_ERROR_MESSAGE = "Please check that your parameter(s) are in proper form.";
    private static final String TIME_ERROR_MESSAGE = "Please check that your TIME parameters are in the correct "
            + "24-hours format";
    private static final String TIME_TRAVELLER_ERROR_MESSAGE = "Are you a time traveller? Your END TIME should "
            + "end after START TIME";
    private static final String NEGATIVE_DAY_MESSAGE = "Trippie doesn't know how to deal with negative days.";

    private final String name;
    private final int day;
    private final int start;
    private final int end;

    /**
     * Adds the place to the list of places to be visited.
     *
     * @param userInput Command inputted by the user.
     * @throws TrippieInvalidArgumentException If index is not found and if input has incorrect format.
     */
    public AddPlaceCommand(String userInput) throws TrippieInvalidArgumentException {
        try {
            this.name = extractName(userInput);
            this.day = extractDay(userInput);
            this.start = extractStartTime(userInput);
            this.end = extractEndTime(userInput);
            char[] characters = userInput.toCharArray();
            if (characters[3] != ' ') {
                throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TrippieInvalidArgumentException(FORMAT_ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            throw new TrippieInvalidArgumentException(PARAMETER_ERROR_MESSAGE);
        }
    }

    /**
     * Extracts the name of the place.
     *
     * @param userInput Command input by the user.
     * @return String of the place name.
     */
    public String extractName(String userInput) {
        String inputWithoutDay = userInput.split(" /d ")[0];
        return inputWithoutDay.split(" /n ")[1];
    }

    /**
     * Extracts the day the place is going to be visited.
     *
     * @param userInput Command input by the user.
     * @return Integer of the day.
     * @throws TrippieInvalidArgumentException if negative day is inputted in the command.
     */
    public int extractDay(String userInput) throws TrippieInvalidArgumentException {
        String inputWithoutTime = userInput.split(" /t ")[0];
        String day = inputWithoutTime.split(" /d ")[1];
        if (Integer.parseInt(day.trim()) <= 0) {
            throw new TrippieInvalidArgumentException(NEGATIVE_DAY_MESSAGE);
        }
        return Integer.parseInt(day.trim());
    }

    /**
     * Extracts the starting time of the place visit.
     *
     * @param userInput Command input by the user.
     * @return Integer of the starting time.
     * @throws TrippieInvalidArgumentException if the time format is wrong.
     */
    public int extractStartTime(String userInput) throws TrippieInvalidArgumentException {
        return extractTime(userInput, true);
    }

    /**
     * Extracts the starting/ending time of the place visit.
     *
     * @param userInput Command input by the user.
     * @param isStart   Boolean to determine whether to return start/end time
     * @return Integer of the starting/ending time.
     * @throws TrippieInvalidArgumentException if the time format is wrong.
     */
    private int extractTime(String userInput, boolean isStart) throws TrippieInvalidArgumentException {
        String time = userInput.split(" /t ")[1];
        String start = time.split("to")[0];
        String end = time.split("to")[1];
        int parsedTime;
        if (isStart) {
            parsedTime = Integer.parseInt(start.trim());
        } else {
            parsedTime = Integer.parseInt(end.trim());
        }
        int hours = parsedTime / 100;
        int minutes = parsedTime % 100;
        if (minutes >= 60 || minutes < 0 || hours >= 24 || hours < 0) {
            throw new TrippieInvalidArgumentException(TIME_ERROR_MESSAGE);
        }
        return parsedTime;
    }

    /**
     * Extracts the ending time of the place visit.
     *
     * @param userInput Command input by the user.
     * @return Integer of the starting time.
     * @throws TrippieInvalidArgumentException if ending time is before start time or the time format is wrong.
     */
    public int extractEndTime(String userInput) throws TrippieInvalidArgumentException {
        int endTime = extractTime(userInput, false);
        if (endTime < this.start) {
            throw new TrippieInvalidArgumentException(TIME_TRAVELLER_ERROR_MESSAGE);
        }
        return endTime;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out successful add place message and add place to the placeList.
     *
     * @param ui          User Interface of the program.
     * @param trippieData The current trip placeList that would updated.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        List<Place> places = trippieData.getCurrentTrip().getPlaceListObject().getPlaceList();
        Place newPlace = new Place(name, day, start, end);
        places.add(newPlace);
        if (places.size() > 1) {
            sortPlaceList(places);
        }
        System.out.println("Got it. I've added this place:");
        System.out.println(newPlace);
        System.out.printf("Now you have %d %s in the list.%n", places.size(), places.size() > 1 ? "places" : "place");
        int placesSize = places.size();
        assert placesSize > 0 : "placesSize should be greater than 0";
        trippieData.getCurrentTrip().getPlaceListObject().setPlaceList(places);
    }

    /**
     * Sorts the placeList everytime new place is added.
     *
     * @param sortedPlaces List of place to be sorted.
     */
    public void sortPlaceList(List<Place> sortedPlaces) {
        boolean isSwapped = false;
        for (int i = (sortedPlaces.size() - 1); i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (sortedPlaces.get(i).getPlaceDay() == sortedPlaces.get(j).getPlaceDay()
                        && sortedPlaces.get(i).getPlaceStartTime() < sortedPlaces.get(j).getPlaceStartTime()) {
                    Collections.swap(sortedPlaces, i, j);
                    isSwapped = true;
                } else if (sortedPlaces.get(i).getPlaceDay() < sortedPlaces.get(j).getPlaceDay()) {
                    Collections.swap(sortedPlaces, i, j);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }
}
