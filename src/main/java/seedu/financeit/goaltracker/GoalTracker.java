package seedu.financeit.goaltracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Goal;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

import java.util.Scanner;


public class GoalTracker {
    private static CommandPacket cmdPacket;
    private static Goal goalSetter = new Goal();
    private static Scanner scanner = new Scanner(System.in);
    private static int expenseGoal = 0;
    private static int incomeGoal = 0;
    private static String input;
    private static boolean backToMenu = false;
    private static boolean exitTracker = false;
    private static TotalGoal totalGoal = new TotalGoal();

    public static void main() {
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Goals Tracker");
        System.out.println("[1]. Set goals for Expenses");
        System.out.println("[2]. Set goals for Income");
        UiManager.printWithStatusIcon(Constants.PrintType.INSTRUCTION, "Input either expense/income "
                + "to set goals for it."
        );

        input = UiManager.handleInput();
        cmdPacket = new InputParser().parseInput(input.toLowerCase());
        while (!exitTracker) {
            switch (cmdPacket.getCommandString()) {
            case "expense":
                setExpenseGoal();
                break;
            case "income":
                setIncomeGoal();
                break;
            case "exit":
                exitTracker = true;
                break;
            default:
                System.out.println("Invalid Command");
                input = UiManager.handleInput();
                cmdPacket = new InputParser().parseInput(input.toLowerCase());
                break;
            }
        }
    }

    private static void setExpenseGoal() {
        backToMenu = false;
        while (!backToMenu && exitTracker == false) {
            System.out.println("Please enter your goals for your expenses.");
            System.out.println("However if you wish to go back to Main Menu. Please enter <back>");
            if (scanner.hasNextInt()) {
                expenseGoal = scanner.nextInt();
                System.out.println("You have set $" + expenseGoal + " as your Expense Goals");
                goalSetter.setExpenseGoal(expenseGoal);
                backToMenu = true;
            } else if (scanner.hasNext()) {
                input = scanner.next();
                if (input.equals("back")) {
                    backToMenu = true;
                    main();
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Invalid input. Please enter "
                            + "<back> to go Main Menu or enter a number");
                }
            }
        }

        if (backToMenu == true && exitTracker == false) {
            System.out.println("Do you want to exit Expense Goal Tracker? y/n ");
            input = UiManager.handleInput();
            cmdPacket = new InputParser().parseInput(input.toLowerCase());
            if (cmdPacket.getCommandString().equals("y")) {
                main();
            } else {
                setExpenseGoal();
            }
        }
    }

    private static void setIncomeGoal() {
        backToMenu = false;
        while (!backToMenu && exitTracker == false) {
            System.out.println("Please enter your goals for your income");
            System.out.println("However if you wish to go back to Main Menu. Please enter <back>");
            if (scanner.hasNextInt()) {
                incomeGoal = scanner.nextInt();
                System.out.println("You have set $" + incomeGoal + " as your Income Goals");
                goalSetter.setIncomeGoal(incomeGoal);
                backToMenu = true;
            } else if (scanner.hasNext()) {
                input = scanner.next();
                if (input.equals("back")) {
                    backToMenu = true;
                    main();
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Invalid input. Please enter "
                            + "<back> to go Main Menu or enter a number");
                }
            }
        }

        if (backToMenu == true && exitTracker == false) {
            System.out.println("Do you want to exit Income Goal Tracker? y/n ");
            input = UiManager.handleInput();
            cmdPacket = cmdPacket = new InputParser().parseInput(input.toLowerCase());
            if (cmdPacket.getCommandString().equals("y")) {
                main();
            } else {
                setIncomeGoal();
            }
        }
    }
}
