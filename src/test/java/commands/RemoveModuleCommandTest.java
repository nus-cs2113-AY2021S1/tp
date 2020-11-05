package commands;

import access.Access;
import storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MODULE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveModuleCommandTest {
    @Test
    public void removeModule_invalidIndex_expectException() throws IOException {
        RemoveModuleCommand command = new RemoveModuleCommand(10);
        Access access = new Access();
        Storage storage = new Storage("src/test/data");
        assertEquals(command.removeModule(access, storage), String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE));
    }
}
