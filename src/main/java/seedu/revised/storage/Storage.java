package seedu.revised.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.quiz.Result;
import seedu.revised.exception.DataLoadingException;
import seedu.revised.task.Deadline;
import seedu.revised.task.Event;
import seedu.revised.task.Task;
import seedu.revised.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File baseDir;
    private final String flashcardFilename;
    private final String taskFilename;
    private final String resultFilename;

    /**
     * Creates a new Storage instance. The same filename string will be used to create files to store the flashcard data
     * under each topic directory.
     *
     * @param baseDir           the name of the directory to store the data into
     * @param flashcardFilename the name of the file to store all the flashcard info
     * @param taskFilename      the name of the file to store all the tasks under a subject
     * @param resultFilename    the name of the file to store all the results of quizzes
     */
    public Storage(String baseDir, String flashcardFilename, String taskFilename, String resultFilename) {
        this.baseDir = new File(baseDir);
        this.flashcardFilename = flashcardFilename;
        this.taskFilename = taskFilename;
        this.resultFilename = resultFilename;
    }

    /**
     * Loads and populates subject data from the storage. Subjects and topics will be sorted by their titles in
     * alphabetical order.
     *
     * @throws DataLoadingException     if fails to load the saved data due to filesystem error
     */
    public List<Subject> loadSubjects() throws DataLoadingException {
        if (!baseDir.exists()) {  // if the data hasn't been saved before
            return new ArrayList<>();
        }

        File[] subjectDirs = baseDir.listFiles(File::isDirectory);
        if (subjectDirs == null) {  // error in getting the directories even if they may exist
            throw new DataLoadingException("Error loading saved data from the disk.");
        }

        return loadSubjects(subjectDirs);
    }

    /**
     * Creates a list of subjects from the saved directories and populates each subject with existing topics, results,
     * and flashcards. Subjects and topics will be sorted by their titles in alphabetical order.
     *
     * @param subjectDirs directories of subjects saved previously
     * @return a list of populated subjects loaded from the disk
     * @throws DataLoadingException     if fails to load the saved data due to filesystem error
     */
    private List<Subject> loadSubjects(File[] subjectDirs) throws DataLoadingException {
        List<Subject> subjects = new ArrayList<>();
        for (File subjectDir : subjectDirs) {
            File[] topicDirs = subjectDir.listFiles(File::isDirectory);
            if (topicDirs == null) {
                throw new DataLoadingException("Error loading saved data from the disk.");
            }

            List<Topic> topics = loadTopics(topicDirs);
            List<Task> tasks;
            try {
                tasks = loadTasks(subjectDir.toPath());
            } catch (FileNotFoundException e) {
                tasks = new ArrayList<>();  // task file may have been deleted by the user
            }
            File resultFile = new File(subjectDir.toString(), getResultFilename());
            List<Result> results = loadResults(resultFile);
            Subject subject = new Subject(subjectDir.getName(), topics, tasks, results);
            subjects.add(subject);
        }
        subjects.sort(Comparator.comparing(Subject::getTitle));
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
        List<Topic> topics = new ArrayList<>();
        for (File topicDir : topicDirs) {
            File flashcardFile = new File(topicDir, getFlashcardFilename());
            File resultFile = new File(topicDir, getResultFilename());
            List<Flashcard> flashcards = loadFlashcards(flashcardFile);
            List<Result> results = loadResults(resultFile);

            Topic topic = new Topic(topicDir.getName(), flashcards, results);
            topics.add(topic);
        }
        topics.sort(Comparator.comparing(Topic::getTitle));
        return topics;
    }

    /**
     * Loads the json data in the file into an ArrayList of objects (of type specified).
     *
     * @param type the type of the object inside the json file
     * @param jsonFile the file that stores the flashcard data
     * @return a list of populated objects with type specified loaded from the file
     */
    public static <T> List<T> loadFromJson(Type type, File jsonFile)  {
        Gson gson = new Gson();
        List<T> objects;

        try (FileReader fileReader = new FileReader(jsonFile)) {
            objects = gson.fromJson(fileReader, type);
        } catch (IOException e) {  // file may have been deleted by the user
            objects = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Error reading the json data at " + jsonFile.getAbsolutePath()
                    + ". Make sure the syntax is correct if you changed it manually.", e);
        }

        assert objects != null;
        return objects;
    }

    /**
     * Loads the data in the file into an ArrayList of Flashcard.
     *
     * @param flashcardFile the file that stores the flashcard data
     * @return a list of populated flashcards loaded from the file
     *
     */
    private List<Flashcard> loadFlashcards(File flashcardFile)  {
        Type objectType = new TypeToken<ArrayList<Flashcard>>() {}.getType();
        return loadFromJson(objectType, flashcardFile);
    }

    /**
     * Loads the data in the file into an ArrayList of Result.
     *
     * @param resultFile the file that stores the result data
     * @return a list of populated results loaded from the file
     *
     */
    private List<Result> loadResults(File resultFile)  {
        Type objectType = new TypeToken<ArrayList<Result>>() {}.getType();
        return loadFromJson(objectType, resultFile);
    }

    /**
     * Saves the subjects along with all the contents into the storage. Quiz results under the subject will
     * also be saved.
     *
     * @param subjects subjects to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveSubjects(List<Subject> subjects) throws IOException {
        assert subjects != null;
        for (Subject subject : subjects) {
            Path subjectPath = Paths.get(getBaseDir().toString(), subject.getTitle());
            Files.createDirectories(subjectPath);

            File resultFile = new File(subjectPath.toString(), getResultFilename());
            saveToJson(resultFile, subject.getResults().getList());
            saveTasks(subjectPath, subject.getTasks().getList());
            saveTopics(subjectPath, subject.getTopics().getList());
        }
    }

    /**
     * Saves the topics along with all the contents into the storage. If the topic has no flashcards in it, the file
     * with name {@link Storage#getFlashcardFilename()} with an empty square bracket will be created under it. Similarly
     * , the quiz result will be stored under the path with name {@link Storage#getResultFilename()}.
     *
     * @param subjectPath subject directory where topics will be stored under
     * @param topics      topics to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveTopics(Path subjectPath, List<Topic> topics) throws IOException {
        assert topics != null;
        for (Topic topic : topics) {
            Path topicPath = Paths.get(subjectPath.toString(), topic.getTitle());
            Files.createDirectories(topicPath);

            File flashcardFile = new File(topicPath.toString(), getFlashcardFilename());
            File resultFile = new File(topicPath.toString(), getResultFilename());
            saveToJson(flashcardFile, topic.getFlashcards());
            saveToJson(resultFile, topic.getResults().getList());
        }
    }

    /**
     * Save the contents of the list of objects to the file path provided.
     * This overwrites the content of the file if it already exists.
     *
     * @param jsonFile  File where the objects will be stored into as json
     * @param objects list of objects
     * @throws IOException if fails to save to the storage
     */
    public static <T> void saveToJson(File jsonFile, List<T> objects) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            gson.toJson(objects, fileWriter);  // store the json to file
            fileWriter.flush();  // flush to actually write the content
        }
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
        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    fileWriter.write("T | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    fileWriter.write("D | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + " | " + ((Deadline) task).getBy() + "\n");
                } else if (task instanceof Event) {
                    fileWriter.write("E | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + " | " + ((Event) task).getAt() + "\n");
                }
            }
        }
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
        List<Task> tasks = new ArrayList<>();

        try (Scanner scan = new Scanner(taskFile)) {
            while (scan.hasNextLine()) {
                String content = scan.nextLine();
                String[] contents = content.split("\\s\\|\\s");
                String legend = contents[0].trim();
                boolean done = Integer.parseInt(contents[1].trim()) == 1;
                String action = contents[2].trim();
                String action2 = "";
                if (legend.equals("D") || legend.equals("E")) {
                    action2 = contents[3].trim();
                }
                switch (legend) {
                case "T":
                    tasks.add(new Todo(action, done));
                    break;
                case "D":
                    tasks.add(new Deadline(action, done, action2));
                    break;
                case "E":
                    tasks.add(new Event(action, done, action2));
                    break;
                default:
                    assert false : legend;
                }
            }
        }
        return tasks;
    }

    public File getBaseDir() {
        return baseDir;
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
}
