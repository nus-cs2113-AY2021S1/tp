package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.command.EditExerciseCommand;
import seedu.duke.command.EditFoodCommand;
import seedu.duke.model.Activity;
import seedu.duke.model.ActivityList;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityEditTest {

    LocalDate date = LocalDate.of(2020, Month.OCTOBER, 25);
    LocalDate date2 = LocalDate.of(2020, Month.NOVEMBER, 10);
    LocalDate date3 = LocalDate.of(2020, Month.DECEMBER, 11);


    void createObjects(ActivityList dummyList) {
        dummyList.addActivity(new Food("Apple", 50, date, false));
        dummyList.addActivity(new Food("Banana", 100, date, false));
        dummyList.addActivity(new Food("Apple pie", 55, date, false));
        dummyList.addActivity(new Exercise("Juggle Apples", 100, date, false));
    }


    void createObjects2(DayMap dummyMap) {
        dummyMap.addActivity(date.atStartOfDay(), new Food("rice with eggs", 50, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Exercise("run 2km", 100, date, false));

        dummyMap.addActivity(date2.atStartOfDay(), new Exercise("run 10km", 51, date2, false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("rice with tofu", 101, date2, false));

        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with shit", 51, date3, false));
        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with pork", 101, date3, false));
    }

    @Test
    void editActivityFoodToExercise_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        Activity activity = new Exercise("jumping", 900, date, false);
        dummyList.insertActivity(0, activity);

        assertEquals("[E] | jumping | 900, [F] | Banana | 100, [F] | Apple pie | 55, "
                + "[E] | Juggle Apples | 100", dummyList.toString());
    }

    @Test
    void editActivityFoodToExercise2_success() throws NullPointerException {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        DayMap dummyMap = new DayMap();
        createObjects2(dummyMap);


        Assertions.assertThrows(NullPointerException.class, () -> {
            EditExerciseCommand exercise = new EditExerciseCommand(0, "jumping", 900);

            exercise.execute();
        });
    }

    @Test
    void editActivityExerciseToFood_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        Activity activity = new Food("Pineapple", 77, date,false);
        dummyList.insertActivity(3, activity);

        assertEquals("[F] | Apple | 50, [F] | Banana | 100, [F] | Apple pie | 55, "
                + "[F] | Pineapple | 77", dummyList.toString());
    }

    @Test
    void editActivityExerciseToFood2_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        DayMap dummyMap = new DayMap();
        createObjects2(dummyMap);


        Assertions.assertThrows(NullPointerException.class, () -> {
            EditFoodCommand food = new EditFoodCommand(3, "Pineapple", 77);

            food.execute();
        });
    }

    @Test
    void editActivityExerciseToExercise_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        Activity activity = new Exercise("jumping", 900, date,false);
        dummyList.insertActivity(3, activity);

        assertEquals("[F] | Apple | 50, [F] | Banana | 100, [F] | Apple pie | 55, "
                + "[E] | jumping | 900", dummyList.toString());
    }

    @Test
    void editActivityExerciseToExercise2_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        DayMap dummyMap = new DayMap();
        createObjects2(dummyMap);


        Assertions.assertThrows(NullPointerException.class, () -> {
            EditExerciseCommand exercise = new EditExerciseCommand(3, "jumping", 900);

            exercise.execute();
        });
    }

    @Test
    void editActivityFoodToFood_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        Activity activity = new Food("Pineapple", 77,  date,false);
        dummyList.insertActivity(2, activity);

        assertEquals("[F] | Apple | 50, [F] | Banana | 100, [F] | Pineapple | 77, "
                + "[E] | Juggle Apples | 100", dummyList.toString());
    }

    @Test
    void editActivityFoodToFood2_success() {
        ActivityList dummyList = new ActivityList();
        createObjects(dummyList);

        DayMap dummyMap = new DayMap();
        createObjects2(dummyMap);


        Assertions.assertThrows(NullPointerException.class, () -> {
            EditFoodCommand food = new EditFoodCommand(2, "Pineapple", 77);
            food.execute();
        });
    }

    /*
    @Test
    void editActivity_success() {
        String userInput0 = "list 2020-10-25";
        Parser parser = new Parser(userInput0);
        parser.parseCommand();

        String userInput1 = "edita a f/ Pineapple c/ 77";
        Parser parser1 = new Parser(userInput1);
        Command command = parser1.parseCommand();

        Assertions.assertThrows(NumberFormatException.class, () -> {
            command.execute();
        });
    }*/
}
