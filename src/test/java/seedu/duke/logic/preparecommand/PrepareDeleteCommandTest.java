package seedu.duke.logic.preparecommand;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteByIndexCommand;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PrepareDeleteCommandTest {
    private static final String DELETE = "Delete";
    LocalDate date = LocalDate.of(2020, Month.OCTOBER, 25);

    void createObjects(DayMap dummyMap) {
        dummyMap.addActivity(date.atStartOfDay(), new Food("rice with eggs", 50, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Exercise("run 2km", 100, date, false));
    }

    @Test
    public void prepareDeleteCommand_DeleteByIndex_Success() {
        DayMap dayMap = new DayMap();
        createObjects(dayMap);
        String[] arguments = {DELETE,"2"};
        Command command = new PrepareDeleteCommand(arguments).prepareCommand();
        assertTrue(command instanceof DeleteByIndexCommand);
        command.setData(dayMap);
        command.execute();
    }

}