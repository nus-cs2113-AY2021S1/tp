package seedu.duke.logic.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;
import seedu.duke.model.bus.Bus;
import seedu.duke.model.bus.BusData;
import seedu.duke.model.bus.BusStops;
import seedu.duke.model.favorite.FavList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {

    private static ArrayList<Bus> busesInZoneA = new ArrayList<>();
    private static final BusStops[] AA1 = {
        BusStops.PGP,
        BusStops.KENTRIDGEMRTSTATION,
        BusStops.OPPUNIVERSITYHEALTHCENTRE,
        BusStops.YUSOFISHAKHOUSE,
        BusStops.CENTRALLIBRARY,
        BusStops.KENTRIDGE,
        BusStops.MUSEUM,
        BusStops.UNIVERSITYTOWN,
        BusStops.UNIVERSITYHEALTHCENTRE,
        BusStops.OPPKENTRIDGEMRTSTATION,
        BusStops.PGPR
    };

    @BeforeAll
    public static void makeList() {
        busesInZoneA.add(new Bus("AA1", AA1));
        BusData.createBusList(busesInZoneA);
    }

    @Test
    void extractType_unknownCommand_expectException() {
        String command = "Hello";
        Parser p = new Parser(command);
        assertThrows(CustomException.class, p::extractType);
    }

    @Test
    void extractType_exitCommand_returnsFalse() throws CustomException {
        String command = "/exit";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertFalse(isOngoing);
    }

    @Test
    void extractType_listStopsCommand_returnsTrue() throws CustomException {
        String command = "/liststops";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_helpCommand_returnsTrue() throws CustomException {
        String command = "/help";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_allBusCommand_returnsTrue() throws CustomException {
        String command = "/allbus";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_routeCommand_returnsTrue() throws CustomException {
        String command = "/route PGP /to PGPR";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_busCommand_returnsTrue() throws CustomException {
        String command = "/bus PGP";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_routeMapCommand_returnsTrue() throws CustomException {
        String command = "/routemap AA1";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_addFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
    }

    @Test
    void extractType_dineCommand_returnsTrue() throws CustomException {
        String command = "/dine science";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_listFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
        p.extractType();
        p.setUserInput("/listfav");
    }

    @Test
    void extractType_dineInfoCommand_returnsTrue() throws CustomException {
        String command = "/dineinfo arise";
        Parser p = new Parser(command);

        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_deleteFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
        p.extractType();
        p.setUserInput("/deletefav 1");
    }

    @Test
    void extractType_facultyCommand_returnsTrue() throws CustomException {
        String command = "/faculty";
        Parser p = new Parser(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_execFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String command = "/execfav 1";
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
        p.extractType();
        p.setUserInput(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_descFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String command = "/descfav 1 /to help function";
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
        p.extractType();
        p.setUserInput(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }

    @Test
    void extractType_clearFavCommand_returnsFalse() throws CustomException {
        FavList list = new FavList();
        String command = "/clearfav";
        String fav = "/help";
        Parser p = new Parser(fav);
        p.extractType();
        p.setUserInput("/addfav");
        p.extractType();
        p.setUserInput(command);
        boolean isOngoing = p.extractType();
        assertTrue(isOngoing);
    }
}
