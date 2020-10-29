package seedu.dietbook.parser;

import seedu.dietbook.database.DataBase;
import seedu.dietbook.food.Food;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.Manager;
import seedu.dietbook.checker.InputChecker;

import java.time.LocalDateTime;

/**
 * Parser class of the program.
 * The parser class takes in user input and process it into command data that manager can use.
 *
 * @author tikimonarch
 */

public class Parser {
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_CALCULATE = "calculate";
    public static final String COMMAND_EDIT_INFO = "editinfo";
    public static final String COMMAND_INFO = "info";
    public static final String COMMAND_NAME = "name";
    public static final String[] PARAM_INFO = {"g/","a/","h/","l/","o/","t/","c/"};
    public static final String[] PARAM_EDIT_INFO = {"n/","g/","a/","h/","l/","o/","t/","c/"};

    /**
     * Returns the command of a user input.
     *
     * @param userInput which is user input.
     * @return First word which is the command of the user input.
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Returns the index after the command of a user input, e.g. delete 3.
     *
     * @param userInput user input.
     * @return index part of the user input.
     * @throws DietException when the user input is of a wrong format.
     */
    public static int getCommandIndex(String userInput) throws DietException {
        String command = getCommand(userInput);

        InputChecker.checkEmpty(userInput, command);
        try {
            return Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new DietException("OOPS!!! No integer index detected!");
        }
    }

    /**
     * Returns the subsequent parameter after the command from the user input.
     *
     * @param userInput user input.
     * @return parameter part of the user input.
     * @throws DietException when the user input is of a wrong format.
     */
    public static String getCommandParam(String userInput) throws DietException {
        String command = getCommand(userInput);

        InputChecker.checkEmpty(userInput, command);
        switch (command) {
        case COMMAND_NAME:
            return userInput.split("name")[1].trim();
        case COMMAND_CALCULATE:
            InputChecker.checkNutrientType(userInput);
            return userInput.split("calculate")[1].trim();
        case COMMAND_ADD:
            InputChecker.checkAddParam(userInput);
            return userInput.substring(userInput.indexOf(' ') + 1);
        case COMMAND_INFO:
            InputChecker.checkInfoParam(userInput);
            return userInput.substring(userInput.indexOf(' ') + 1);
        case COMMAND_EDIT_INFO:
            return userInput.substring(userInput.indexOf(' ') + 1);
        default:
            return null;
        }

    }

    /**
     * Processes the parameters for <code>add</code> command of user input and adds a <code>Food</code> object.
     *
     * @param userInput user input.
     * @param foodList the FoodList object.
     * @return name of the food that was added.
     * @throws DietException when the user input is of a wrong format.
     */
    public static String getProcessedAdd(String userInput, FoodList foodList) throws DietException {
        int portionSize = 1;
        String foodName = "Food Name";
        int calorie = 0;
        int carb = 0;
        int protein = 0;
        int fat = 0;
        String trimmedParam;
        //String[] databaseCheck = getCommandParam(userInput).trim().split("/");;
        String[] processedParam;
        String[] paramList = {"x/", "n/", "k/", "c/", "p/", "f/"};
        InputChecker.checkRepeatedOption(getCommand(userInput), getCommandParam(userInput));
        for (String param: paramList) {
            if (getCommandParam(userInput).contains(param)) {
                processedParam = getCommandParam(userInput).split(param);
                InputChecker.checkEmptyOption(processedParam);
                trimmedParam = processedParam[1].trim();
                if (processedParam[1].contains("/")) {
                    trimmedParam = processedParam[1].substring(0, processedParam[1].indexOf("/") - 2).trim();
                }
                switch (param) {
                case "x/":
                    portionSize = Integer.parseInt(trimmedParam);
                    InputChecker.checkFoodLimit(portionSize);
                    break;
                case "n/":
                    foodName = trimmedParam;
                    break;
                case "k/":
                    calorie = Integer.parseInt(trimmedParam);
                    InputChecker.checkFoodLimit(calorie);
                    break;
                case "c/":
                    carb = Integer.parseInt(trimmedParam);
                    InputChecker.checkFoodLimit(carb);
                    break;
                case "p/":
                    protein = Integer.parseInt(trimmedParam);
                    InputChecker.checkFoodLimit(protein);
                    break;
                default:
                    fat = Integer.parseInt(trimmedParam);
                    InputChecker.checkFoodLimit(fat);
                    break;
                }
            }
        }
        //if (databaseCheck.length == 3) {
        //    DataBase dataBase = new DataBase();
        //    Food searchedFood = dataBase.searchFoodByName(foodName);
        //    return  foodList.addFood(portionSize, searchedFood);
        //}
        return foodList.addFood(portionSize, foodName, calorie, carb, protein, fat);
    }

