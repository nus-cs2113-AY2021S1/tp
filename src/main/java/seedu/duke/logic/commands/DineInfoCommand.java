package seedu.duke.logic.commands;

import seedu.duke.DiningOptions;
import seedu.duke.FoodPlace;
import seedu.duke.FoodPlacesData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.printLine;

public class DineInfoCommand extends Command {

    private String userFoodPlace;

    public DineInfoCommand(String input) {
        this.userFoodPlace = input;
    }

    @Override
    public void executeCommand() throws CustomException {
        if (userFoodPlace.trim().length() == 0) {
            throw new CustomException(ExceptionType.INVALID_FOODPLACE);
        }
        printLine();
        ArrayList<FoodPlace> foodPlaceList = FoodPlacesData.getDiningOptionsInNus();
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
        if (searchList.size() == 0) {
            System.out.println("No match found.");
        } else {
            System.out.println("The stores that match your search:");
            for (DiningOptions item : searchList) {
                System.out.println("\n" + item.toString());
            }
        }
        printLine();
    }

}
