package seedu.duke.logic.commands.dinecommand;

import seedu.duke.logic.commands.commons.Command;
import seedu.duke.model.foodoptions.FoodPlace;
import seedu.duke.model.foodoptions.FoodPlacesData;

import java.util.ArrayList;

import static seedu.duke.ui.Ui.printFacultyResult;
import static seedu.duke.ui.Ui.printLine;

public class FacultyCommand extends Command {

    @Override
    public void executeCommand() {
        printLine();
        ArrayList<FoodPlace> foodPlaceList = FoodPlacesData.getDiningOptionsInNus();
        printFacultyResult(foodPlaceList);
        printLine();
    }

}
