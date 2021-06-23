package logic.commands.profile;

import exceptions.InvalidCommandWordException;
import org.junit.jupiter.api.Test;
import storage.profile.ProfileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class ProfileWrongTest {
    private static final Path SAMPLE_PROFILE_DATA_FOLDER = Paths.get("src", "test", "java", "saves",
            "ProfileCommandsTest");

    @Test
    void testExecute_inputNullArguments_ValidStorage_throwsAssertionError() {
        Path dataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, dataFile);
        assertThrows(AssertionError.class, () -> {
            new ProfileWrong().execute(null, storage);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_NullStorage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new ProfileWrong().execute(EMPTY_STRING, (ProfileStorage) null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_ValidStorage_throwsInvalidCommandWordException() {
        Path dataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, dataFile);
        assertThrows(InvalidCommandWordException.class, () -> {
            new ProfileWrong().execute(EMPTY_STRING, storage);
        });
    }
}
