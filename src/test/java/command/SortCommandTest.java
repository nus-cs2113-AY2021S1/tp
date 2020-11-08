package command;

import event.Assignment;
import event.Class;
import eventlist.EventList;
import exception.InvalidSortCriteriaException;
import exception.NoSortCriteriaException;
import exception.NuScheduleException;
import location.Location;
import location.OnlineLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.time.LocalDateTime;
import java.util.Arrays;


class SortCommandTest {

    @Test
    void execute_NoSortCriteria_NoSortCriteriaException() {
        Assertions.assertThrows(NoSortCriteriaException.class, () -> {
            Command d = Parser.parse("sort",  null,0);
        });
    }

    @Test
    void execute_InvalidSortCriteria_InvalidSortCriteriaException() {
        Assertions.assertThrows(InvalidSortCriteriaException.class, () -> {
            Command d = Parser.parse("sort something",  null,0);
        });
    }

    @Test
    void execute_descriptionCriteriaGiven_SortByDescription() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        String[] descriptions = {"eeeee", "ddddd", "ccccc", "bbbbb", "aaaaa"};
        LocalDateTime by = LocalDateTime.parse("2020-02-02T20:00");
        for (int i = 0; i < 5; i++) {
            events.addEvent(new Assignment(descriptions[i], location, by));
        }
        events.sortEvent("description");
        Arrays.sort(descriptions);
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(descriptions[i], events.get(i).getDescription());
        }
    }

    @Test
    void execute_timeCriteriaGiven_SortByEndTime() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        LocalDateTime[] bys = {LocalDateTime.parse("2020-02-02T20:00"), LocalDateTime.parse("2020-02-02T19:00"),
                LocalDateTime.parse("2020-02-02T18:00")};
        for (int i = 0; i < 3; i++) {
            events.addEvent(new Assignment("description", location, bys[i]));
        }
        events.sortEvent("time");
        Arrays.sort(bys);
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(bys[i], events.get(i).getStartDateTime());
        }
    }

    @Test
    void execute_locationCriteriaGiven_SortByLocation() throws NuScheduleException {
        EventList events = new EventList();
        Location[] locations = {new Location("cccc"), new Location("bbbb"), new Location("aaaa")};
        OnlineLocation[] onlineLocation = {new OnlineLocation("cccc.com"), new OnlineLocation("bbbb.com"),
            new OnlineLocation("aaaa.com")};
        LocalDateTime start = LocalDateTime.parse("2020-02-02T20:00");
        LocalDateTime end = LocalDateTime.parse("2020-02-02T21:00");
        int j = 0;
        int k = 0;
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                events.addEvent(new Class("description", locations[j], start, end));
                j++;
            } else {
                events.addEvent(new Class("description", onlineLocation[k], start, end));
                k++;
            }
        }
        events.sortEvent("location");
        // by locations will be sorted on top of online locations
        Assertions.assertEquals("aaaa", events.get(0).getLocation().getName());
        Assertions.assertEquals("bbbb", events.get(1).getLocation().getName());
        Assertions.assertEquals("cccc", events.get(2).getLocation().getName());
        Assertions.assertEquals("aaaa.com", events.get(3).getLink().getLink());
        Assertions.assertEquals("bbbb.com", events.get(4).getLink().getLink());
        Assertions.assertEquals("cccc.com", events.get(5).getLink().getLink());
    }
}