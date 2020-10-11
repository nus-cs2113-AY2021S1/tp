package seedu.duke;

import java.util.Scanner;

/**
 * User class keeps track of user's personal information.
 */
public class User {
    public static String name;
    public static Integer age;
    public static Double height;
    public static Double weight;
    public static String gender;
    public Scanner in = new Scanner(System.in);
    private static boolean isConfig = false;

    public void setup() {
        System.out.println("Hi there, before we begin, let me get to know you :)");
        System.out.println("Please enter your name:");
        setName();
        System.out.println("Please enter your age:");
        setupAge();
        System.out.println("Please enter your height (in m):");
        setupHeight();
        System.out.println("Please enter your weight (in kg):");
        setupWeight();
        System.out.println("Please enter your gender (Enter 'm' for Male or 'f' for Female):");
        setupGender();
        System.out.println("Setup complete!");
        isConfig = true;
    }

    public static String getName() {
        return name;
    }

    public static Integer getAge() {
        return age;
    }

    public static Double getHeight() {
        return height;
    }

    public static Double getWeight() {
        return weight;
    }

    public static String getGender() {
        return gender;
    }

    public void setName() {
        name = in.nextLine();
    }

    public void setupAge() {
        Integer ageInput = 0;
        while (ageInput <= 0) {
            try {
                ageInput = Integer.parseInt(in.nextLine());
                if (ageInput <= 0) {
                    System.out.println("Oops that is an invalid age input.\n"
                            + "Please enter your age:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Oops that is an invalid age input.\n"
                        + "Please enter your age:");
                ageInput = 0;
            }
        }
        age = ageInput;
    }

    public void setupHeight() {
        Double heightInput = 0.00;
        // Height (in m)
        while (heightInput <= 0.00) {
            try {
                heightInput = Double.parseDouble(in.nextLine());
                if (heightInput <= 0.00) {
                    System.out.println("Oops that is an invalid height input.\n"
                            + "Please enter your height (in m):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Oops that is an invalid height input.\n"
                        + "Please enter your height (in m):");
                heightInput = 0.00;
            }
        }
        height = heightInput;
    }

    public void setupWeight() {
        // Weight (in kg)
        Double weightInput = 0.00;
        while (weightInput <= 0.00) {
            try {
                weightInput = Double.parseDouble(in.nextLine());
                if (weightInput <= 0.00) {
                    System.out.println("Oops that is an invalid weight input\n"
                            + "Please enter your weight (in kg):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Oops that is an invalid weight input.\n"
                        + "Please enter your weight (in kg):");
                weightInput = 0.00;
            }
        }
        weight = weightInput;
    }

    public void setupGender() {
        String genderInput = in.nextLine();
        while (!genderInput.equalsIgnoreCase("m") && !genderInput.equalsIgnoreCase("f")) {
            System.out.println("oops that is an invalid Gender input.\n"
                    + "Please enter your gender (Enter 'm' for Male or 'f' for Female):");
            genderInput = in.nextLine();
        }
        if (genderInput.equalsIgnoreCase("m")) {
            gender = "Male";
        } else if (genderInput.equalsIgnoreCase("f")) {
            gender = "Female";
        }
    }

    @Override
    public String toString() {
        return "Name:\n" + getName() + "\n" + "Age:\n" + getAge() + "\n" + "Gender:\n" + getGender()
                + "\n" + "Height:\n" + getHeight() + "\n" + "Weight:\n" + getWeight() + "\n";
    }

    public Calorie calculateCalorieBurnt(ExerciseList exerciseList) {
        int index = 0;
        int totalBurnt = 0;
        while (index < exerciseList.getSize()) {
            totalBurnt += exerciseList.getExercise(index).getCalories();
            index++;
        }
        return new Calorie(totalBurnt);
    }

    public Calorie calculateCalorieConsumed(FoodList foodList) {
        int index = 0;
        int totalConsumed = 0;
        while (index < foodList.getSize()) {
            totalConsumed += foodList.getFood(index).getCalories();
            index++;
        }
        return new Calorie(totalConsumed);
    }

    public Calorie calculateCalorie(FoodList foodList, ExerciseList exerciseList) {
        int totalCalories;
        Calorie totalConsumed = calculateCalorieConsumed(foodList);
        Calorie totalBurnt = calculateCalorieBurnt(exerciseList);
        totalCalories = totalConsumed.get() - totalBurnt.get();
        return new Calorie(totalCalories);
    }

    public void loadUserData(String name, Integer age, Double height, Double weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    public double getBMI() {
        return weight / ((height) * (height));
    }

}
