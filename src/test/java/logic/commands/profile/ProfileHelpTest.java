package logic.commands.profile;

import exceptions.SchwarzeneggerException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import storage.profile.ProfileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static profile.Constants.ADD_PROFILE_FORMAT;
import static profile.Constants.EDIT_PROFILE_FORMAT;
import static seedu.duke.Constants.COMMAND_WORD_ADD;
import static seedu.duke.Constants.COMMAND_WORD_DELETE;
import static seedu.duke.Constants.COMMAND_WORD_EDIT;
import static seedu.duke.Constants.COMMAND_WORD_END;
import static seedu.duke.Constants.COMMAND_WORD_VIEW;
import static ui.CommonUi.EMPTY_STRING;
import static ui.CommonUi.helpFormatter;

//@@author tienkhoa16
class ProfileHelpTest {
    private static final Path SAMPLE_DATA_FOLDER = Paths.get("src", "test", "java", "saves", "ProfileCommandsTest");

    @Test
    void testExecute_inputNullArguments_ValidStorage_throwsAssertionError() {
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        assertThrows(AssertionError.class, () -> {
            new ProfileHelp().execute(null, storage);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_NullStorage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new ProfileHelp().execute(EMPTY_STRING, (ProfileStorage) null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_ValidStorage_returnHelpMessage() throws SchwarzeneggerException {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_ADD), ADD_PROFILE_FORMAT,
                "Add your new profile"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_VIEW), COMMAND_WORD_VIEW,
                "View your profile"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_EDIT), EDIT_PROFILE_FORMAT,
                "Edit your existing profile. You may edit from 1 field to all fields"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_DELETE), COMMAND_WORD_DELETE,
                "Delete your existing profile"));
        helpMessage.append(helpFormatter(StringUtils.capitalize(COMMAND_WORD_END), COMMAND_WORD_END,
                "Go back to Main Menu"));

        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        assertEquals(helpMessage.toString().trim(),
                new ProfileHelp().execute(EMPTY_STRING, storage).getFeedbackMessage());
    }
}
