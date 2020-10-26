package parser;

import command.AddCommand;
import command.Command;
import exception.CommandException;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parser_completeInput_success() {
        final String userInput = "/add /n name /s subject /d details";

        ArrayList<CommandFlag> argEnumList = new ArrayList<>();
        argEnumList.add(CommandFlag.NAME);
        argEnumList.add(CommandFlag.SUBJECT);
        argEnumList.add(CommandFlag.DESCRIPTION);

        ArrayList<String> detailsList = new ArrayList<>();
        detailsList.add("name");
        detailsList.add("subject");
        detailsList.add("details");

        HashMap<CommandFlag, String> flagstodescriptions = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            flagstodescriptions.put(argEnumList.get(i - 1), detailsList.get(i - 1));
        }

        try {
            Parser parser = new Parser();
            Command command = parser.parse(userInput);
            assertEquals(AddCommand.class, command.getClass());
            assertEquals(flagstodescriptions, command.getFlagstodescriptionsMap());
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    void parser_incompleteInput_exceptionThrown() {
        String userInput = "dummy command";
        try {
            Parser parser = new Parser();
            parser.parse(userInput);
            fail();
        } catch (CommandException e) {
            assertEquals("Please enter a valid command", e.getMessage());
        }
    }
}
