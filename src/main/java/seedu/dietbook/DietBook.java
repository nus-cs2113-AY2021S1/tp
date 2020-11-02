package seedu.dietbook;

import seedu.dietbook.database.DataBase;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.command.Command;
import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.person.Gender;
import seedu.dietbook.saveload.FoodSaveLoadManager;
import seedu.dietbook.saveload.PersonSaveLoadManager;

/**
 * Main class of the program.
 * The DietBook program is an application which can store, display and check your daily dietary intake.
 *
 * @author tikimonarch
 */

import java.io.FileNotFoundException;

public class DietBook {
    private FoodList foodList;
    private Ui ui;
    private Manager manager;
    private DataBase dataBase;
    private PersonSaveLoadManager loadPerson;
    private FoodSaveLoadManager loadFood;
    public static boolean isExit = false;

    /**
     * Constructor for new DietBook.
     */
    public DietBook() {
        ui = new Ui();
        loadFood = new FoodSaveLoadManager();
        loadPerson = new PersonSaveLoadManager();
        foodList = new FoodList();
        dataBase = new DataBase();
        dataBase.init();
        manager = new Manager(foodList, dataBase);
    }

    /**
     * Method to load Person data.
     */
    public void loadPerson() {
        try {
            loadPerson.load("resources/UserInfo.txt");
            ActivityLevel actLvl = ActivityLevel.NONE;
            int actLvlInt = loadPerson.getActivityLevel();
            if (actLvlInt == 1) {
                actLvl = ActivityLevel.NONE;
            } else if (actLvlInt == 2) {
                actLvl = ActivityLevel.LOW;
            } else if (actLvlInt == 3) {
                actLvl = ActivityLevel.MEDIUM;
            } else if (actLvlInt == 4) {
                actLvl = ActivityLevel.HIGH;
            } else if (actLvlInt == 5) {
                actLvl = ActivityLevel.EXTREME;
            }

            Gender gender;
            String genderString = loadPerson.getGender();
            if (genderString.equals("Male")) {
                gender = Gender.MALE;
            } else if (genderString.equals("Female")) {
                gender = Gender.FEMALE;
            } else {
                gender = Gender.OTHERS;
            }
            manager.getPerson().setAll(loadPerson.getName(), gender, loadPerson.getAge(),
                    loadPerson.getHeight(), loadPerson.getOriginalWeight(), loadPerson.getCurrentWeight(),
                    loadPerson.getTargetWeight(), actLvl);

        } catch (FileNotFoundException e) {
            ui.printErrorMessage("Person data file not found!");
        } catch (IllegalAccessException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Method to load DietBook FoodList data.
     */
    public void loadFood() {
        try {
            loadFood.load("resources/UserInfo.txt");
            loadFood.getFoodList();
            //foodList = new FoodList(loadFood.getFoodList());
            ui.printWelcomeBackMessage(manager.getPerson().getName());
        } catch (FileNotFoundException e) {
            ui.printErrorMessage("FoodList data file not found!");
        } catch (IllegalAccessException e) {
            ui.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Main method to run the program.
     */
    public static void main(String[] args) {
        DietBook dietBook = new DietBook();
        dietBook.ui.printWelcomeMessage();
        //dietBook.loadPerson();
        //dietBook.loadFood();

        while (!isExit) {
            try {
                String userInput = dietBook.ui.readCommand();
                Command c = dietBook.manager.manage(userInput);
                c.execute(dietBook.manager, dietBook.ui);
            } catch (DietException e) {
                dietBook.ui.printErrorMessage(e.getMessage());
            }
        }
    }
}
