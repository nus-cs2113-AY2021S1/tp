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
    private FoodList foodList;
    private ExerciseList exerciseList;
    private Storage storage;

    public User(FoodList foodList, ExerciseList exerciseList, Storage storage) {
        while (isConfig == false) {
            setup();
            isConfig = true;
        }
        this.foodList = foodList;
        this.exerciseList = exerciseList;
        this.storage = storage;
    }

    private void setup() {
        System.out.println("Hi there, before we begin, let me get to know you :)");
        System.out.println("Please enter your name:");
        setName();
        System.out.println("Please enter your age:");
        setupAge();
        System.out.println("Please enter your height (in m):");
        setupHeight();
        System.out.println("Please enter your weight (in kg):");
        setupWeight();
        System.out.println("Please enter your gender (Enter '1' for Male or '2' for Female):");
        setupGender();
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
                continue;
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
                continue;
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
                continue;
            }
        }
        weight = weightInput;
    }

    public void setupGender() {
        Integer genderInput = 0;
        while (genderInput != 1 && genderInput != 2) {
            try {
                genderInput = Integer.parseInt(in.nextLine());
                System.out.println(genderInput);
                if (genderInput != 1 && genderInput != 2) {
                    System.out.println("oops that is an invalid Gender input.\n"
                            + "Please enter your gender (Enter '1' for Male or '2' for Female):");
                }
            } catch (NumberFormatException e) {
                System.out.println("Oops that is an invalid gender input.\n"
                        + "Please enter your gender (Enter '1' for Male or '2' for Female):");
                genderInput = 0;
                continue;
            }
        }
        if (genderInput == 1) {
            gender = "Male";
        } else if (genderInput == 2) {
            gender = "Female";
        }
    }

    public void viewProfile() {
        System.out.println("Name:");
        System.out.println(getName());
        System.out.println("Age:");
        System.out.println(getAge());
        System.out.println("Gender:");
        System.out.println(getGender());
        System.out.println("Height:");
        System.out.println(getHeight());
        System.out.println("Weight:");
        System.out.println(getWeight());
    }

    public Calorie calculateCalorieBurnt() {
        int index = 0;
        int totalBurnt = 0;
        while (exerciseList.getExercise(index) != null) {
            totalBurnt = exerciseList.getExercise(index).getCalories();
            index++;
        }
        return new Calorie(totalBurnt);
    }

    public Calorie calculateCalorieConsumed() {
        int index = 0;
        int totalConsumed = 0;
        while (foodList.getFood(index) != null) {
            totalConsumed = foodList.getFood(index).getCalories();
        }
        return new Calorie(totalConsumed);
    }

    public Calorie calculateCalorie() {
        int totalCalories;
        Calorie totalConsumed = calculateCalorieConsumed();
        Calorie totalBurnt = calculateCalorieBurnt();
        totalCalories = totalConsumed.get() - totalBurnt.get();
        return new Calorie(totalCalories);
    }
}
