package seedu.revised;

import seedu.revised.card.Subject;
import seedu.revised.list.SubjectList;
import seedu.revised.command.subject.SubjectCommand;
import seedu.revised.exception.storage.DataLoadingException;
import seedu.revised.parser.SubjectParser;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Revised {
    public static final String BASE_DIR = "data";
    public static final String EXPORT_DIR = "export";
    public static final String FLASHCARD_FILENAME = "flashcards.json";
    public static final String TASK_FILENAME = "tasks.txt";
    public static final String RESULT_FILENAME = "results.json";
    public static final String EXPORT_FILENAME = "data.json";

    private final Storage storage;
    private final SubjectList subjects;

    private static final Logger logger = Logger.getLogger(Revised.class.getName());

    static {
        // configuration for logging, should only be done once
        try {
            InputStream stream = Revised.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to read logging configuration file.", e);
        }
    }

    /**
     * Initialises Duke by loading saved data from the disk, if any.
     *
     * @param baseDir           the name of the directory to store the data into
     * @param exportDir         the name of the directory to export the data to
     * @param flashcardFilename the name of the file to store all the flashcard info
     * @param taskFilename      the name of the file to store all the tasks under a subject
     * @param resultFilename    the name of the file to store all the results of quizzes
     * @param exportFilename    the name of the file that the data will be exported to
     */
    public Revised(String baseDir, String exportDir, String flashcardFilename, String taskFilename,
                   String resultFilename, String exportFilename) {
        storage = new Storage.StorageBuilder()
                .setBaseDir(baseDir)
                .setExportDir(exportDir)
                .setFlashcardFilename(flashcardFilename)
                .setTaskFilename(taskFilename)
                .setResultFilename(resultFilename)
                .setExportFilename(exportFilename)
                .build();
        List<Subject> savedSubjects = new ArrayList<>();  // initialize to empty in case data loading fails
        try {
            savedSubjects = storage.loadSubjects();
        } catch (DataLoadingException e) {
            logger.log(Level.WARNING, "Failed to load saved subjects or topics.", e);
            Ui.printError(e);
        }
        subjects = new SubjectList(savedSubjects);
    }

    /**
     * Runs the Duke program until the user exits the program.
     */
    public void run() {
        Ui.printStart(subjects);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                SubjectCommand c = SubjectParser.parse(fullCommand);
                c.execute(subjects, storage);
                isExit = c.isExit();

            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMsg(Ui.INDEX_OUT_OF_BOUND_EXCEPTION);
            } catch (NumberFormatException e) {
                Ui.printErrorMsg(Ui.INDEX_FORMAT_EXCEPTION);
            } catch (Exception e) {
                Ui.printError(e);
            }
        }

        try {
            storage.saveSubjects(subjects.getList());
        } catch (IOException e) {
            Ui.printErrorMsg(Ui.WRITING_EXCEPTION);
            logger.log(Level.SEVERE, Ui.WRITING_EXCEPTION, e);
        }

        Ui.printBye();
    }

    public static void main(String[] args) {
        logger.info("Application starts.");
        try {
            new Revised(BASE_DIR, EXPORT_DIR, FLASHCARD_FILENAME, TASK_FILENAME, RESULT_FILENAME, EXPORT_FILENAME)
                    .run();
        } finally {
            logger.info("Application exits.");
        }
    }
}