    /**
     * Processes the parameters for <code>info</code> command of user input and updates the <code>Person</code> object.
     *
     * @param userInput user input.
     * @param manager the manager object.
     * @throws DietException when the user input is of a wrong format.
     */
    public static void executeProcessedInfo(String userInput, Manager manager) throws DietException {
        Gender gender = Gender.MALE;
        ActivityLevel actLvl = ActivityLevel.NONE;
        int age = 0;
        int height = 0;
        int orgWeight = 0;
        int currWeight = 0;
        int tarWeight = 0;
        String trimmedParam;
        String[] processedParam;
        InputChecker.checkRepeatedOption(getCommand(userInput), getCommandParam(userInput));
        for (String param: PARAM_INFO) {
            processedParam = getCommandParam(userInput).split(param);
            InputChecker.checkEmptyOption(processedParam);
            trimmedParam = processedParam[1].trim();
            if (processedParam[1].contains("/")) {
                trimmedParam = processedParam[1].substring(0, processedParam[1].indexOf("/") - 2).trim();
            }
            switch (param) {
            case "g/":
                String processGender = trimmedParam;
                InputChecker.checkGender(processGender);              
                if (processGender.equals("F")) {
                    gender = Gender.FEMALE;
                } else if (processGender.equals("O")) {
                    gender = Gender.OTHERS;
                }
                break;
            case "a/":
                age = Integer.parseInt(trimmedParam);
                InputChecker.checkAgeLimit(age);
                break;
            case "h/":
                height = Integer.parseInt(trimmedParam);
                InputChecker.checkHeightLimit(height);
                break;
            case "o/":
                orgWeight = Integer.parseInt(trimmedParam);
                InputChecker.checkWeightLimit(orgWeight);
                break;
            case "c/":
                currWeight = Integer.parseInt(trimmedParam);
                InputChecker.checkWeightLimit(currWeight);
                break;
            case "t/":
                tarWeight = Integer.parseInt(trimmedParam);
                InputChecker.checkWeightLimit(tarWeight);
                break;
            default:
                String processActLvl = trimmedParam;
                InputChecker.checkActivity(processActLvl);
                if (processActLvl.equals("1")) {
                    actLvl = ActivityLevel.NONE;
                } else if (processActLvl.equals("2")) {
                    actLvl = ActivityLevel.LOW;
                } else if (processActLvl.equals("3")) {
                    actLvl = ActivityLevel.MEDIUM;
                } else if (processActLvl.equals("4")) {
                    actLvl = ActivityLevel.HIGH;
                } else {
                    actLvl = ActivityLevel.EXTREME;
                }
                break;
            }
        }
        manager.setPerson(manager.getName(), gender, age, height, orgWeight, currWeight, tarWeight, actLvl);
    }

    /**
     * Processes the parameters for <code>editinfo</code> command of user input.
     * The specified parameters are used to update the <code>Person</code> object.
     *
     * @param userInput user input.
     * @param manager the manager object.
     * @throws DietException when the user input is of a wrong format.
     */
    public static void executeEditInfo(String userInput, Manager manager) throws DietException {
        Gender gender;
        ActivityLevel actLvl;
        String name;
        int age;
        int height;
        int orgWeight;
        int currWeight;
        int tarWeight;
        String trimmedParam;
        String[] processedParam;
        InputChecker.checkRepeatedOption(getCommand(userInput), getCommandParam(userInput));
        for (String param : PARAM_EDIT_INFO) {
            if (getCommandParam(userInput).contains(param)) {
                processedParam = getCommandParam(userInput).split(param);
                InputChecker.checkEmptyOption(processedParam);
                trimmedParam = processedParam[1].trim();
                if (processedParam[1].contains("/")) {
                    trimmedParam = processedParam[1].substring(0, processedParam[1].indexOf("/") - 2).trim();
                }
                switch (param) {
                case "g/":
                    String processGender = trimmedParam;
                    InputChecker.checkGender(processGender);
                    if (processGender.equals("F")) {
                        gender = Gender.FEMALE;
                    } else if (processGender.equals("M")) {
                        gender = Gender.MALE;
                    } else {
                        gender = Gender.OTHERS;
                    }
                    manager.getPerson().setGender(gender);
                    break;
                case "n/":
                    name = trimmedParam;
                    manager.getPerson().setName(name);
                    break;
                case "a/":
                    age = Integer.parseInt(trimmedParam);
                    InputChecker.checkAgeLimit(age);
                    manager.getPerson().setAge(age);
                    break;
                case "h/":
                    height = Integer.parseInt(trimmedParam);
                    InputChecker.checkHeightLimit(height);
                    manager.getPerson().setHeight(height);
                    break;
                case "o/":
                    orgWeight = Integer.parseInt(trimmedParam);
                    InputChecker.checkWeightLimit(orgWeight);
                    manager.getPerson().setOriginalWeight(orgWeight);
                    break;
                case "c/":
                    currWeight = Integer.parseInt(trimmedParam);
                    InputChecker.checkWeightLimit(currWeight);
                    manager.getPerson().setCurrentWeight(currWeight);
                    break;
                case "t/":
                    tarWeight = Integer.parseInt(trimmedParam);
                    InputChecker.checkWeightLimit(tarWeight);
                    manager.getPerson().setTargetWeight(tarWeight);
                    break;
                default:
                    String processActLvl = trimmedParam;
                    InputChecker.checkActivity(processActLvl);
                    if (processActLvl.equals("1")) {
                        actLvl = ActivityLevel.NONE;
                    } else if (processActLvl.equals("2")) {
                        actLvl = ActivityLevel.LOW;
                    } else if (processActLvl.equals("3")) {
                        actLvl = ActivityLevel.MEDIUM;
                    } else if (processActLvl.equals("4")) {
                        actLvl = ActivityLevel.HIGH;
                    } else {
                        actLvl = ActivityLevel.EXTREME;
                    }
                    manager.getPerson().setActivityLevel(actLvl);
                    break;
                }
            }
        }
    }
}
