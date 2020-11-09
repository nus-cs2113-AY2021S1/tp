package seedu.revised.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.storageexception.DataLoadingException;
import seedu.revised.card.quizcard.Result;
import seedu.revised.card.taskcard.Deadline;
import seedu.revised.card.taskcard.Event;
import seedu.revised.card.taskcard.Task;
import seedu.revised.card.taskcard.Todo;
import seedu.revised.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.stream.Stream;

public class Storage {
    private final File baseDir;
    private final File exportDir;
    private final String flashcardFilename;
    private final String taskFilename;
    private final String resultFilename;
    private final String exportFilename;
    private final String topicResultFilename;
    private final String subjectResultFilename;

    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    private Storage(StorageBuilder builder) {
        this.baseDir = new File(builder.baseDir);
        this.exportDir = new File(builder.exportDir);
        this.flashcardFilename = builder.flashcardFilename;
        this.taskFilename = builder.taskFilename;
        this.resultFilename = builder.resultFilename;
        this.exportFilename = builder.exportFilename;

        String capResultFilename = resultFilename.substring(0, 1).toUpperCase() + resultFilename.substring(1);
        this.topicResultFilename = "topic" + capResultFilename;  // prepend topic to resultFilename
        this.subjectResultFilename = "subject" + capResultFilename;  // prepend subject to resultFilename
    }

    /**
     * Loads and populates subject data from the storage. Subjects and topics will be sorted by their titles in
     * alphabetical order.
     *
     * @throws DataLoadingException if fails to load the saved data due to filesystem error
     */
    public List<Subject> loadSubjects() throws DataLoadingException {
        logger.info("Loading subject data and its contents from the disk.");
        List<Subject> subjects;
        if (!baseDir.exists()) {  // if the data hasn't been saved before
            logger.info(String.format("%s folder is not found, no saved data is loaded.", baseDir.getAbsolutePath()));
            subjects = new ArrayList<>();
        } else {
            File[] subjectDirs = baseDir.listFiles(File::isDirectory);
            if (subjectDirs == null) {  // error in getting the directories even if they may exist
                throw new DataLoadingException(Ui.DATA_LOADING_EXCEPTION);
            }
            subjects = loadSubjects(subjectDirs);
        }

        logger.info("Finish loading subject data.");
        logger.fine(String.format("Returning subjects: %s.", subjects));
        return subjects;
    }

    /**
     * Creates a list of subjects from the saved directories and populates each subject with existing topics, results,
     * and flashcards. Subjects and topics will be sorted by their titles in alphabetical order.
     *
     * @param subjectDirs directories of subjects saved previously
     * @return a list of populated subjects loaded from the disk
     * @throws DataLoadingException if fails to load the saved data due to filesystem error
     */
    private List<Subject> loadSubjects(File[] subjectDirs) throws DataLoadingException {
        logger.fine(String.format("Loading subject data from the directories: %s.", Arrays.toString(subjectDirs)));
        List<Subject> subjects = new ArrayList<>();
        for (File subjectDir : subjectDirs) {
            File[] topicDirs = subjectDir.listFiles(File::isDirectory);
            if (topicDirs == null) {
                throw new DataLoadingException(Ui.DATA_LOADING_EXCEPTION);
            }

            List<Topic> topics = loadTopics(topicDirs);
            List<Task> tasks;
            try {
                tasks = loadTasks(subjectDir.toPath());
            } catch (Exception e) {
                if (e instanceof FileNotFoundException) {  // task file may have been deleted by the user intentionally
                    logger.info(String.format("Task file is not found under %s, "
                            + "proceeding with an empty task list.", subjectDir.getAbsolutePath()));
                } else {  // task file content may have been corrupted by the user
                    String errMessage = Ui.fileSyntaxErrorMsg("task", subjectDir.getAbsolutePath());
                    logger.log(Level.WARNING, errMessage, e);
                    Ui.printErrorMsg(errMessage);
                }
                tasks = new ArrayList<>();
            }
            File resultFile = new File(subjectDir.toString(), getSubjectResultFilename());
            List<Result> results = loadResults(resultFile);
            Subject subject = new Subject(subjectDir.getName(), topics, tasks, results);
            subjects.add(subject);
        }
        subjects.sort(Comparator.comparing(Subject::getTitle));
        logger.fine(String.format("Finish loading subject data. Subjects: %s.", subjects));
        return subjects;
    }

