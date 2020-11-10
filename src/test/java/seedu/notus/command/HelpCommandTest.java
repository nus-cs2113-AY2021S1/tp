package seedu.notus.command;

//@@author R-Ramana

import com.diogonunes.jcolor.Attribute;
import seedu.notus.ui.Formatter;

import org.junit.jupiter.api.Test;

import static com.diogonunes.jcolor.Ansi.colorize;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.notus.util.CommandMessage.ADD_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.ADD_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.ARCHIVE_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.CREATE_T_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.DELETE_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.DELETE_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.DELETE_T_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.EDIT_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.EDIT_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.EXIT_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.FIND_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.LIST_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.LIST_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.LIST_T_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.PIN_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.REMIND_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.TAG_E_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.TAG_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.UNARCHIVE_N_COMMAND_USAGE;
import static seedu.notus.util.CommandMessage.VIEW_N_COMMAND_USAGE;

class HelpCommandTest {
    static final Attribute CYAN = Attribute.BRIGHT_CYAN_TEXT();
    static final Attribute WHITE = Attribute.BRIGHT_WHITE_TEXT();

    private static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ], represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {HELP_STRING,
            colorize(ADD_E_COMMAND_USAGE, WHITE),
            colorize(ADD_N_COMMAND_USAGE, CYAN),
            colorize(ARCHIVE_N_COMMAND_USAGE, WHITE),
            colorize(CREATE_T_COMMAND_USAGE, CYAN),
            colorize(DELETE_E_COMMAND_USAGE, WHITE),
            colorize(DELETE_N_COMMAND_USAGE, CYAN),
            colorize(DELETE_T_COMMAND_USAGE, WHITE),
            colorize(EDIT_E_COMMAND_USAGE, CYAN),
            colorize(EDIT_N_COMMAND_USAGE, WHITE),
            colorize(EXIT_COMMAND_USAGE, CYAN),
            colorize(FIND_N_COMMAND_USAGE, WHITE),
            colorize(LIST_E_COMMAND_USAGE, CYAN),
            colorize(LIST_N_COMMAND_USAGE, WHITE),
            colorize(LIST_T_COMMAND_USAGE, CYAN),
            colorize(PIN_N_COMMAND_USAGE, WHITE),
            colorize(REMIND_E_COMMAND_USAGE, CYAN),
            colorize(TAG_E_COMMAND_USAGE, WHITE),
            colorize(TAG_N_COMMAND_USAGE, CYAN),
            colorize(UNARCHIVE_N_COMMAND_USAGE, WHITE),
            colorize(VIEW_N_COMMAND_USAGE, CYAN)
    };

    @Test
    void executeTest() {
        String helpCommand = new HelpCommand().execute();

        assertEquals(Formatter.formatString(COMMANDS_USAGE, true, false), helpCommand);
    }
}
