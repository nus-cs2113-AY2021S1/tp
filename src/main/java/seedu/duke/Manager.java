package seedu.duke;

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
        this.person = new Person();
        this.foodList = foodlist;
        this.dataBase = dataBase;
        this.calculator = new Calculator(0,0,0,0);
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

    public void setPerson(String gender, String age,String height,String actLvl,String orgWeight,String targWeight) {
        this.person = new Person(gender, age, height, actLvl, orgWeight, targWeight);
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
