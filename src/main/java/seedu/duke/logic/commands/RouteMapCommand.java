package seedu.duke.logic.commands;

import seedu.duke.BusRoute;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class RouteMapCommand extends Command{
    /*External classes used*/
    private static final Scanner userInput = new Scanner(System.in);

    @Override
    public void executeCommand(){
        try {
            Ui.printRouteSelectionMessage();
            String userBusRouteSelection = userInput.nextLine().trim().toLowerCase();
            System.out.println(BusRoute.selectBusRoute(userBusRouteSelection));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
