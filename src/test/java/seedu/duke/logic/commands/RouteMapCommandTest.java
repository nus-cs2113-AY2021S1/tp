package seedu.duke.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RouteMapCommandTest {
    private static final String PRINTED_ROUTE_AA1 = "PGP > Kent Ridge MRT Station > Opp University Health Center > " +
            "Yusof Ishak House > Central Library > Kent Ridge > Museum > University Town > University Health Centre > " +
            "Opp Kent Ridge MRT station > PGPR";

    @Test
    void executeCommand_unknownCommand_expectException(){
        RouteMapCommand routeMap = new RouteMapCommand();
        String unknownCommand = "random";
        assertThrows(CustomException.class, () -> routeMap.executeCommand());
    }
    @Test
    void executeCommand() throws CustomException {
        RouteMapCommand routeMap = new RouteMapCommand();
        String correctCommand = "aa1";
        ByteArrayInputStream in = new ByteArrayInputStream(correctCommand.getBytes());
        System.setIn(in);
        routeMap.executeCommand();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertEquals(PRINTED_ROUTE_AA1,outContent.toString());

    }

}