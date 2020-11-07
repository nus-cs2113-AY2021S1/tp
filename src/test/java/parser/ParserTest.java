package parser;

import access.Access;
import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.EditCardCommand;
import commands.EditChapterCommand;
import commands.EditCommand;
import commands.EditModuleCommand;
import commands.ExitCommand;
import commands.ListCardsCommand;
import commands.ListChaptersCommand;
import commands.ListCommand;
import commands.ListModulesCommand;
import commands.RescheduleCommand;
import exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class  ParserTest {

    @Test
    public void parse_add() throws Exception {
        Access access = new Access();

        access.setIsAdminLevel();
        assertTrue(Parser.parse(AddCommand.COMMAND_WORD + " CS2113", access) instanceof AddModuleCommand);

        access.setIsModuleLevel();
        assertTrue(Parser.parse(AddCommand.COMMAND_WORD + " Chapter 1", access)
                instanceof AddChapterCommand);

        access.setIsChapterLevel();
        assertTrue(Parser.parse(AddCommand.COMMAND_WORD + " q:1+1 | a:2", access) instanceof AddCardCommand);
    }

    @Test
    public void parse_edit() throws Exception {
        Access access = new Access();

        access.setIsAdminLevel();
        assertTrue(Parser.parse(EditCommand.COMMAND_WORD + " 1 CS2113", access)
                instanceof EditModuleCommand);

        access.setIsModuleLevel();
        assertTrue(Parser.parse(EditCommand.COMMAND_WORD + " 1 Chapter 1", access)
                instanceof EditChapterCommand);

        access.setIsChapterLevel();
        assertTrue(Parser.parse(EditCommand.COMMAND_WORD + " 1 q:1+1 | a:", access)
                instanceof EditCardCommand);
    }

    @Test
    public void parse_list() throws Exception {
        Access access = new Access();

        access.setIsAdminLevel();
        assertTrue(Parser.parse(ListCommand.COMMAND_WORD, access) instanceof ListModulesCommand);

        access.setIsModuleLevel();
        assertTrue(Parser.parse(ListCommand.COMMAND_WORD, access) instanceof ListChaptersCommand);

        access.setIsChapterLevel();
        assertTrue(Parser.parse(ListCommand.COMMAND_WORD, access) instanceof ListCardsCommand);
    }

    @Test
    public void parse_reschedule() throws Exception {
        Access access = new Access();
        String date = LocalDate.now().plusDays(1).toString();

        access.setIsModuleLevel();
        assertTrue(Parser.parse(RescheduleCommand.COMMAND_WORD + " 1 " + date, access)
                instanceof RescheduleCommand);
    }

    @Test
    public void parse_exit() throws Exception {
        Access access = new Access();
        assertTrue(Parser.parse(ExitCommand.COMMAND_WORD, access) instanceof ExitCommand);
    }

    @Test
    public void parse_unknownCommand_throwsInvalidInputException() {
        Access access = new Access();
        assertThrows(InvalidInputException.class, () -> Parser.parse("unknownCommand", access));
    }

    @Test
    public void parse_goCommandEmptyArgs_expectException() {
        Access access = new Access();
        final String[] inputs = {"go", "go "};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_backCommandWithArgs_expectException() {
        Access access = new Access();
        access.setIsModuleLevel();
        final String[] inputs = {"back args"};
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> Parser.parse(input, access));
        }
    }

    @Test
    public void parse_removeCommandEmptyArgs_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        final String[] inputs = {
            "remove",
            "remove ",
        };
        for (String input : inputs) {
            assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
        }
    }

    @Test
    public void parse_removeCommandNonIntegerArgsAdmin_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        String input = "remove two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
    }

    @Test
    public void parse_removeCommandNonIntegerArgsModule_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        access.setModuleLevel("module");
        String input = "remove two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
    }

    @Test
    public void parse_removeCommandNonIntegerArgsChapter_exception() {
        Parser parser = new Parser();
        Access access = new Access();
        access.setChapterLevel("chapter");
        String input = "remove two";
        assertThrows(InvalidInputException.class, () -> parser.parse(input, access));
    }
}
