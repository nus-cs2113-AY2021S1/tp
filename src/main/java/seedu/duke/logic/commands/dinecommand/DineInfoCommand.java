package seedu.duke.logic.commands.dinecommand;

import seedu.duke.model.foodoptions.DiningOptions;
import seedu.duke.model.foodoptions.FoodPlace;
import seedu.duke.model.foodoptions.FoodPlacesData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.logic.commands.commons.Command;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.printDineInfoResult;
import static seedu.duke.ui.Ui.printLine;

public class DineInfoCommand extends Command {

    private String userFoodPlace;

    public DineInfoCommand(String input) {
        this.userFoodPlace = input;
        super.isValid = false;
    }

    @Override
    public void executeCommand() throws CustomException {
        if (userFoodPlace.trim().length() == 0) {
            throw new CustomException(ExceptionType.INVALID_FOODPLACE);
        }
        printLine();
        ArrayList<FoodPlace> foodPlaceList = FoodPlacesData.getDiningOptionsInNus();
        checkFoodPlace(foodPlaceList);
        printLine();
    }

    /**
     * Checks for any match between the user input and the data, and adds the matching data to searchList.
     * Calls printDineInfoResult() to print the data in searchList if size of searchList > 0.
     *
     * @param foodPlaceList contains data of all dining options
     * @throws CustomException thrown if searchList is empty
     */
    private void checkFoodPlace(ArrayList<FoodPlace> foodPlaceList) throws CustomException {
        assert foodPlaceList != null : "Data not available.";
        ArrayList<DiningOptions> searchList = new ArrayList<>();
        for (FoodPlace foodPlace : foodPlaceList) {
            ArrayList<DiningOptions> dineInfoList = foodPlace.getInfo();
            for (DiningOptions dineInfo : dineInfoList) {
                String name = dineInfo.getName();
                if (name.toLowerCase().contains(userFoodPlace.trim().toLowerCase())) {
                    searchList.add(dineInfo);
                }
            }
        }
        if (searchList.size() > 0) {
            isValid = true;
        }
        if (searchList.size() == 0) {
            throw new CustomException(ExceptionType.NO_MATCH_FOODPLACE);
        } else {
            printDineInfoResult(searchList);
        }
    }

}
