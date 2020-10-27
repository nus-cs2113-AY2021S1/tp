package seedu.dietbook.command;

import seedu.dietbook.Manager;
import seedu.dietbook.Ui;
import seedu.dietbook.exception.DietException;

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
        if (commandCount == 1) {
            throw new DietException("Please enter your name first!");
        } else if (commandCount == 2) {
            throw new DietException("Please enter your basic information first!");
        }
        manager.setCalculator();
        switch (this.param) {
        case "all":
            //ui.printAllIntakeAndFoods();
            //ui.printAllNutrientIntake(this.calorie, this.carb, this.protein, this.fat);
            break;
        case "calorie":
            //ui.printCalorieIntakeAndFoods();
            //ui.printCalorieIntake(this.calorie);
            break;
        case "carbohydrate":
            //ui.printCarbIntakeAndFoods();
            //ui.printCarbohydrateIntake(this.carb);
            break;
        case "protein":
            //ui.printProteinIntakeAndFoods();
            //ui.printProteinIntake(this.protein);
            break;
        default:
            //ui.printFatIntakeAndFoods();
            //ui.printFatIntake(this.fat);
        }
    }
}
