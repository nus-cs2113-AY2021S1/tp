package seedu.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.FindAllCommand;
import seedu.duke.command.FindCalorieCommand;
import seedu.duke.command.FindDescriptionCommand;
import seedu.duke.command.FindEitherCommand;
import seedu.duke.exception.EmptyKeywordException;
import seedu.duke.exception.FindSlashException;
import seedu.duke.exception.KeywordNotFoundException;
import seedu.duke.model.DayMap;
import seedu.duke.model.Exercise;
import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityFindTest {
    LocalDate date = LocalDate.of(2020, Month.OCTOBER, 9);
    LocalDate date2 = LocalDate.of(2020, Month.NOVEMBER, 10);
    LocalDate date3 = LocalDate.of(2020, Month.DECEMBER, 11);

    void createObjects(DayMap dummyMap) {
        dummyMap.addActivity(date.atStartOfDay(), new Food("fried rice", 60, date, false));
        dummyMap.addActivity(date.atStartOfDay(), new Exercise("Noodles", 100, date, false));

        dummyMap.addActivity(date2.atStartOfDay(), new Exercise("run 10km", 100,  date2,false));
        dummyMap.addActivity(date2.atStartOfDay(), new Food("chicken rice", 200, date2, false));

        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with vegs", 100, date3, false));
        dummyMap.addActivity(date3.atStartOfDay(), new Food("rice with tofu", 300, date3, false));
    }

    @Test
    void findDescription_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindDescriptionCommand("rice");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("[F] | rice with vegs | 100, [F] | rice with tofu | 300, "
                + "[F] | chicken rice | 200, [F] | fried rice | 60", dummyMap.getLastSeenList().toString());

    }

    @Test
    void findCalorie_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindCalorieCommand("100");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("[F] | rice with vegs | 100, [E] | run 10km | 100, "
                + "[E] | Noodles | 100", dummyMap.getLastSeenList().toString());
    }

    @Test
    void findDescriptionAll_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindAllCommand("find a/ rice / with");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("[F] | rice with vegs | 100, [F] | rice with tofu | 300",
                dummyMap.getLastSeenList().toString());
    }

    @Test
    void findDescriptionAll_withNoResult_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindAllCommand("find a/ rice / banana");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("", dummyMap.getLastSeenList().toString());
    }

    @Test
    void findDescriptionEither_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindEitherCommand("find e/ rice / run");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("[F] | rice with vegs | 100, [F] | rice with tofu | 300, "
                        + "[E] | run 10km | 100, [F] | chicken rice | 200, [F] | fried rice | 60",
                dummyMap.getLastSeenList().toString());
    }

    @Test
    void findDescriptionEither_withNoResult_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Command findCommand = new FindEitherCommand("find e/ papaya / western");
        findCommand.setData(dummyMap);
        findCommand.execute();
        assertEquals("", dummyMap.getLastSeenList().toString());
    }

    @Test
    void findDescriptionAll_consecutiveSlashException_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Assertions.assertThrows(FindSlashException.class, () -> {
            dummyMap.listActivitiesContainingAll("find e/ papaya // western");
        });
    }

    @Test
    void findDescription_emptyKeywordException_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Assertions.assertThrows(EmptyKeywordException.class, () -> {
            dummyMap.listActivitiesContainingDescription("");
        });
    }

    @Test
    void findCalorie_keywordNotFoundException_success() {
        DayMap dummyMap = new DayMap();
        createObjects(dummyMap);

        Assertions.assertThrows(KeywordNotFoundException.class, () -> {
            dummyMap.listActivitiesContainingCalorie("1000");
        });
    }
}
