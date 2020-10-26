package seedu.duke.logic.commands.dinecommand;

import seedu.duke.model.foodoptions.DiningOptions;
import seedu.duke.model.foodoptions.FoodPlace;
import seedu.duke.model.foodoptions.FoodPlacesData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.commons.Command;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.printDineResult;
import static seedu.duke.ui.Ui.printLine;

public class DineCommand extends Command {

    private String userFaculty;

    public DineCommand(String input) {
        this.userFaculty = input;
    }

    @Override
    public void executeCommand() throws CustomException {
        if (userFaculty.trim().length() == 0) {
            throw new CustomException(ExceptionType.INVALID_FACULTY);
        }
        printLine();
        ArrayList<FoodPlace> foodPlaceList = FoodPlacesData.getDiningOptionsInNus();
        checkFaculty(foodPlaceList);
        printLine();
    }

    public void checkFaculty(ArrayList<FoodPlace> foodPlaceList) {
        int displayCount = 0;
        for (FoodPlace foodPlace : foodPlaceList) {
            ArrayList<String> tempFacultyList = foodPlace.getFaculty();
            for (String tempFaculty : tempFacultyList) {
                if (tempFaculty.toLowerCase().contains(userFaculty.trim().toLowerCase())) {
                    ArrayList<DiningOptions> foodPlaceInfo = foodPlace.getInfo();
                    if (foodPlaceInfo.size() == 0) {
                        System.out.println("There are no dining options in this faculty.");
                    } else {
                        displayCount = printDineResult(displayCount, tempFaculty, foodPlaceInfo);
                        break;
                    }
                }
            }
        }
        if (displayCount == 0) {
            System.out.println("No match found.");
        }
    }

}
