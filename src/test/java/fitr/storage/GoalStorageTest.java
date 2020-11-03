package fitr.storage;

import fitr.common.DateManager;
import fitr.exception.InvalidFileFormatException;
import fitr.goal.Goal;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GoalStorageTest {

    @Test
    public void loadGoalList_validGoalData_success() throws Exception {
        GoalStorage goalStorage = new GoalStorage("src/test/data/StorageTest/ValidGoalData.txt");
        ArrayList<Goal> expectedGoalList = getValidGoalList();
        ArrayList<Goal> actualGoalList = goalStorage.loadGoalList();

        assertEquals(expectedGoalList.get(0).getCreatedDate(), actualGoalList.get(0).getCreatedDate());
        assertEquals(expectedGoalList.get(0).getGoalType(), actualGoalList.get(0).getGoalType());
        assertEquals(expectedGoalList.get(0).getDescription(), actualGoalList.get(0).getDescription());

        assertEquals(expectedGoalList.get(1).getCreatedDate(), actualGoalList.get(1).getCreatedDate());
        assertEquals(expectedGoalList.get(1).getGoalType(), actualGoalList.get(1).getGoalType());
        assertEquals(expectedGoalList.get(1).getDescription(), actualGoalList.get(1).getDescription());
    }

    private ArrayList<Goal> getValidGoalList() throws ParseException {
        ArrayList<Goal> goalList = new ArrayList<>();
        goalList.add(new Goal(LocalDate.parse("23/10/2020", DateManager.formatter), "E", "N", "run more"));
        goalList.add(new Goal(LocalDate.parse("25/10/2020", DateManager.formatter), "F", "Y", "eat more"));
        return goalList;
    }

    @Test
    public void loadGoalList_invalidGoalData_exceptionThrown() throws Exception {
        GoalStorage goalStorage = new GoalStorage("src/test/data/StorageTest/InvalidGoalData.txt");
        assertThrows(InvalidFileFormatException.class, goalStorage::loadGoalList);
    }
}