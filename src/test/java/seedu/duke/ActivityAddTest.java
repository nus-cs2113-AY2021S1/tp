package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddExerciseCommand;
import seedu.duke.command.AddFoodCommand;
import seedu.duke.model.ActivityList;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityAddTest {
    private final LocalDate date = LocalDate.now();

    void createObjects(ActivityList dummyList) {
        dummyList.addActivity(new Food("Apple", 50, date, false));
        dummyList.addActivity(new Food("Banana", 100, date, false));
        dummyList.addActivity(new Food("Apple pie", 55, date, false));
        dummyList.addActivity(new Exercise("Juggle Apples", 100, date, false));
    }

    @Test
    void addActivityFood_success() {
        Food food = new Food("Apple", 50, date, false);
        String output = food.getString();
        assertEquals("[F] | Apple | 50", output);
    }

    @Test
    void addActivityFood2_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        AddFoodCommand food = new AddFoodCommand("Apple", 50, false);

        Assertions.assertThrows(NullPointerException.class, food::execute);
    }

    @Test
    void addActivityExercise_success() {
        Exercise exercise = new Exercise("Running", 450, date, false);
        String output = exercise.getString();
        assertEquals("[E] | Running | 450", output);
    }

    @Test
    void addActivityExercise2_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        AddExerciseCommand exercise = new AddExerciseCommand("Running", 450, false);

        Assertions.assertThrows(NullPointerException.class, exercise::execute);
    }
}
