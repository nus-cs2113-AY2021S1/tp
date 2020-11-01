package seedu.revised.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.quiz.Result;
import seedu.revised.exception.storage.DataLoadingException;
import seedu.revised.card.task.Deadline;
import seedu.revised.card.task.Event;
import seedu.revised.card.task.Task;
import seedu.revised.card.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class StorageTest {
    private List<Subject> subjects;
    private List<Topic> topics;
    private List<Flashcard> flashcards;
    private List<Task> tasks;
    private List<String> tasksStr;
    private List<Result> results;
    private Storage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        // use tempDir as baseDir to avoid file creation for subjects
        storage = new Storage.StorageBuilder()
                .setBaseDir(tempDir.toString())
                .setExportDir(tempDir.toString())
                .setFlashcardFilename("flashcards.json")
                .setTaskFilename("tasks.txt")
                .setResultFilename("results.json")
                .setExportFilename("data.json")
                .build();

        subjects = new ArrayList<>(List.of(
                new Subject("subject1"),
                new Subject("subject2"),
                new Subject("subject3")
        ));
        topics = new ArrayList<>(List.of(
                new Topic("topic1"),
                new Topic("topic2"),
                new Topic("topic3")
        ));
        flashcards = new ArrayList<>(List.of(
                new Flashcard("question1", "answer1"),
                new Flashcard("question2", "answer2"),
                new Flashcard("question3", "answer3")
        ));
        String dateTimeInput = "18:00 10-10-2019";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, format);
        tasks = new ArrayList<>(List.of(
                new Todo("todoTask", false),
                new Event("eventTask", true, dateTime),
                new Deadline("deadlineTask", false, dateTime)
        ));
        results = new ArrayList<>(List.of(
                new Result(100, 100),
                new Result(50, 100),
                new Result(10, 30)
        ));
        tasksStr = new ArrayList<>(List.of(
                "T | 0 | todoTask",
                "E | 1 | eventTask | 6:00 PM 10 Oct 2019",
                "D | 0 | deadlineTask | 6:00 PM 10 Oct 2019"
        ));
    }

    /**
     * Create a valid data directory with populated subjects and topics.
     *
     * @return root directory of the directory created
     */
    private File createValidDataDirectory() throws IOException {
        // create valid directory structure
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Subject subject : subjects) {
            for (Topic topic : topics) {
                Path topicPath = Paths.get(storage.getBaseDir().toString(), subject.getTitle(), topic.getTitle());
                Files.createDirectories(topicPath);
                File flashcardFile = new File(topicPath.toString(), storage.getFlashcardFilename());
                File resultFile = new File(topicPath.toString(), storage.getTopicResultFilename());

                // write flashcards to file
                try (FileWriter fileWriter = new FileWriter(flashcardFile)) {
                    gson.toJson(flashcards, fileWriter);  // store the json to file
                    fileWriter.flush();  // flush to actually write the content
                }

                // write results to file
                try (FileWriter fileWriter = new FileWriter(resultFile)) {
                    gson.toJson(results, fileWriter);  // store the json to file
                    fileWriter.flush();  // flush to actually write the content
                }
            }
        }
        return storage.getBaseDir();
    }

    @Test
    void saveSubjects_subjectsWithoutTopics_DirectoriesWithSubjectTitlesAndTasksFileAndResultFileCreated()
            throws IOException {
        storage.saveSubjects(subjects);
        for (Subject subject : subjects) {
            File subjectDir = Paths.get(storage.getBaseDir().toString(), subject.getTitle()).toFile();
            assertTrue(subjectDir.exists());
            assertTrue(subjectDir.isDirectory());

            File taskFile = new File(subjectDir, storage.getTaskFilename());
            File resultFile = new File(subjectDir, storage.getSubjectResultFilename());
            assertTrue(taskFile.exists());
            assertTrue(resultFile.exists());
        }
    }

    @Test
    void saveSubjects_subjectsWithTopicsAndResults_DirectoriesWithEveryHierarchyCreated() throws IOException {
        // populate subjects
        for (Subject subject : subjects) {
            for (Topic topic : topics) {
                subject.getTopics().add(topic);
            }
            results.forEach(result -> subject.getResults().add(result));
        }

        storage.saveSubjects(subjects);
        for (Subject subject : subjects) {
            File subjectDir = Paths.get(storage.getBaseDir().toString(), subject.getTitle()).toFile();
            assertTrue(subjectDir.exists());
            assertTrue(subjectDir.isDirectory());

            File resultFile = new File(subjectDir, storage.getSubjectResultFilename());
            File taskFile = new File(subjectDir, storage.getTaskFilename());
            assertTrue(taskFile.exists());
            assertTrue(resultFile.exists());
            // check if subdirectories are created
            File[] topicDirs = subjectDir.listFiles(File::isDirectory);
            assertNotNull(topicDirs);
            assertEquals(subject.getTopics().getList().size(), topicDirs.length);
            assertNotEquals("[]", Files.readString(resultFile.toPath()));
        }
    }

    @Test
    void saveTopics_topicsWithoutFlashcardsAndResult_DirectoriesWithTopicTitlesAndEmptyFileCreated()
            throws IOException {
        storage.saveTopics(tempDir, topics);
        for (Topic topic : topics) {
            File topicDir = Paths.get(tempDir.toString(), topic.getTitle()).toFile();
            assertTrue(topicDir.exists());
            assertTrue(topicDir.isDirectory());

            File flashcardFile = new File(topicDir, storage.getFlashcardFilename());
            assertTrue(flashcardFile.exists());
            assertEquals("[]", Files.readString(flashcardFile.toPath()));

            File resultFile = new File(topicDir, storage.getTopicResultFilename());
            assertTrue(resultFile.exists());
            assertEquals("[]", Files.readString(resultFile.toPath()));
        }
    }

    @Test
    void saveTopics_topicsWithFlashcardsAndResults_DirectoriesWithTopicTitlesAndPopulatedFilesCreated()
            throws IOException {
        // populate topics with flashcards and results
        for (Topic topic : topics) {
            for (Flashcard flashcard : flashcards) {
                topic.addFlashcard(flashcard);
            }
            results.forEach(result -> topic.getResults().add(result));
        }

        storage.saveTopics(tempDir, topics);
        for (Topic topic : topics) {
            File topicDir = Paths.get(tempDir.toString(), topic.getTitle()).toFile();
            assertTrue(topicDir.exists());
            assertTrue(topicDir.isDirectory());

            File flashcardFile = new File(topicDir, storage.getFlashcardFilename());
            assertTrue(flashcardFile.exists());
            assertNotEquals("[]", Files.readString(flashcardFile.toPath()));

            File resultFile = new File(topicDir, storage.getTopicResultFilename());
            assertTrue(resultFile.exists());
            assertNotEquals("[]", Files.readString(resultFile.toPath()));
        }
    }

    @Test
    void saveToJson_validFlashcards_readableJsonIsStored() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(tempDir.toString(), storage.getFlashcardFilename());

        Storage.saveToJson(file, flashcards);

        assertTrue(file.exists());
        assertEquals(gson.toJson(flashcards), Files.readString(file.toPath()));
    }

    @Test
    void loadFromJson_populatedFile_listWithCorrectTypeOfObjectsReturned() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File flashcardFile = Paths.get(tempDir.toString(), storage.getFlashcardFilename()).toFile();

        // write flashcards to file
        try (FileWriter fileWriter = new FileWriter(flashcardFile)) {
            gson.toJson(flashcards, fileWriter);  // store the json to file
            fileWriter.flush();  // flush to actually write the content
        }

        Type type = new TypeToken<ArrayList<Flashcard>>() {}.getType();
        List<Flashcard> loadedFlashcards = Storage.loadFromJson(type, flashcardFile);

        assertEquals(flashcards.size(), loadedFlashcards.size());
        for (Flashcard flashcard : loadedFlashcards) {
            assertNotNull(flashcard);
        }
    }

    @Test
    void loadSubjects_noSavedData_emptyListOfSubjectsReturned() throws DataLoadingException {
        assertTrue(storage.loadSubjects().isEmpty());
    }

    @Test
    void loadSubjects_validStructure_populatedSubjects() throws IOException, DataLoadingException {
        createValidDataDirectory();

        List<Subject> loadedSubjects = storage.loadSubjects();
        assertEquals(loadedSubjects.size(), subjects.size());
        for (Subject subject : loadedSubjects) {
            assertEquals(subject.getTopics().getList().size(), topics.size());
            for (Topic topic : subject.getTopics().getList()) {
                assertEquals(topic.getFlashcards().size(), flashcards.size());
                assertEquals(topic.getResults().getList().size(), results.size());
            }
            // subject result is not populated
            assertEquals(subject.getResults().getList().size(), 0);
        }
    }

    @Test
    void saveTasks_tasks_correctFormatInFile() throws IOException {
        Path subjectPath = storage.getBaseDir().toPath();
        storage.saveTasks(subjectPath, tasks);
        File writtenFile = new File(subjectPath.toString(), storage.getTaskFilename());

        try (Scanner scanner = new Scanner(writtenFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assertTrue(tasksStr.stream().anyMatch(s -> s.equals(line)));
            }
        }
    }

    @Test
    void loadTasks_taskFile_correctlyParsedTasks() throws IOException {
        Path subjectPath = storage.getBaseDir().toPath();
        File taskFile = new File(subjectPath.toString(), storage.getTaskFilename());
        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (String taskStr : tasksStr) {
                fileWriter.write(taskStr + "\n");
            }
        }

        List<Task> savedTasks = storage.loadTasks(subjectPath);
        for (int i = 0; i < savedTasks.size(); i++) {
            Task t1 = savedTasks.get(i);
            Task t2 = tasks.get(i);
            assertEquals(t1.getDescription(), t2.getDescription());
            assertEquals(t1.getIsDone(), t2.getIsDone());
            if (t1 instanceof Event) {
                assertEquals(((Event) t1).getDateTimeDescription(), ((Event) t2).getDateTimeDescription());
            } else if (t1 instanceof Deadline) {
                assertEquals(((Deadline) t1).getDateTimeDescription(), ((Deadline) t2).getDateTimeDescription());
            }
        }
    }

    @Test
    void deleteDirectory_directoryWithContent_directoryIsDeleted() throws IOException {
        File dir = createValidDataDirectory();
        assertTrue(dir.exists());
        assertTrue(dir.isDirectory());
        Storage.deleteDirectory(Paths.get(dir.toString()));
        assertFalse(dir.exists());
    }

    @Test
    void export_populatedSubjects_allContentsAreSaved() throws IOException {
        // populated subjects with data
        for (Subject subject : subjects) {
            for (Topic topic : topics) {
                subject.getTopics().add(topic);
                for (Flashcard flashcard : flashcards) {
                    topic.addFlashcard(flashcard);
                }
                for (Result result : results) {
                    topic.getResults().add(result);
                }
            }
            for (Result result : results) {
                subject.getResults().add(result);
            }
            for (Task task : tasks) {
                subject.getTasks().add(task);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(subjects);

        File exportFile = storage.export(subjects);

        assertTrue(exportFile.exists());
        String storedJson = Files.readString(exportFile.toPath());

        assertEquals(jsonString, storedJson);
    }

}