package seedu.dietbook;

import seedu.dietbook.calculator.Calculator;
import seedu.dietbook.calculator.CalculatorData;
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
import seedu.dietbook.database.DataBase;
import seedu.dietbook.exception.DietException;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.parser.Parser;
import seedu.dietbook.person.FitnessLevel;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;
import seedu.dietbook.saveload.FoodPortionDateSaveLoadManager;
import seedu.dietbook.saveload.PersonSaveLoadManager;
import seedu.dietbook.ui.Ui;

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
    public static int commandCount = 1;
    private DataBase dataBase;
    private CalculatorData data = new CalculatorData();
    private Calculator calculator;
    private PersonSaveLoadManager personSave = new PersonSaveLoadManager();
    private FoodPortionDateSaveLoadManager foodSave = new FoodPortionDateSaveLoadManager();

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
                1, FitnessLevel.LOW);
        this.foodList = foodlist;
        this.dataBase = dataBase;
        this.data.inputData(this.foodList);
        this.calculator = new Calculator(this.data);
    }

    public FoodList getFoodList() {
        return this.foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(String name, Gender gender, int age,int height,int orgWeight, int currWeight,
                          int targWeight, FitnessLevel fitLvl) {
        this.person.setAll(name, gender, age, height, orgWeight, currWeight, targWeight, fitLvl);
    }

    public Calculator getCalculator() {
        return this.calculator;
    }

    public void setCalculator() {
        this.data.inputData(foodList);
        this.calculator.update(this.data);
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
            return new AddCommand(userInput);
        case COMMAND_CALCULATE:
            return new CalculateCommand(calculator.calculateCalorie(), calculator.calculateCarb(),
                    calculator.calculateProtein(), calculator.calculateFat(), Parser.getCommandParam(userInput));
        case COMMAND_CLEAR:
            return new ClearCommand(userInput);
        case COMMAND_DATA:
            return new DataCommand(userInput);
        case COMMAND_DELETE:
            return new DeleteCommand(userInput, Parser.getCommandIndex(userInput));
        case COMMAND_EDIT_INFO:
            return new EditInfoCommand(userInput);
        case COMMAND_EXIT:
            return new ExitCommand(userInput);
        case COMMAND_HELP:
            return new HelpCommand(userInput);
        case COMMAND_INFO:
            return new InfoCommand(userInput);
        case COMMAND_LIST:
            return new ListCommand(userInput);
        case COMMAND_NAME:
            return new NameCommand(Parser.getCommandParam(userInput));
        case COMMAND_RECOMMEND:
            return new RecommendCommand(getPerson(), userInput);
        case COMMAND_USERINFO:
            return new UserinfoCommand(userInput);
        default:
            throw new DietException("There's no such command!");
        }
    }

    public void save() {
        FitnessLevel fitLvl = getPerson().getFitnessLevel();
        int fitLvlInt = 1;
        if (fitLvl.equals(FitnessLevel.NONE)) {
            fitLvlInt = 1;
        } else if (fitLvl.equals(FitnessLevel.LOW)) {
            fitLvlInt = 2;
        } else if (fitLvl.equals(FitnessLevel.MEDIUM)) {
            fitLvlInt = 3;
        } else if (fitLvl.equals(FitnessLevel.HIGH)) {
            fitLvlInt = 4;
        } else if (fitLvl.equals(FitnessLevel.EXTREME)) {
            fitLvlInt = 5;
        }

        Gender gender = getPerson().getGender();
        String genderString;
        if (gender.equals(Gender.MALE)) {
            genderString = "Male";
        } else if (gender.equals(Gender.FEMALE)) {
            genderString = "Female";
        } else {
            genderString = "Others";
        }

        personSave.setActivityLevel(fitLvlInt);
        personSave.setAge(getPerson().getAge());
        personSave.setCurrentWeight(getPerson().getCurrentWeight());
        personSave.setGender(genderString);
        personSave.setHeight(getPerson().getHeight());
        personSave.setName(getPerson().getName());
        personSave.setOriginalWeight(getPerson().getOriginalWeight());
        personSave.setTargetWeight(getPerson().getTargetWeight());
        personSave.save("UserInfo.txt");
        foodSave.saveFoodList(getFoodList(), "FoodList.txt");
    }

}
