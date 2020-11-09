package parser;

import command.Command;
import command.AddCommand;
import command.ClearCommand;
import command.DeleteCommand;
import command.EditCommand;
import command.ExitCommand;
import command.FavouriteCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.SettingsCommand;
import command.ViewCommand;

import exception.CommandException;
import org.junit.jupiter.api.Test;
import ui.Printer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    Parser parser;
    UiStub ui;
    SettingsStub settings;

    public ParserTest() {
        Printer printer = new Printer();
        settings = new SettingsStub();
        ui = new UiStub();
        parser = new Parser(null, null, null, printer, ui, settings, null);
    }

    @Test
    void parse_addCommand_pass() {
        final String userInput = "/add /n if /s Java";
        try {
            Command result = parser.parse(userInput);
            assertEquals(true, result instanceof AddCommand);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_addCommandFlagNotRequired_exception() {
        final String userInput = "/add /k name /s subject";
        try {
            parser.parse(userInput);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "Please input the correct flags");
            return;
        }
        fail("Exception not thrown");
    }

    @Test
    void parse_deleteCommandFlagsRepeated_exception() {
        final String userInput = "/delete /n name /n name";
        try {
            parser.parse(userInput);
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "Duplicated flags are not allowed");
            return;
        }
        fail("Exception not thrown");
    }

    @Test
    void parse_deleteCommandWhiteSpaces_pass() {
        final String userInput = "/delete /n     na 1me       ";
        try {
            Command result = parser.parse(userInput);
            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.NAME);
            assertEquals(true, result instanceof DeleteCommand);
            assertEquals("na 1me", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_clearCommandExtraCharacters_pass() {
        final String userInput = "/clear asdasdasd1231 / a";
        try {
            Command result = parser.parse(userInput);
            assertEquals(true, result instanceof ClearCommand);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_editCommandLargeInputIndex_pass() {
        final String userInput = "/edit /i " + String.valueOf(Integer.MAX_VALUE);
        try {
            Command result = parser.parse(userInput);
            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.INDEX);
            assertEquals(true, result instanceof EditCommand);
            assertEquals(String.valueOf(Integer.MAX_VALUE), description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_editCommandSpecialChar_pass() {
        final String userInput = "/edit /n @@ASD''s.&*()(*&^%+P{AS";
        try {
            Command result = parser.parse(userInput);
            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.NAME);
            assertEquals(true, result instanceof EditCommand);
            assertEquals("@@ASD''s.&*()(*&^%+P{AS", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_findCommandCharAfterCommand_pass() {
        final String userInput = "/find  s /s 123 ";
        try {
            Command result = parser.parse(userInput);
            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.SUBJECT);
            assertEquals(true, result instanceof FindCommand);
            assertEquals("123", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_favouriteCommandFillMissingFlag_pass() {
        final String userInput = "/fav";
        try {
            ui.clearUserInput();
            ui.pushUserInput("\r");
            ui.pushUserInput("\r");
            ui.pushUserInput("Noobs");

            Command result = parser.parse(userInput);

            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.NAME);
            assertEquals(true, result instanceof FavouriteCommand);
            assertEquals("Noobs", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_listCommandExtraLetterWithHelp_pass() {
        final String userInput = "/list asd";
        try {
            settings.setDisplayingHelpMessages(true);
            Command result = parser.parse(userInput);

            assertEquals(true, result instanceof ListCommand);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_viewCommandSpacesInMissingFlagInput_pass() {
        final String userInput = "/view";
        try {
            ui.clearUserInput();
            ui.pushUserInput("    Noo  bs    ");
            Command result = parser.parse(userInput);

            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.NAME);
            assertEquals(true, result instanceof ViewCommand);
            assertEquals("Noo  bs", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_settingCommandEmptyFlagDescription_pass() {
        final String userInput = "/set /m";
        try {
            Command result = parser.parse(userInput);

            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.HELPMESSAGE);
            assertEquals(true, result instanceof SettingsCommand);
            assertEquals("", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_settingsCommandSpecialCharsInMissingFlagInput_pass() {
        final String userInput = "/set";
        try {
            ui.clearUserInput();
            ui.pushUserInput("123@1!!\t! ");

            Command result = parser.parse(userInput);

            String description = result.getFlagsToDescriptionsMap().get(CommandFlag.COLORSCHEME);
            assertEquals(true, result instanceof SettingsCommand);
            assertEquals("123@1!!\t!", description);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_helpCommandExtraChars_pass() {
        final String userInput = "/help as";
        try {
            Command result = parser.parse(userInput);
            assertEquals(true, result instanceof HelpCommand);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_exitCommandExtraChars_pass() {
        final String userInput = "/exit as";
        try {
            Command result = parser.parse(userInput);
            assertEquals(true, result instanceof ExitCommand);
        } catch (CommandException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void parse_nonExistentCommandExtraChars_pass() {
        final String userInput = "/exASDASDASasda1it as";
        try {
            Command result = parser.parse(userInput);
            fail("Created an non existent command");
        } catch (CommandException e) {
            assertEquals("Please enter a valid command", e.getMessage());
        }
    }
}
