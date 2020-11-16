package seedu.financeit.datatrackers.goaltracker;

import org.junit.jupiter.api.Test;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

public class SetGoalTest {
    public static String[] handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseGoalCommand(input.toLowerCase());
    }

    @Test
    void setExpenseGoalSuccessfully() {
        String[] command = handleInput("expense 5000 for 08");
        GoalTracker.setExpenseGoal(command);
    }

    @Test
    void setIncomeGoalSuccessfully() {
        String[] command = handleInput("income 2000 for 07");
        GoalTracker.setIncomeGoal(command);
    }
}
