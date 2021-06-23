package logic.commands.profile;

import exceptions.SchwarzeneggerException;
import exceptions.profile.InvalidCommandFormatException;
import exceptions.profile.SavingException;
import models.Profile;
import org.junit.jupiter.api.Test;
import storage.profile.ProfileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static profile.Constants.CALORIES_UPPER_BOUND;
import static ui.CommonUi.EMPTY_STRING;
import static profile.Constants.EXAMPLE_CALORIES;
import static profile.Constants.EXAMPLE_EXPECTED_WEIGHT;
import static profile.Constants.EXAMPLE_HEIGHT;
import static profile.Constants.EXAMPLE_NAME;
import static profile.Constants.EXAMPLE_WEIGHT;
import static seedu.duke.Constants.COMMAND_WORD_EDIT;
import static ui.profile.ProfileUi.MESSAGE_EDIT_NOTHING;
import static ui.profile.ProfileUi.MESSAGE_EDIT_PROFILE_ACK;
import static ui.profile.ProfileUi.MESSAGE_PROFILE_NOT_EXIST;

//@@author tienkhoa16
class ProfileEditTest {
    private static final Path SAMPLE_DATA_FOLDER = Paths.get("src", "test", "java", "saves", "ProfileCommandsTest");
    private static final Profile SAMPLE_PROFILE = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
            EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);

    @Test
    void testExecute_inputNullArguments_ValidStorage_throwsAssertionError() {
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        assertThrows(AssertionError.class, () -> {
            new ProfileEdit().execute(null, storage);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_NullStorage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new ProfileEdit().execute(EMPTY_STRING, (ProfileStorage) null);
        });
    }

    @Test
    void testExecute_noExistingProfile_inputValidArguments_ValidStorage_returnFailure() throws SchwarzeneggerException {
        String commandArgs = "/n Arnold";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(null);

        assertEquals(String.format(MESSAGE_PROFILE_NOT_EXIST, COMMAND_WORD_EDIT),
                new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEmptyArguments_ValidStorage_throwsInvalidCommandFormatException()
            throws SavingException {
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        assertThrows(InvalidCommandFormatException.class, () -> {
            new ProfileEdit().execute(EMPTY_STRING, storage);
        });
    }

    @Test
    void testExecute_hasExistingProfile_inputEditName_ValidStorage_returnSuccess() throws SchwarzeneggerException {
        String commandArgs = "/n Arnold";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile("Arnold", EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEditHeight_ValidStorage_returnSuccess() throws SchwarzeneggerException {
        String commandArgs = "/h 200";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile(EXAMPLE_NAME, 200, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEditWeight_ValidStorage_returnSuccess() throws SchwarzeneggerException {
        String commandArgs = "/w 200";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, 200,
                EXAMPLE_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEditExpectedWeight_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        String commandArgs = "/e 200";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, 200, EXAMPLE_CALORIES);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEditCaloriesToUpperBound_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        String commandArgs = "/c 200000";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, CALORIES_UPPER_BOUND);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputEditCaloriesMoreThanUpperBound_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        String commandArgs = "/c 200001";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        Profile editedProfile = new Profile(EXAMPLE_NAME, EXAMPLE_HEIGHT, EXAMPLE_WEIGHT,
                EXAMPLE_EXPECTED_WEIGHT, CALORIES_UPPER_BOUND);
        String successMsg = String.format(MESSAGE_EDIT_PROFILE_ACK, editedProfile.toString());
        assertEquals(successMsg, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_inputSameInformation_ValidStorage_returnFailure()
            throws SchwarzeneggerException {
        String commandArgs = "/n Schwarzenegger";
        Path dataFile = Paths.get(SAMPLE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_DATA_FOLDER, dataFile);
        storage.saveData(SAMPLE_PROFILE);

        assertEquals(MESSAGE_EDIT_NOTHING, new ProfileEdit().execute(commandArgs, storage).getFeedbackMessage());
    }
}
