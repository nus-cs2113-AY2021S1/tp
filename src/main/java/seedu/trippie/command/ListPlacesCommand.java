package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Place;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieInvalidArgumentException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListPlacesCommand extends Command {

    private static final String FORMAT_ERROR_MESSAGE = "You typed in the incorrect format for [list /p] command! "
            + "Please try the following:\nFormat:\nlist /p\nlist /p /d DAY_INDEX";
    private static final String PARAMETER_ERROR_MESSAGE = "Please check that the index keyed in is a number.";

    private final int specifiedDay;

    /**
     * Takes in list command from the user.
     * Initializes the specified day to be used in execute() method.
     *
     * @param userInput Command input by the user.
     * @throws TrippieInvalidArgumentException if input has formatting error, or specified day has incorrect parameters.
     */
    public ListPlacesCommand(String userInput) throws TrippieInvalidArgumentException {
        specifiedDay = getSpecifiedDay(userInput);
    }

    /**
     * Parses specified day to display in the list, if any.
     *
     * @param userInput Command input by user.
     * @return Specific day that user request to display, if any.
     * @throws TrippieInvalidArgumentException if input has formatting error, or specified day has incorrect parameters.
     */
    public int getSpecifiedDay(String userInput) throws TrippieInvalidArgumentException {
        try {
            String[] specificDay = userInput.split("/p");
            if (specificDay.length == 1) {
                return -1;
            } else {
                String[] finalDay = specificDay[1].split("/d");
                return Integer.parseInt(finalDay[1].trim());
            }
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
     * Prints out list of place added by user for all days, unless specified otherwise.
     *
     * @param ui User interface of the program.
     * @param trippieData The current trip placeList to fetch data from.
     */
    public void execute(Ui ui, TrippieData trippieData) {
        List<Place> places = trippieData.getCurrentTrip().getPlaceListObject().getPlaceList();
        Date date = trippieData.getCurrentTrip().getStartDate();

        if (places.size() == 0) {
            System.out.println("There is currently nothing in your place list. Why not add one?");
        } else if (specifiedDay == -1) {
            int listIndex = 1;
            int dayPointer = places.get(0).getPlaceDay();
            String currentDate = addDays(date, dayPointer - 1);
            System.out.println("DAY " + dayPointer + ": (" + currentDate + ")");
            for (Place place : places) {
                if (dayPointer != place.getPlaceDay()) {
                    dayPointer = place.getPlaceDay();
                    currentDate = addDays(date, dayPointer - 1);
                    System.out.println("DAY " + dayPointer + ": (" + currentDate + ")");
                }
                System.out.println("[" + listIndex + "] " + place.toString());
                listIndex++;
            }
        } else {
            String currentDate = addDays(date, specifiedDay - 1);
            System.out.println("DAY " + specifiedDay + ": (" + currentDate + ")");
            for (Place place : places) {
                if (place.getPlaceDay() == specifiedDay) {
                    System.out.println(place.toString());
                }
            }
        }
        trippieData.getCurrentTrip().getPlaceListObject().setPlaceList(places);
    }

    /**
     * Add days to start of trip date.
     *
     * @param date Initial start date.
     * @param day Number of days to add.
     * @return Final date after addition.
     */
    public String addDays(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }

}
