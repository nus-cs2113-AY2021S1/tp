package seedu.financeit.datatrackers.goaltracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

public class EditGoalTest {

    public static String[] handleInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseGoalCommand(input.toLowerCase());
    }

    @BeforeEach
    void setup() {
        String[] goalCommand = handleInput("expense 1000 for 08");
        GoalTracker.setExpenseGoal(goalCommand);
    }

    public static String[] handleEditInput(String input) {
        UiManager.printInputPrompt();
        return InputParser.getInstance().parseEditCommand(input.toLowerCase());
    }

    @Test
    void editExpenseGoalSuccessfully() {
        String[] editCommand = handleEditInput("edit expense 2000 for 08");
        GoalTracker.editGoal(editCommand);
    }
}
