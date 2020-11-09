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
        LocalDateTime startTime;
        LocalDateTime endTime;
        int calorie;
        int carb;
        int protein;
        int fat;
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        manager.setCalculator();
        String[] processedParam = this.param.split("\\s+");
        InputChecker.checkCalculateParam(processedParam);

        switch (processedParam[0]) {
        case "all":
            if (processedParam.length == 1) {
                ui.printAllIntake(this.calorie, this.carb, this.protein, this.fat);
            } else if (processedParam.length == 2) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                calorie = manager.getCalculator().calculateCalorie(startTime);
                carb = manager.getCalculator().calculateCarb(startTime);
                protein = manager.getCalculator().calculateProtein(startTime);
                fat = manager.getCalculator().calculateFat(startTime);
                ui.printAllIntake(calorie, carb, protein, fat, startTime);
            } else if (processedParam.length == 3) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                endTime = LocalDateTime.parse(processedParam[2]);
                InputChecker.checkEndDate(startTime, endTime);
                calorie = manager.getCalculator().calculateCalorie(startTime, endTime);
                carb = manager.getCalculator().calculateCarb(startTime, endTime);
                protein = manager.getCalculator().calculateProtein(startTime, endTime);
                fat = manager.getCalculator().calculateFat(startTime, endTime);
                ui.printAllIntake(calorie, carb, protein, fat, startTime, endTime);

            }
            break;
        case "calorie":
            if (processedParam.length == 1) {
                ui.printCalorieIntake(this.calorie);
            } else if (processedParam.length == 2) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                calorie = manager.getCalculator().calculateCalorie(startTime);
                ui.printCalorieIntake(calorie, startTime);
            } else if (processedParam.length == 3) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                endTime = LocalDateTime.parse(processedParam[2]);
                InputChecker.checkEndDate(startTime, endTime);
                calorie = manager.getCalculator().calculateCalorie(startTime, endTime);
                ui.printCalorieIntake(calorie, startTime, endTime);
            }
            break;
        case "carb":
            if (processedParam.length == 1) {
                ui.printCarbIntake(this.carb);
            } else if (processedParam.length == 2) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                carb = manager.getCalculator().calculateCarb(startTime);
                ui.printCarbIntake(carb, startTime);
            } else if (processedParam.length == 3) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                endTime = LocalDateTime.parse(processedParam[2]);
                InputChecker.checkEndDate(startTime, endTime);
                carb = manager.getCalculator().calculateCarb(startTime, endTime);
                ui.printCarbIntake(carb, startTime, endTime);
            }
            break;
        case "protein":
            if (processedParam.length == 1) {
                ui.printProteinIntake(this.protein);
            } else if (processedParam.length == 2) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                protein = manager.getCalculator().calculateProtein(startTime);
                ui.printProteinIntake(protein, startTime);
            } else if (processedParam.length == 3) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                endTime = LocalDateTime.parse(processedParam[2]);
                InputChecker.checkEndDate(startTime, endTime);
                protein = manager.getCalculator().calculateProtein(startTime, endTime);
                ui.printProteinIntake(protein, startTime, endTime);
            }
            break;
        case "fat":
            if (processedParam.length == 1) {
                ui.printFatIntake(this.fat);
            } else if (processedParam.length == 2) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                fat = manager.getCalculator().calculateFat(startTime);
                ui.printFatIntake(fat, startTime);
            } else if (processedParam.length == 3) {
                startTime = LocalDateTime.parse(processedParam[1]);
                InputChecker.checkFutureDate(startTime);
                endTime = LocalDateTime.parse(processedParam[2]);
                InputChecker.checkEndDate(startTime, endTime);
                fat = manager.getCalculator().calculateFat(startTime, endTime);
                ui.printFatIntake(fat, startTime, endTime);
            }
            break;
        default:
            throw new DietException("No such nutrient type!");
        }
    }
}
