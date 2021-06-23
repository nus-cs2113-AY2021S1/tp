package logic.commands.profile;

import exceptions.SchwarzeneggerException;
import models.Profile;
import org.junit.jupiter.api.Test;
import storage.profile.ProfileStorage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static profile.Constants.EXAMPLE_CALORIES;
import static profile.Constants.EXAMPLE_HEIGHT;
import static profile.Constants.EXAMPLE_NAME;
import static profile.Constants.EXAMPLE_WEIGHT;
import static seedu.duke.Constants.COMMAND_WORD_VIEW;
import static ui.CommonUi.EMPTY_STRING;
import static ui.profile.ProfileUi.MESSAGE_ENOUGH_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_MORE_CALORIES;
import static ui.profile.ProfileUi.MESSAGE_PROFILE_NOT_EXIST;
import static ui.profile.ProfileUi.MESSAGE_SET_EXPECTED_WEIGHT;
import static ui.profile.ProfileUi.MESSAGE_VIEW_PROFILE;

//@@author tienkhoa16
class ProfileViewTest {
    private static final String SAMPLE_DIET_DATA_FOLDER = Paths.get("src", "test", "java", "saves",
            "ProfileCommandsTest", "diet").toString() + "/";
    private static final Path SAMPLE_PROFILE_DATA_FOLDER = Paths.get("src", "test", "java", "saves",
            "ProfileCommandsTest");

    private static final double EXAMPLE_NORMAL_EXPECTED_WEIGHT = 76.7;
    private static final double EXAMPLE_UNDERWEIGHT_EXPECTED_WEIGHT = 40;
    private static final double EXAMPLE_OBESITY_EXPECTED_WEIGHT = 100;

    private static final Profile SAMPLE_NORMAL_EXPECTED_WEIGHT_PROFILE = new Profile(EXAMPLE_NAME,
            EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_NORMAL_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
    private static final Profile SAMPLE_UNDERWEIGHT_EXPECTED_WEIGHT_PROFILE = new Profile(EXAMPLE_NAME,
            EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_UNDERWEIGHT_EXPECTED_WEIGHT, EXAMPLE_CALORIES);
    private static final Profile SAMPLE_OBESITY_EXPECTED_WEIGHT_PROFILE = new Profile(EXAMPLE_NAME,
            EXAMPLE_HEIGHT, EXAMPLE_WEIGHT, EXAMPLE_OBESITY_EXPECTED_WEIGHT, EXAMPLE_CALORIES);


    @Test
    void testExecute_inputNullArguments_ValidStorage_throwsAssertionError() {
        Path dataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, dataFile);
        assertThrows(AssertionError.class, () -> {
            new ProfileView().execute(null, storage);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_NullStorage_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new ProfileView().execute(EMPTY_STRING, (ProfileStorage) null);
        });
    }

    @Test
    void testExecute_noExistingProfile_inputEmptyArguments_ValidStorage_returnFailure() throws SchwarzeneggerException {
        Path dataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, dataFile);
        storage.saveData(null);

        assertEquals(String.format(MESSAGE_PROFILE_NOT_EXIST, COMMAND_WORD_VIEW),
                new ProfileView().execute(EMPTY_STRING, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_notReachedTarget_normalWeight_inputEmptyArguments_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        Path profileDataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, profileDataFile);
        storage.saveData(SAMPLE_NORMAL_EXPECTED_WEIGHT_PROFILE);
        String sampleCaloriesMsg = String.format(MESSAGE_MORE_CALORIES, 2200.0);

        assertEquals(String.format(MESSAGE_VIEW_PROFILE, SAMPLE_NORMAL_EXPECTED_WEIGHT_PROFILE.toString(),
                sampleCaloriesMsg, EMPTY_STRING).trim(),
                new ProfileView(SAMPLE_DIET_DATA_FOLDER, LocalDate.of(2020, 11, 8))
                        .execute(EMPTY_STRING, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_reachedTarget_normalWeight_inputEmptyArguments_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        Path profileDataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, profileDataFile);
        storage.saveData(SAMPLE_NORMAL_EXPECTED_WEIGHT_PROFILE);

        assertEquals(String.format(MESSAGE_VIEW_PROFILE, SAMPLE_NORMAL_EXPECTED_WEIGHT_PROFILE.toString(),
                MESSAGE_ENOUGH_CALORIES, EMPTY_STRING).trim(),
                new ProfileView(SAMPLE_DIET_DATA_FOLDER, LocalDate.of(2020, 11, 7))
                        .execute(EMPTY_STRING, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_reachedTarget_underWeight_inputEmptyArguments_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        Path profileDataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, profileDataFile);
        storage.saveData(SAMPLE_UNDERWEIGHT_EXPECTED_WEIGHT_PROFILE);

        String weightTip = String.format(MESSAGE_SET_EXPECTED_WEIGHT,
                EXAMPLE_NORMAL_EXPECTED_WEIGHT, EXAMPLE_NORMAL_EXPECTED_WEIGHT);
        assertEquals(String.format(MESSAGE_VIEW_PROFILE, SAMPLE_UNDERWEIGHT_EXPECTED_WEIGHT_PROFILE.toString(),
                MESSAGE_ENOUGH_CALORIES, weightTip).trim(),
                new ProfileView(SAMPLE_DIET_DATA_FOLDER, LocalDate.of(2020, 11, 7))
                        .execute(EMPTY_STRING, storage).getFeedbackMessage());
    }

    @Test
    void testExecute_hasExistingProfile_reachedTarget_obesity_inputEmptyArguments_ValidStorage_returnSuccess()
            throws SchwarzeneggerException {
        Path profileDataFile = Paths.get(SAMPLE_PROFILE_DATA_FOLDER.toString(), "profileDataFile.json");
        ProfileStorage storage = new ProfileStorage(SAMPLE_PROFILE_DATA_FOLDER, profileDataFile);
        storage.saveData(SAMPLE_OBESITY_EXPECTED_WEIGHT_PROFILE);

        String weightTip = String.format(MESSAGE_SET_EXPECTED_WEIGHT,
                EXAMPLE_NORMAL_EXPECTED_WEIGHT, EXAMPLE_NORMAL_EXPECTED_WEIGHT);
        assertEquals(String.format(MESSAGE_VIEW_PROFILE, SAMPLE_OBESITY_EXPECTED_WEIGHT_PROFILE.toString(),
                MESSAGE_ENOUGH_CALORIES, weightTip).trim(),
                new ProfileView(SAMPLE_DIET_DATA_FOLDER, LocalDate.of(2020, 11, 7))
                        .execute(EMPTY_STRING, storage).getFeedbackMessage());
    }
}
