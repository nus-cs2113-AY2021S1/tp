package seedu.dietbook;

import seedu.dietbook.command.AddCommand;
import seedu.dietbook.command.CalculateCommand;
import seedu.dietbook.command.ClearCommand;
import seedu.dietbook.command.Command;
import seedu.dietbook.command.DataCommand;
import seedu.dietbook.command.DeleteCommand;
import seedu.dietbook.command.EditInfoCommand;
import seedu.dietbook.command.ExitCommand;
import seedu.dietbook.command.HelpCommand;
import seedu.dietbook.command.InfoCommand;
import seedu.dietbook.command.ListCommand;
import seedu.dietbook.command.NameCommand;
import seedu.dietbook.command.RecommendCommand;
import seedu.dietbook.command.UserinfoCommand;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.person.Person;
import seedu.dietbook.calculator.Calculator;
import seedu.dietbook.database.DataBase;
import seedu.dietbook.person.Gender;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

/**
 * Manager class of the program.
 * The manager class takes in the checked and processed input and carry out the command specified.
 * Initialization of important classes such as FoodList and Person is done here.
 *
 * @author tikimonarch
 */

public class Manager {
    private Person person;
    private FoodList foodList;
    private String name;
    private int commandCount = 1;
    private DataBase dataBase;
    private Calculator calculator;

    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_CALCULATE = "calculate";
    public static final String COMMAND_CLEAR = "clear";
    public static final String COMMAND_DATA = "data";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_EDIT_INFO = "editinfo";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_INFO = "info";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_NAME = "name";
    public static final String COMMAND_RECOMMEND = "recommend";
    public static final String COMMAND_USERINFO = "userinfo";

    public Manager(FoodList foodlist, DataBase dataBase) {
        this.name = "John Doe";
        this.person = new Person(this.name, Gender.MALE, 1,1,1,1,
                1, ActivityLevel.LOW);
        this.foodList = foodlist;
        this.dataBase = dataBase;
        this.calculator = new Calculator(foodList.getFoods());
    }

    public FoodList getFoodList() {
        return this.foodList;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(String name, Gender gender, int age,int height,int orgWeight, int currWeight,
                          int targWeight, ActivityLevel actLvl) {
        this.person.setAll(name, gender, age, height, orgWeight, currWeight, targWeight, actLvl);
    }

    public Calculator getCalculator() {
        return this.calculator;
    }

    public void setCalculator() {
        this.calculator = new Calculator(foodList.getFoods());
    }

    public DataBase getDataBase() {
        return this.dataBase;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Takes in the user input and returns the command to be carried.
     *
     * @param userInput user input.
     * @return <code>Command</code> for the command specified.
     * @throws DietException when the program does not recognize the command given.
     */
    public Command manage(String userInput) throws DietException {
        Calculator calculator = this.calculator;
        switch (Parser.getCommand(userInput)) {
        case COMMAND_ADD:
            return new AddCommand(Parser.getProcessedAdd(userInput, getFoodList()));
        case COMMAND_CALCULATE:
            return new CalculateCommand(calculator.calculateCalorie(), calculator.calculateCarb(),
                    calculator.calculateProtein(), calculator.calculateFat(), Parser.getCommandParam(userInput));
        case COMMAND_CLEAR:
            return new ClearCommand();
        case COMMAND_DATA:
            return new DataCommand();
        case COMMAND_DELETE:
            return new DeleteCommand(Parser.getCommandIndex(userInput));
        case COMMAND_EDIT_INFO:
            return new EditInfoCommand(userInput);
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_INFO:
            return new InfoCommand(userInput);
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_NAME:
            return new NameCommand(Parser.getCommandParam(userInput));
        case COMMAND_RECOMMEND:
            return new RecommendCommand(getPerson());
        case COMMAND_USERINFO:
            return new UserinfoCommand();
        default:
            throw new DietException("There's no such command!");
        }
    }

}