    /**
     * Creates a list of topics from the saved directories and populates each topic with the existing flashcards
     * and results. Topics will be sorted by their titles in alphabetical order.
     *
     * @param topicDirs directories of topics saved previously
     * @return a list of populated topics loaded from the disk
     */
    private List<Topic> loadTopics(File[] topicDirs) {
        logger.fine(String.format("Loading topic data from the directories: %s.", Arrays.toString(topicDirs)));
        List<Topic> topics = new ArrayList<>();
        for (File topicDir : topicDirs) {
            File flashcardFile = new File(topicDir, getFlashcardFilename());
            File resultFile = new File(topicDir, getTopicResultFilename());
            List<Flashcard> flashcards = loadFlashcards(flashcardFile);
            List<Result> results = loadResults(resultFile);

            Topic topic = new Topic(topicDir.getName(), flashcards, results);
            topics.add(topic);
        }
        topics.sort(Comparator.comparing(Topic::getTitle));
        logger.fine(String.format("Finish loading topic data. Topics: %s.", topics));
        return topics;
    }

    /**
     * Loads the json data in the file into an ArrayList of objects (of type specified).
     *
     * @param type     the type of the object inside the json file
     * @param jsonFile the file that stores the flashcard data
     * @return a list of populated objects with type specified loaded from the file
     * @throws JsonSyntaxException if the file is not in json format or the content is corrupted
     */
    public static <T> List<T> loadFromJson(Type type, File jsonFile) {
        logger.info(String.format("Loading data of type %s from %s.", type, jsonFile.getAbsolutePath()));
        Gson gson = new Gson();
        List<T> objects;

        try (FileReader fileReader = new FileReader(jsonFile)) {
            objects = gson.fromJson(fileReader, type);
        } catch (IOException e) {  // file may have been deleted by the user
            logger.info(String.format("%s is not found, proceeding with returning an empty list.",
                    jsonFile.getAbsolutePath()));
            objects = new ArrayList<>();
        }

        assert objects != null;
        logger.info("Finish loading data.");
        logger.fine(String.format("Data: %s.", objects));
        return objects;
    }

    /**
     * Loads the data in the file into an ArrayList of Flashcard.
     *
     * @param flashcardFile the file that stores the flashcard data
     * @return a list of populated flashcards loaded from the file
     */
    private List<Flashcard> loadFlashcards(File flashcardFile) {
        logger.fine(String.format("Loading flashcards from %s.", flashcardFile));
        Type objectType = new TypeToken<ArrayList<Flashcard>>() {}.getType();
        List<Flashcard> flashcards = new ArrayList<>();
        try {
            flashcards = loadFromJson(objectType, flashcardFile);
        } catch (JsonSyntaxException e) {
            String errMessage = Ui.fileSyntaxErrorMsg("flashcard", flashcardFile.getAbsolutePath());
            logger.log(Level.WARNING, errMessage, e);
            Ui.printErrorMsg(errMessage);
        }
        logger.fine(String.format("Finish loading flashcards: %s.", flashcards));
        return flashcards;
    }

    /**
     * Loads the data in the file into an ArrayList of Result.
     *
     * @param resultFile the file that stores the result data
     * @return a list of populated results loaded from the file
     */
    private List<Result> loadResults(File resultFile) {
        logger.fine(String.format("Loading results from %s.", resultFile));
        Type objectType = new TypeToken<ArrayList<Result>>() {}.getType();
        List<Result> results = new ArrayList<>();
        try {
            results = loadFromJson(objectType, resultFile);
        } catch (JsonSyntaxException e) {
            String errMessage = Ui.fileSyntaxErrorMsg("result", resultFile.getAbsolutePath());
            logger.log(Level.WARNING, errMessage, e);
            Ui.printErrorMsg(errMessage);
        }
        logger.fine(String.format("Finish loading results: %s.", results));
        return results;
    }

