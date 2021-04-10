package seedu.hdbuy.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommandTypeTest {

    @Test void hasType() {
        CommandType type = new CommandType();
        assertNotNull(type);
    }

}