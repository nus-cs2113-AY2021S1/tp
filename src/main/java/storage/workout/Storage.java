package storage.workout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import workout.workoutsession.exercise.Exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class holds the data loaded during runtime and read and writes to the local storage.
 */
public class Storage {

    private static final String FILEPATH = "./saves/workout";
    //private static ArrayList<Exercise> taskList = new ArrayList<>();
    private static Gson gson;
    private static File file = null;


    /**
     * Initialise the database with locally stored data.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public static void initialise(String filePath) throws IOException {
        /*RuntimeTypeAdapterFactory<Task> taskAdapterFactory = RuntimeTypeAdapterFactory
                .of(Task.class, "type", true)
                .registerSubtype(ToDo.class, "Todo")
                .registerSubtype(Deadline.class, "Deadline")
                .registerSubtype(Event.class, "Event");*/

        gson = new GsonBuilder().setPrettyPrinting()
                .create();

        //creates the file
        String fileName = "saves/workout/" + Integer.toString(1);
        file = new File(fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Write the content in TaskList to a local file.
     * If the local file is not found. It creates the relevant file and folder.
     * @throws IOException If director or file cannot be created.
     */
    public static void writeToStorage(String filePath, ArrayList<Exercise> taskList) throws IOException {
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file.getPath());
        gson.toJson(taskList, writer);
        writer.flush();
        writer.close();
    }

    public static void readFileContents(String filePath, ArrayList<Exercise> taskList) throws FileNotFoundException {
        File file = new File(filePath);

        Type taskListType = new TypeToken<ArrayList<Exercise>>(){}.getType();

        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        taskList.clear();
        taskList.addAll(gson.fromJson(reader, taskListType));
    }


}
