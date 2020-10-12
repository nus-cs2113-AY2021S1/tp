package seedu.duke.logic.commands;

<<<<<<< HEAD
import seedu.duke.Bus;
import seedu.duke.BusData;
import seedu.duke.exceptions.CustomException;
import seedu.duke.exceptions.ExceptionType;
import seedu.duke.ui.Ui;


=======
import seedu.duke.BusRoute;
import seedu.duke.exceptions.CustomException;
import seedu.duke.ui.Ui;

>>>>>>> 637c5325eb4fb5eb8d7c8408ba4927f49fecf2b1
public class RouteMapCommand extends Command {

    @Override
    public void executeCommand() {
<<<<<<< HEAD
        Ui.printRouteSelectionMessage();
        String userBusRouteSelection = Ui.getCommand().trim().toLowerCase();
        try {
            Bus selectedBus = BusData.selectBus(userBusRouteSelection);
            Ui.printFullRoute(selectedBus);
        } catch (NullPointerException e) {
            System.out.println("The bus code that you have provided is not valid!");
=======
        try {
            Ui.printRouteSelectionMessage();
            String userBusRouteSelection = Ui.getCommand().trim().toLowerCase();
            System.out.println(BusRoute.selectBusRoute(userBusRouteSelection));
        } catch (CustomException e) {
            System.out.println(e.getMessage());
>>>>>>> 637c5325eb4fb5eb8d7c8408ba4927f49fecf2b1
        }
    }
}
