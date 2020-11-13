package seedu.duke.logic.preparecommand;

import seedu.duke.Trakcal;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteAllCommand;
import seedu.duke.exception.ListNotFoundException;
import seedu.duke.model.DayMap;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.ExceptionMessages.print;

//@@author 1-Karthigeyan-1
/**
 * Prepares Delete All Command.
 */
public class PrepareDeleteAll extends PrepareDeleteCommand {
    DayMap dayMap;

    /**
     * Initializes PrepareDeleteAll command.
     *
     * @param description list of descripition parsed from parser.
     */
    public PrepareDeleteAll(String[] description) {
        super(description);
        this.dayMap = Trakcal.calList;
    }

    /**
     * Checks for validity of deleteAll description.
     *
     * @return DeleteAllCommand
     */
    @Override
    public Command prepareCommand() {
        try {
            checkList();
            if (isDeleteConfirmed()) {
                return new DeleteAllCommand();
            }
        } catch (ListNotFoundException e) {
            logging.writeToLogInfo("Exception! Empty List.");
            print("List is empty");
        } catch (NoSuchElementException e) {
            logging.writeToLogInfo("Exception! Input interrupted");
            print("Invalid input....aborting delete command.");
        }
        return null;
    }

    /**
     * Gets input from the user to confirm the delete command.
     *
     * @return true if the user says yes
     * @throws NoSuchElementException if user inputs ctrl-c.
     */
    public boolean isDeleteConfirmed() throws NoSuchElementException {
        System.out.println("Are you sure you want to delete all activities in today's list? [yes/no]");
        logging.writeToLogInfo("Prompt to delete all asked.");
        String userInput = Trakcal.in.nextLine().trim().toLowerCase();
        if (userInput.equals("yes")) {
            logging.writeToLogInfo("User input yes");
            return true;
        } else if (userInput.equals("no")) {
            logging.writeToLogInfo("User Input no.");
            print("Delete command aborted.");
        } else {
            print("Invalid input....aborting delete command.");
        }
        return false;

    }

    /**
     * Checks if the list if empty.
     *
     * @throws ListNotFoundException List is empty or not found.
     */
    private void checkList() throws ListNotFoundException {
        if (dayMap.getActivityList(LocalDate.now().atStartOfDay()).getNumberOfActivities() == 0) {
            throw new ListNotFoundException();
        }
    }
}


