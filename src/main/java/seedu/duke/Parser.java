package seedu.duke;
import java.util.Arrays;

public class Parser {
    public static final String COMMAND_NAME = "name";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_INFO = "info";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_CLEAR = "clear";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_CALCULATE = "calculate";
    public static final String COMMAND_DATA = "data";
    public static final String COMMAND_USERINFO = "userinfo";
    public static final String[] PARAM_CALCULATE = {"fat", "carbohydrate","protein", "calorie", "all"};
    public static final String[] PARAM_INFO = {"g/","a/","h/","l/","o/","t/"};


    /**
     * Returns the command of a user input.
     * @param userInput which is user input.
     * @return First word which is the command of the user input.
     */
    private static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Returns the subsequent parameter after the command from the user input.
     * @param userInput user input.
     * @return parameter part of the user input.
     * @throws DietException when the user input is of a wrong format.
     */
    private static String getCommandParam(String userInput) throws DietException {
        String command = getCommand(userInput);
        String[] input = {userInput};

        if (userInput.split(command).length < 2
                || userInput.split(command)[1].equals(" ")) {
            throw new DietException("☹ Error! Missing command parameters!");
        } else {
            switch (command) {
            case COMMAND_NAME:
                return userInput.split(" ")[1];
            case COMMAND_CALCULATE:
                for (String param: PARAM_CALCULATE) {
                    if (userInput.contains(param)) {
                        return userInput.split(" ")[1];
                    }
                }
                throw new DietException("☹ Oops! Incorrect nutrient type");
            case COMMAND_ADD:
                if (!userInput.contains("n/") || !userInput.contains("x/") || !userInput.contains("k/")) {
                    throw new DietException("☹ Oh no... Missing or incorrect add statement");
                }
                return userInput.substring(userInput.indexOf(' ') + 1);
            case COMMAND_INFO:
                if (!Arrays.asList(input).containsAll(Arrays.asList(PARAM_INFO))) {
                    throw new DietException("☹ Oh no... Missing or incorrect info statement");
                }
                return userInput.substring(userInput.indexOf(' ') + 1);
            default:
                return null;
            }
        }
    }

    /**
     * Returns the index after the command of a user input, e.g. delete 3.
     * @param userInput user input.
     * @return index part of the user input.
     * @throws DietException when the user input is of a wrong format.
     */
    private static int getCommandIndex(String userInput) throws DietException {
        String command = getCommand(userInput);

        if (userInput.split(command).length < 2 || userInput.split(command)[1].equals(" ")) {
            throw new DietException("☹ OOPS!!! Missing index of duke.task!");
        }
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DietException("☹ OOPS!!! No integer index detected!");
        }
    }

    /**
     * Makes sense of the user input and carries out the functions according to the command given.
     * @param userInput user input.
     * @throws DietException when the program does not recognize the command given.
     */
    public static void parse(String userInput, Manager manager, Ui ui) throws DietException {
        Calculator calculator = manager.getCalculator();
        switch (getCommand(userInput)) {
        case COMMAND_NAME:
            manager.setName(getCommandParam(userInput));
            ui.printAskForUserInfoMessage(manager.getName())
            return;
        case COMMAND_EXIT:
            ui.printExitMessage(manager.getName());
            DietBook.isExit = true;
            return;
        case COMMAND_LIST:
            ui.printFoodList(manager.getFoodList());
            return;
        case COMMAND_USERINFO:
            ui.printPersonInformation(personInformation);
            return;
        case COMMAND_DATA:
            manager.getDataBase().printAllData();
            return;
        case COMMAND_DELETE:
            ui.printDeletedFood(manager.getFoodList().get(getCommandIndex(userInput)));
            manager.getFoodList().delete(getCommandIndex(userInput));
            return;
        case COMMAND_CLEAR:
            ui.printClearFoodListMessage();
            manager.getFoodList().clear;
            return;
        case COMMAND_CALCULATE:
            if (getCommandParam(userInput).equals("all")) {
                ui.printAllNutrientIntake(calculator.calculateCalorie(), calculator.calculateCarb(),
                        calculator.calculateProtein(), calculator.calculateFat());
            } else if (getCommandParam(userInput).equals("calorie")) {
                ui.printCalorieIntake(calculator.calculateCalorie());
            } else if (getCommandParam(userInput).equals("carbohydrate")) {
                ui.printCarbohydrateIntake(calculator.calculateCarb());
            } else if (getCommandParam(userInput).equals("protein")) {
                ui.printProteinIntake(calculator.calculateProtein());
            } else {
                ui.printFatIntake(calculator.calculateFat());
            }
            return;
        case COMMAND_INFO:
            String[] processedParam = getCommandParam(userInput).split(" ");
            String gender = processedParam[0].substring(processedParam[0].indexOf("/") + 1);
            String age = processedParam[1].substring(processedParam[1].indexOf("/") + 1);
            String height = processedParam[2].substring(processedParam[2].indexOf("/") + 1);
            String actLvl = processedParam[3].substring(processedParam[3].indexOf("/") + 1);
            String orgWeight = processedParam[4].substring(processedParam[4].indexOf("/") + 1);
            String tarWeight = processedParam[5].substring(processedParam[5].indexOf("/") + 1);
            manager.setPerson(gender, age, height, actLvl, orgWeight, tarWeight);
            ui.printTutorialMessage();
            return;
        case COMMAND_ADD:
            return;
        default:
            throw new DietException("☹ There's no such command!");
        }
    }
}
