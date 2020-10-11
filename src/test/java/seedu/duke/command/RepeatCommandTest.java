package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data");


}