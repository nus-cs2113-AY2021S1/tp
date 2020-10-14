package seedu.duke;

import seedu.duke.list.FoodList;
import seedu.duke.person.ActivityLevel;
import seedu.duke.person.Person;
import seedu.calculator.Calculator;
import seedu.duke.database.DataBase;
import seedu.duke.person.Gender;


import java.util.Scanner;


public class Manager {
    private Person person;
    private FoodList foodList;
    private String name;
    private DataBase dataBase;
    private Calculator calculator;
    private static Scanner s = new Scanner(System.in);

    public Manager(FoodList foodlist, DataBase dataBase) {
        this.name = "John Doe";
        this.person = new Person(this.name, Gender.MALE, 0,0,0,0, ActivityLevel.LOW);
        this.foodList = foodlist;
        this.dataBase = dataBase;
        this.calculator = new Calculator(foodList.getFoods());
    }

    public String readCommand() {
        return s.nextLine();
    }

    public FoodList getFoodList() {
        return this.foodList;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(String name, Gender gender, int age,int height,int orgWeight,
                          int targWeight, ActivityLevel actLvl) {
        this.person = new Person(name, gender, age, height, orgWeight, targWeight, actLvl);
    }

    public Calculator getCalculator() {
        return this.calculator;
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

}