    /**
     * Saves the subjects along with all the contents into the storage. Quiz results under the subject will
     * also be saved. Note that all of the storage content will be cleared before storing of data happens so that
     * deletion of subjects and topics will be reflected.
     *
     * @param subjects subjects to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveSubjects(List<Subject> subjects) throws IOException {
        logger.info("Clearing old data.");
        clearData();
        logger.info("Finish clearing old data.");

        logger.info("Saving the subject data to the disk.");
        logger.fine(String.format("Subjects: %s.", subjects));

        assert subjects != null;
        for (Subject subject : subjects) {
            Path subjectPath = Paths.get(getBaseDir().toString(), subject.getTitle());
            Files.createDirectories(subjectPath);

            File resultFile = new File(subjectPath.toString(), getSubjectResultFilename());
            saveToJson(resultFile, subject.getResults().getList());
            saveTasks(subjectPath, subject.getTasks().getList());
            saveTopics(subjectPath, subject.getTopics().getList());
        }
        logger.info("Finish saving the subjects to the disk.");
    }

    /**
     * Saves the topics along with all the contents into the storage. If the topic has no flashcards in it, the file
     * with name {@link Storage#getFlashcardFilename()} with an empty square bracket will be created under it. Similarly
     * , the quiz result will be stored under the path with name {@link Storage#getTopicResultFilename()}.
     *
     * @param subjectPath subject directory where topics will be stored under
     * @param topics      topics to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveTopics(Path subjectPath, List<Topic> topics) throws IOException {
        assert topics != null;
        logger.info(String.format("Saving list of topics to %s.", subjectPath.toAbsolutePath()));
        logger.fine(String.format("Topics: %s.", topics));
        for (Topic topic : topics) {
            Path topicPath = Paths.get(subjectPath.toString(), topic.getTitle());
            Files.createDirectories(topicPath);

            File flashcardFile = new File(topicPath.toString(), getFlashcardFilename());
            File resultFile = new File(topicPath.toString(), getTopicResultFilename());
            saveToJson(flashcardFile, topic.getFlashcards());
            saveToJson(resultFile, topic.getResults().getList());
        }
        logger.info("Finish saving the list of topics.");
    }

    /**
     * Save the contents of the list of objects to the file path provided.
     * This overwrites the content of the file if it already exists.
     *
     * @param jsonFile File where the objects will be stored into as json
     * @param objects  list of objects
     * @throws IOException if fails to save to the storage
     */
    public static <T> void saveToJson(File jsonFile, List<T> objects) throws IOException {
        logger.info(String.format("Saving list of data to %s.", jsonFile.getAbsolutePath()));
        logger.fine(String.format("Objects: %s.", objects));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            gson.toJson(objects, fileWriter);  // store the json to file
            fileWriter.flush();  // flush to actually write the content
        }
        logger.info("Finish saving the list of data.");
    }

    /**
     * Delete the directory and all its content.
     *
     * @param directory the directory to be deleted
     * @throws IOException if any of the file fails to be deleted
     */
    public static void deleteDirectory(Path directory) throws IOException {
        logger.info(String.format("Deleting directory: %s.", directory.toString()));
        try (Stream<Path> fileStream = Files.walk(directory)) {
            fileStream
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        logger.info("Finish deleting the directory.");
    }

    /**
     * Delete baseDir and its contents.
     *
     * @throws IOException if any of the file fails to be deleted
     */
    private void clearData() throws IOException {
        logger.fine(String.format("Clearing the directory %s.", getBaseDir().getAbsolutePath()));
        if (getBaseDir().exists()) {  // only delete if the folder exists to avoid error
            Path dataFolder = Paths.get(getBaseDir().toString());
            deleteDirectory(dataFolder);
        }
        logger.fine("Finish clearing the directory.");
    }

    /**
     * Saves the tasks into the file with name {@link Storage#getTaskFilename()} under the subjectPath.
     * This overwrites the content of the file if it already exists.
     *
     * @param subjectPath subject directory where the tasks will be stored under
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(Path subjectPath, List<Task> tasks) throws IOException {
        File taskFile = new File(subjectPath.toString(), getTaskFilename());
        logger.info(String.format("Saving tasks to %s", taskFile.getAbsolutePath()));
        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (Task task : tasks) {
                String type = null;
                String done = task.getIsDone() ? "1" : "0";
                String datetime = null;

                if (task instanceof Todo) {
                    type = "T";
                } else if (task instanceof Deadline) {
                    type = "D";
                    datetime = ((Deadline) task).getDateTimeDescription();
                } else if (task instanceof Event) {
                    type = "E";
                    datetime = ((Event) task).getDateTimeDescription();
                } else {
                    assert false : "Unknown tasks have not been accounted for.";
                }

                fileWriter.write(type + " | " + done + " | " + task.getDescription());
                if (datetime != null) {
                    fileWriter.write(" | " + datetime);
                }
                fileWriter.write("\n");
            }
        }
        logger.info("Finish saving tasks.");
    }

    /**
     * Reads the task file contents under the specified subject into a list of tasks.
     *
     * @param subjectPath subject directory where the tasks were stored under
     * @return a list of previously saved tasks
     * @throws FileNotFoundException when there are no files found
     */
    public List<Task> loadTasks(Path subjectPath) throws FileNotFoundException {
        File taskFile = new File(subjectPath.toString(), getTaskFilename());
        logger.info(String.format("Loading tasks from %s.", taskFile.getAbsolutePath()));
        List<Task> tasks = new ArrayList<>();

        try (Scanner scan = new Scanner(taskFile)) {
            while (scan.hasNextLine()) {
                String content = scan.nextLine();
                String[] contents = content.split("\\s\\|\\s");
                String type = contents[0].trim();
                boolean done = Integer.parseInt(contents[1].trim()) == 1;
                String description = contents[2].trim();
                String datetimeStr;

                LocalDateTime dateTime = null;
                DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a d MMM yyyy");

                if (type.equals("D") || type.equals("E")) {
                    datetimeStr = contents[3].trim();
                    dateTime = LocalDateTime.parse(datetimeStr, format);
                }

                switch (type) {
                case "T":
                    tasks.add(new Todo(description, done));
                    break;
                case "D":
                    tasks.add(new Deadline(description, done, dateTime));
                    break;
                case "E":
                    tasks.add(new Event(description, done, dateTime));
                    break;
                default:
                    assert false : type;
                }
            }
        }
        logger.info("Finish loading tasks.");
        logger.fine(String.format("Tasks: %s.", tasks));
        return tasks;
    }

    /**
     * Export the subjects with all their contents into one json file. The file location is specified by
     * {@link Storage#getExportDir()}/{@link Storage#getExportFilename()}.
     *
     * @param subjects list of subjects to be saved to the storage
     * @return the file that the data has been exported to
     * @throws IOException if fails to save to the file
     */
    public File export(List<Subject> subjects) throws IOException {
        File file = new File(getExportDir().toString(), getExportFilename());
        logger.info(String.format("Exporting the subject data to %s.", file.getAbsolutePath()));

        Files.createDirectories(getExportDir().toPath());  // create export directory
        saveToJson(file, subjects);
        logger.info("Finish exporting data.");
        return file;
    }

    public File getBaseDir() {
        return baseDir;
    }

    public File getExportDir() {
        return exportDir;
    }

    public String getFlashcardFilename() {
        return flashcardFilename;
    }

    public String getTaskFilename() {
        return taskFilename;
    }

    public String getResultFilename() {
        return resultFilename;
    }

    public String getExportFilename() {
        return exportFilename;
    }

    public String getTopicResultFilename() {
        return topicResultFilename;
    }

    public String getSubjectResultFilename() {
        return subjectResultFilename;
    }

    public static class StorageBuilder {
        private String baseDir;
        private String exportDir;
        private String flashcardFilename;
        private String taskFilename;
        private String resultFilename;
        private String exportFilename;

        /**
         * Set baseDir property of the Storage to be built.
         *
         * @param baseDir the name of the directory to store the data into
         */
        public StorageBuilder setBaseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        /**
         * Set exportDir property of the Storage to be built.
         *
         * @param exportDir the name of the directory to export the data to
         */
        public StorageBuilder setExportDir(String exportDir) {
            this.exportDir = exportDir;
            return this;
        }

        /**
         * Set flashcardFilename property of the Storage to be built.
         *
         * @param flashcardFilename the name of the file to store all the flashcard info
         */
        public StorageBuilder setFlashcardFilename(String flashcardFilename) {
            this.flashcardFilename = flashcardFilename;
            return this;
        }

        /**
         * Set taskFilename property of the Storage to be built.
         *
         * @param taskFilename the name of the file to store all the tasks under a subject
         */
        public StorageBuilder setTaskFilename(String taskFilename) {
            this.taskFilename = taskFilename;
            return this;
        }

        /**
         * Set resultFilename property of the Storage to be built.
         *
         * @param resultFilename the name of the file to store all the results of quizzes
         */
        public StorageBuilder setResultFilename(String resultFilename) {
            this.resultFilename = resultFilename;
            return this;
        }

        /**
         * Set exportFilename property of the Storage to be built.
         *
         * @param exportFilename the name of the file that the data will be exported to
         */
        public StorageBuilder setExportFilename(String exportFilename) {
            this.exportFilename = exportFilename;
            return this;
        }

        /**
         * Build a Storage object with all the properties previously set. All the properties must be set before
         * calling this function or an exception will be thrown.
         *
         * @return a Storage object with all the properties set.
         */
        public Storage build() {
            Storage storage = new Storage(this);
            if (storage.getBaseDir() == null || storage.getExportDir() == null
                    || storage.getFlashcardFilename() == null || storage.getTaskFilename() == null
                    || storage.getResultFilename() == null || storage.getExportFilename() == null) {
                throw new IllegalArgumentException();
            }

            return storage;
        }
    }
}
