package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteByIndexCommand;
import seedu.duke.command.FindDescriptionCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvancedListingTest {
    LocalDate date = LocalDate.of(2020, Month.OCTOBER, 9);
    LocalDate date2 = LocalDate.of(2020, Month.NOVEMBER, 10);
    LocalDate date3 = LocalDate.of(2020, Month.DECEMBER, 11);


    void createObjects(DayMap dummyMap) {
        dummyMap.addActivity(date.atStartOfDay(), new Food("rice with eggs", 50, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Exercise("run 2km", 100, date, false));

        dummyMap.addActivity(date2.atStartOfDay(), new Exercise("run 10km", 51,  date2,false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("rice with tofu", 101, date2, false));

        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with vegs", 51, date3, false));
        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with pork", 101, date3, false));
    }


    @Test
    void listDate_andDeleteFromActivityListShown_successfully() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command listCommand = new ListCommand(date);
        listCommand.setData(dummyMap);
        listCommand.execute();
        assertEquals("2020-10-09, [F] | rice with eggs | 50, [E] | run 2km | 100",
                dummyMap.toString(date.atStartOfDay()));

        Command deleteCommand = new DeleteByIndexCommand(0);
        deleteCommand.setData(dummyMap);
        deleteCommand.execute();
        assertEquals("2020-10-09, [E] | run 2km | 100", dummyMap.toString(date.atStartOfDay()));
    }

    @Test
    void findDescription_andDeleteFromActivityListShown_successfully() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindDescriptionCommand("rice");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("[F] | rice with vegs | 51, [F] | rice with pork | 101, [F] | rice with tofu | 101, "
                + "[F] | rice with eggs | 50", dummyMap.getLastSeenList().toString());

        Command deleteCommand = new DeleteByIndexCommand(2);
        deleteCommand.setData(dummyMap);
        deleteCommand.execute();

        assertEquals("[F] | rice with vegs | 51, [F] | rice with pork | 101, [F] | rice with eggs | 50",
                dummyMap.getLastSeenList().toString());
    }

}
