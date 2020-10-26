package command;

import cheatsheet.CheatSheetList;
import exception.CommandException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import parser.Parser;
import ui.Printer;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {
    //    @Test
    //    void addOneCheatSheet_completeInput_success() {
    //        final String userInput = "/add /n name /slanguage /d details";
    //        CheatSheetList.clear();
    //        try {
    //            Parser parser = new Parser(null, new Printer(), null);
    //            Command addCommand = parser.parse(userInput);
    //            addCommand.execute();
    //            assertEquals(1, CheatSheetList.getSize());
    //        } catch (CommandException e) {
    //            fail();
    //        }
    //    }
    //
    //    @Test
    //    void addOneCheatSheet_SpacedInput_success() {
    //        final String userInput = "/add /n Did someone say /sPython v2";
    //        CheatSheetList.clear();
    //        try {
    //            Parser parser = new Parser(null, new Printer(), null);
    //            Command addCommand = parser.parse(userInput);
    //            addCommand.execute();
    //            assertEquals(1, CheatSheetList.getSize());
    //            assertEquals("PythonV2", CheatSheetList.getCheatSheet(1).getSubject());
    //        } catch (CommandException e) {
    //            fail();
    //        }
    //        Command addCommand = new AddCommand(new Printer());
    //    }
    //
    //    @Test
    //    void addOneCheatSheet_allFieldsFilled_success() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /n FirstTest /sJava /d Content1";
    //        Parser parser = new Parser(null, new Printer(), null);
    //        Command addCommand = parser.parse(userInput);
    //        addCommand.execute();
    //        Assertions.assertAll(
    //            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getName()),
    //            () -> assertEquals("Java", CheatSheetList.getCheatSheet(1).getSubject()),
    //            () -> assertEquals("Content1", CheatSheetList.getCheatSheet(1).getDetails())
    //        );
    //    }
    //
    //    @Test
    //    void addOneCheatSheet_nameAndLanguageFilled_success() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /n FirstTest /sJava";
    //        Parser parser = new Parser(null, new Printer(), null);
    //        Command addCommand = parser.parse(userInput);
    //        addCommand.execute();
    //        Assertions.assertAll(
    //            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getName()),
    //            () -> bassertEquals("Java", CheatSheetList.getCheatSheet(1).getSubject()),
    //            () -> assertNull(CheatSheetList.getCheatSheet(1).getDetails())
    //        );
    //    }
    //
    //    @Test
    //    void addOneCheatSheet_nameAndDescriptionFilled_success() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /n FirstTest /d Content1";
    //        Parser parser = new Parser(null, new Printer(), null);
    //        Command addCommand = parser.parse(userInput);
    //        addCommand.execute();
    //        Assertions.assertAll(
    //            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getName()),
    //            () -> assertNull(CheatSheetList.getCheatSheet(1).getSubject()),
    //            () -> assertEquals("Content1", CheatSheetList.getCheatSheet(1).getDetails())
    //        );
    //    }
    //
    //    //    @Test
    //    //    void addOneCheatSheet_languageAndDescriptionFilled_CommandExceptionThrown() throws CommandException {
    //    //        CheatSheetList.clear();
    //    //        String userInput = "/add /sJava /d Content1";
    //    //        Parser parser = new Parser(null, new Printer(), null);
    //    //        Command addCommand = parser.parse(userInput);
    //    //        try {
    //    //            addCommand.execute();
    //    //        } catch (CommandException c) {
    //    //            assertEquals("Please enter a name", c.getMessage());
    //    //        }
    //    //    }
    //
    //    @Test
    //    void addOneCheatSheet_nameFilled_CommandExceptionThrown() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /n FirstTest";
    //        Parser parser = new Parser(null, new Printer(), null);
    //        Command addCommand = parser.parse(userInput);
    //        addCommand.execute();
    //        Assertions.assertAll(
    //            () -> assertEquals("FirstTest", CheatSheetList.getCheatSheet(1).getName()),
    //            () -> assertNull(CheatSheetList.getCheatSheet(1).getSubject()),
    //            () -> assertNull(CheatSheetList.getCheatSheet(1).getDetails())
    //        );
    //    }

    //    @Test
    //    void addOneCheatSheet_LanguageFilled_CommandExceptionThrown() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /sJava";
    //        Parser parser = new Parser(null, new Printer(), new Ui());
    //        Command addCommand = parser.parse(userInput);
    //        try {
    //            addCommand.execute();
    //        } catch (CommandException c) {
    //            assertEquals("Please enter a name", c.getMessage());
    //        }
    //    }

    //    @Test
    //    void addOneCheatSheet_DescriptionFilled_PromptName() throws CommandException {
    //        CheatSheetList.clear();
    //        String userInput = "/add /d Content1";
    //        Parser parser = new Parser(null, new Printer(), new Ui());
    //        Command addCommand = parser.parse(userInput);
    //        try {
    //            addCommand.execute();
    //        } catch (CommandException c) {
    //            assertEquals("Please enter a name", c.getMessage());
    //        }
    //    }

    //    @Test
    //    void execute_noName_exceptionThrown() {
    //        final String userInput = "/add";
    //        CheatSheetList.clear();
    //        try {
    //            Parser parser = new Parser(null, new Printer(), null);
    //            Command addCommand = parser.parse(userInput);
    //            addCommand.execute();
    //            fail();
    //        } catch (CommandException e) {
    //            assertEquals("Please enter a name", e.getMessage());
    //        }
    //    }
}
