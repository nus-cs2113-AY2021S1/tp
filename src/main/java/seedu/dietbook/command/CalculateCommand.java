package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.ui.Ui;
import seedu.dietbook.checker.InputChecker;
import seedu.dietbook.exception.DietException;

import java.time.LocalDateTime;

public class CalculateCommand extends Command {
    int calorie;
    int carb;
    int protein;
    int fat;
    String param;

    public CalculateCommand(int calorie, int carb, int protein, int fat, String param) {
        this.calorie = calorie;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.param = param;
    }

    @Override
    public void execute(Manager manager, Ui ui) throws DietException {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        manager.setCalculator();
        String[] processedParam = this.param.split("\\s+");
        InputChecker.checkCalculateParam(processedParam);
        if (processedParam.length == 2) {
            InputChecker.checkDateValidity(processedParam[1]);
            startTime = LocalDateTime.parse(processedParam[1]);
            InputChecker.checkFutureDate(startTime);
            this.calorie = manager.getCalculator().calculateCalorie(startTime);
            this.carb = manager.getCalculator().calculateCarb(startTime);
            this.protein = manager.getCalculator().calculateProtein(startTime);
            this.fat = manager.getCalculator().calculateFat(startTime);
        } else if (processedParam.length == 3) {
            InputChecker.checkDateValidity(processedParam[1]);
            InputChecker.checkDateValidity(processedParam[2]);
            startTime = LocalDateTime.parse(processedParam[1]);
            InputChecker.checkFutureDate(startTime);
            endTime = LocalDateTime.parse(processedParam[2]);
            InputChecker.checkEndDate(startTime, endTime);
            this.calorie = manager.getCalculator().calculateCalorie(startTime, endTime);
            this.carb = manager.getCalculator().calculateCarb(startTime, endTime);
            this.protein = manager.getCalculator().calculateProtein(startTime, endTime);
            this.fat = manager.getCalculator().calculateFat(startTime, endTime);
        }

        switch (processedParam[0]) {
        case "all":
            if (processedParam.length == 1) {
                ui.printAllIntake(this.calorie, this.carb, this.protein, this.fat);
            } else if (processedParam.length == 2) {
                ui.printAllIntake(this.calorie, this.carb, this.protein, this.fat, startTime);
            } else if (processedParam.length == 3) {
                ui.printAllIntake(this.calorie, this.carb, this.protein, this.fat, startTime, endTime);
            }
            break;
        case "calorie":
            if (processedParam.length == 1) {
                ui.printCalorieIntake(this.calorie);
            } else if (processedParam.length == 2) {
                ui.printCalorieIntake(this.calorie, startTime);
            } else if (processedParam.length == 3) {
                calorie = manager.getCalculator().calculateCalorie(startTime, endTime);
                ui.printCalorieIntake(this.calorie, startTime, endTime);
            }
            break;
        case "carb":
            if (processedParam.length == 1) {
                ui.printCarbIntake(this.carb);
            } else if (processedParam.length == 2) {
                ui.printCarbIntake(this.carb, startTime);
            } else if (processedParam.length == 3) {
                ui.printCarbIntake(this.carb, startTime, endTime);
            }
            break;
        case "protein":
            if (processedParam.length == 1) {
                ui.printProteinIntake(this.protein);
            } else if (processedParam.length == 2) {
                ui.printProteinIntake(this.protein, startTime);
            } else if (processedParam.length == 3) {
                ui.printProteinIntake(this.protein, startTime, endTime);
            }
            break;
        case "fat":
            if (processedParam.length == 1) {
                ui.printFatIntake(this.fat);
            } else if (processedParam.length == 2) {
                ui.printFatIntake(this.fat, startTime);
            } else if (processedParam.length == 3) {
                ui.printFatIntake(this.fat, startTime, endTime);
            }
            break;
        default:
            throw new DietException("No such nutrient type!");
        }
    }
}
