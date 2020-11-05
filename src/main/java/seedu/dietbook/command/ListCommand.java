package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.list.FoodList;

import java.time.LocalDateTime;

public class ListCommand extends Command {
    String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        LocalDateTime startTime;
        LocalDateTime endTime;
        FoodList foodList = manager.getFoodList();
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        String[] processedInput = this.userInput.split("\\s+");
        InputChecker.checkList(processedInput);
        if (processedInput.length == 1) {
            ui.printFoodList(manager.getFoodList().toString());
        } else {
            try {
                if (processedInput.length == 2) {
                    startTime = LocalDateTime.parse(processedInput[1]);
                    InputChecker.checkFutureDate(startTime);
                    ui.printFoodList(foodList.getAfterDateTimeToString(startTime), startTime);
                } else if (processedInput.length == 3) {
                    startTime = LocalDateTime.parse(processedInput[1]);
                    endTime = LocalDateTime.parse(processedInput[2]);
                    InputChecker.checkFutureDate(startTime);
                    InputChecker.checkEndDate(startTime, endTime);
                    ui.printFoodList(foodList.getInDateTimeRangeToString(startTime,endTime), startTime, endTime);
                }
            } catch (Exception e) {
                throw new DietException("Wrong date time format (Format: yyyy-mm-ddTHH:mm) or future date entered!");
            }
        }
    }
}
