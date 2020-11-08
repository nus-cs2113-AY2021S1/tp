package command;

import event.Assignment;
import event.Class;
import event.PersonalEvent;
import event.SelfStudy;
import eventlist.EventList;
import exception.CreatingFileException;
import exception.DataFileNotFoundException;
import exception.EditIndexOutOfBoundsException;
import exception.EmptyEventIndexException;
import exception.ExistingEventInListException;
import exception.NoEditEventDescriptionException;
import exception.UndefinedEventException;
import exception.WrongEditFormatException;
import location.Location;
import location.OnlineLocation;
import locationlist.BusStopList;
import locationlist.LocationList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.NuScheduleException;
import parser.Parser;
import storage.Storage;
import ui.UI;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;


class EditCommandTest {

    @Test
    void execute_NoIndexSpecified_emptyEventIndexException() throws NuScheduleException {

        Assertions.assertThrows(EmptyEventIndexException.class, () -> {
            Command d = Parser.parse("edit", null, 0);
        });

    }

    @Test
    void execute_InputIsNotInteger_WrongEditFormatException() {
        Assertions.assertThrows(WrongEditFormatException.class, () -> {
            Command c = Parser.parse("edit c", null, 0);
        });
    }


    @Test
    void execute_IndexOutOfBounds_UndefinedEventException() throws CreatingFileException, DataFileNotFoundException {
        Storage storage = new Storage("data/events.txt", "data/UserInfo.txt");
        LocationList locations = new LocationList();
        storage.loadLocationData(locations.getLocationList());
        Assertions.assertThrows(UndefinedEventException.class, () -> {
            Parser.parse("edit 1", locations, 0)
                    .execute(new EventList(), new LocationList(), new BusStopList(), new UI(), storage);
        });
    }

    @Test
    void execute_ChangeDescription_DescriptionChanged() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        LocalDateTime by = LocalDateTime.parse("2020-02-02T20:00");
        event.Event existing = new Assignment("existing", location, by);
        events.addEvent(existing);
        String[] editInformation = {"","something","","",""};
        LocalDateTime[] startEnd = new LocalDateTime[2];

        Command c = new EditCommand(0, editInformation, startEnd, null, null);
        Storage storage = new Storage("data/events.txt", "data/UserInfo.txt");
        c.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        Assertions.assertEquals("something", events.get(0).getDescription());
    }

    @Test
    void execute_ChangeType_EventTypeChanged() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        LocalDateTime start = LocalDateTime.parse("2020-02-02T20:00");
        LocalDateTime end = LocalDateTime.parse("2020-02-02T21:00");
        event.Event existing = new Class("existing", location, start, end);
        events.addEvent(existing);
        String[] editInformation = {"personalEvent","","","",""};
        LocalDateTime[] startEnd = new LocalDateTime[2];

        Command c = new EditCommand(0, editInformation, startEnd, null, null);
        Storage storage = new Storage("data/events.txt", "data/UserInfo.txt");
        c.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        Assertions.assertTrue(events.get(0) instanceof PersonalEvent);

        editInformation[0] = "class";
        Command d = new EditCommand(0, editInformation, startEnd, null, null);
        d.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        Assertions.assertTrue(events.get(0) instanceof Class);

        editInformation[0] = "selfStudy";
        Command e = new EditCommand(0, editInformation, startEnd, null, null);
        e.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        Assertions.assertTrue(events.get(0) instanceof SelfStudy);

        editInformation[0] = "assignment";
        Command f = new EditCommand(0, editInformation, startEnd, null, null);
        f.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        Assertions.assertTrue(events.get(0) instanceof Assignment);
    }

    @Test
    void editEvent_changeLocation_LocationChanged() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        LocalDateTime start = LocalDateTime.parse("2020-02-02T20:00");
        LocalDateTime end = LocalDateTime.parse("2020-02-02T21:00");
        event.Event existing = new Class("existing", location, start, end);
        events.addEvent(existing);
        String[] editInformation = {"","","","",""};
        LocalDateTime[] startEnd = new LocalDateTime[2];
        Location location1 = new Location("location1");
        OnlineLocation onlineLocation = new OnlineLocation("zoom.com");
        events.editEvent(0,editInformation,startEnd,location1,null);
        Assertions.assertEquals(events.get(0).getLocation().getName(), "location1");
        events.editEvent(0,editInformation,startEnd,null,onlineLocation);
        Assertions.assertEquals(events.get(0).getLink().getLink(), "zoom.com");
    }

    @Test
    void editEvent_changeTime_StartEndTimeChanged() throws NuScheduleException {
        EventList events = new EventList();
        Location location = new Location("location");
        LocalDateTime start = LocalDateTime.parse("2020-02-02T20:00");
        LocalDateTime end = LocalDateTime.parse("2020-02-02T21:00");
        event.Event existing = new PersonalEvent("existing", location, start, end);
        events.addEvent(existing);

        start = LocalDateTime.parse("2020-02-02T19:00");
        end = LocalDateTime.parse("2020-02-02T22:00");
        LocalDateTime[] startEnd = new LocalDateTime[2];
        startEnd[0] = start;
        startEnd[1] = end;
        String[] editInformation = {"","","","",""};
        events.editEvent(0, editInformation, startEnd,null,null);
        Assertions.assertEquals(events.get(0).getStartDateTime(),start);
        Assertions.assertEquals(events.get(0).getEndDateTime(), end);

        editInformation[4] = "nil";
        Command c = new EditCommand(0, editInformation, startEnd, null, null);
        Storage storage = new Storage("data/events.txt", "data/UserInfo.txt");
        c.execute(events, new LocationList(), new BusStopList(), new UI(), storage);
        // end set to null, getEndDate will get start if end is null
        Assertions.assertEquals(start, events.get(0).getEndDateTime());
    }
}