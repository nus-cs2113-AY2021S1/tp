package ui.workout.workoutsession;

import models.Exercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.CommonUi.helpFormatter;

//@@author yujinyang1998
class WorkoutSessionUiTest {

    @Test
    void saveCorruptedError() {
        String filePath = "save/workout/";
        String actual = WorkoutSessionUi.saveCorruptedError(filePath);
        String expected = ":( Save format in save/workout/ is invalid.\n"
                + "\t File is cleared.";
        assertEquals(expected, actual);
    }

    @Test
    void printHelp() {
        String actual = WorkoutSessionUi.printHelp();
        String expected = (helpFormatter("Add", "add [NAME_OF_MOVE] /n [NUMBER_OF_REPETITIONS] /w [WEIGHT]",
                "Add a new move")
                + helpFormatter("List", "list",
                "Show all moves in this current session")
                + helpFormatter("Delete", "delete [INDEX]",
                "Delete a move according to the index in the list")
                + helpFormatter("Search", "search [NAME_OF_MOVE]",
                "Show a list of moves that match the entered keyword")
                + helpFormatter("End", "end",
                "Go back to the Workout Menu")).trim();

        assertEquals(expected, actual);
    }

    @Test
    void addExerciseSuccess() {
        Exercise exercise = new Exercise("benchpress", 435, 432.432);
        String actual = WorkoutSessionUi.addExerciseSuccess(exercise);
        String expected = "Yay! You have added benchpress to your list.\n"
                + "\t [Repetitions: 435 || Weight: 432.432]";
        assertEquals(expected, actual);
    }

    @Test
    void deleteExerciseSuccess() {
        Exercise exercise = new Exercise("benchpress", 435, 432.432);
        String actual = WorkoutSessionUi.deleteExerciseSuccess(exercise);
        String expected = "You have deleted benchpress from your list!\n"
                + "\t [Repetitions: 435 || Weight: 432.432]";
        assertEquals(expected, actual);
    }
}