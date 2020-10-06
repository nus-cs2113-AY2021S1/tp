package seedu.rex.storage;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.patient.Patient;
import seedu.rex.parser.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public static final String LOAD_ERROR = "Error loading file.";
    private static final String READ_ERROR = "Error reading file.";
    private static final String DIRECTORY_ERROR = "Error creating directory.";
    private static final String WRITE_ERROR = "Error writing file.";

    private final String folder;
    private final String file;

    /**
     * Initializes path of folder and file.
     *
     * @param filePath Full file path.
     */
    public Storage(String filePath) {
        int index = filePath.lastIndexOf("/");
        folder = filePath.substring(0, index);
        file = filePath.substring(index + 1);
    }

    /**
     * Loads patients, stores them into ArrayList and returns the ArrayList.
     *
     * @return ArrayList of patients.
     * @throws RexException If there is problem reading file.
     */
    public ArrayList<Patient> load() throws RexException {
        Path path = Paths.get(folder, file);
        ArrayList<Patient> patients = new ArrayList<>();

        if (Files.exists(path)) {
            try {
                BufferedReader bufferedReader = Files.newBufferedReader(path);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }

                    Patient patient = Parser.readPatient(line);
                    patients.add(patient);
                }
            } catch (IOException e) {
                throw new RexException(READ_ERROR);
            }
        }

        return patients;
    }

    /**
     * Saves patients into file.
     *
     * @param patients ArrayList of patients to save.
     * @throws RexException If there is problem writing or saving file.
     */
    public void save(PatientList patients) throws RexException {
        StringBuilder fileContent = new StringBuilder();

        for (int i = 0; i < patients.getSize(); i++) {
            // Need to format tasks
            fileContent.append(patients.getPatientUsingIndex(i));
        }

        Path folderPath = Paths.get(folder);
        if (!Files.exists(folderPath) && !new File(folder).mkdir()) {
            throw new RexException(DIRECTORY_ERROR);
        }

        Path filePath = Paths.get(folder, file);
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath);
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RexException(WRITE_ERROR);
        }
    }
}
