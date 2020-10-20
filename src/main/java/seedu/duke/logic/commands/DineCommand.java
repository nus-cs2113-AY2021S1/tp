package seedu.duke.logic.commands;

import seedu.duke.DiningOptions;
import seedu.duke.FoodPlace;
import seedu.duke.FoodPlacesData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.ArrayList;

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
        int displayCount = 0;
        ArrayList<FoodPlace> foodPlaceList = FoodPlacesData.getDiningOptionsInNus();
        for (FoodPlace foodPlace: foodPlaceList) {
            ArrayList<String> tempFacultyList = foodPlace.getFaculty();
            for (String tempFaculty: tempFacultyList) {
                if (tempFaculty.toLowerCase().contains(userFaculty.toLowerCase())){
                    ArrayList<DiningOptions> foodPlaceInfo = foodPlace.getInfo();
                    if (foodPlaceInfo.size() == 0){
                        System.out.println("There is no dining options in this faculty.");
                    }
                    else {
                        System.out.println("The dining options available at " + tempFaculty + " are:");
                        for (DiningOptions info: foodPlaceInfo) {
                            displayCount++;
                            System.out.println(displayCount + ". " + info.getName());
                        }
                        break;
                    }
                }
            }
        }
        if (displayCount == 0) {
            System.out.println("No match found.");
        }
        printLine();
    }
}
