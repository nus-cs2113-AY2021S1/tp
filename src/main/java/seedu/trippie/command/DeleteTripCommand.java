package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Trip;
import seedu.trippie.data.TrippieData;

public class DeleteTripCommand extends Command {

    public DeleteTripCommand() {

    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes the DeleteTripCommand. Will prompt the user for input from Scanner.in.
     *
     * @param ui Ui to read user input
     * @param trippieData TrippieData object to store user data in runtime
     */
    public void execute(Ui ui, TrippieData trippieData) {
        System.out.println("Here are your existing trips.");
        System.out.println(trippieData.list());
        int index = 0;
        do {
            try {
                System.out.print("Which one do you want to edit? Enter the index:");
                index = Integer.parseInt(ui.getLine());

                if (index <= 0) {
                    throw new IndexOutOfBoundsException();
                }

                trippieData.setCurrentTripFromIndex(index - 1);
                trippieData.loadCurrentTripFromFile();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid trip number!");
            } catch (IndexOutOfBoundsException e) {
                index = 0;
                System.out.println("Trip cannot be found! Please enter a valid trip number from the list.");
            }

        } while (index == 0);


        String tripName = trippieData.getTripFromIndex(index - 1).getName();
        Boolean confirmation = null;
        do {
            System.out.print("Are you sure you want to permanently delete '" + tripName + "'? [Y/N]:");

            String input = ui.getLine();

            if (input.toUpperCase().equals("Y")) {
                confirmation = true;
            } else if (input.toUpperCase().equals("N")) {
                confirmation = false;
            }

        } while (confirmation == null);

        if (confirmation) {
            Trip removedTrip = trippieData.removeTripFromIndex(index - 1);
            System.out.println("Deleted trip " + removedTrip.getName() + ".");
        } else {
            System.out.println("Cancelled trip deletion.");
        }
    }
}
