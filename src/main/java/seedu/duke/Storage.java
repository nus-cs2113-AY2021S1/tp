package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.duke.slot.Timetable;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage<T> {

    private String filePath;
    private Class<T> storageClass;

    /**
     * Constructs a new Storage instance by storing the given pathname of the file.
     *
     * @param filePath The pathname of the file.
     */
    public Storage(String filePath, Class<T> storageClass) {
        this.filePath = filePath.replace('/', File.separatorChar);
        this.storageClass = storageClass;
    }

    /**
     * Returns the tasks found within the file.
     *
     * @return Objects T found in the file.
     * @throws DukeException If file is not found.
     */
    public T load() throws DukeException {
        String fileAsString;
        try {
            fileAsString = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            return createNewInstance();
        }

        if (!fileAsString.equals("null")) {
            Gson gson = new Gson();
            return gson.fromJson(fileAsString, storageClass);
        } else {
            return createNewInstance();
        }
    }

    private T createNewInstance() throws DukeException {
        try {
            return storageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new DukeException(DukeExceptionType.ERROR_LOADING_FILE);
        }
    }

    private ArrayList<String> getData(File f) throws FileNotFoundException {
        ArrayList<String> items = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            items.add(s.nextLine());
        }
        return items;
    }

    /**
     * This method creates the file if it does not exist and saves tasks data in the file.
     *
     * @param data An object to be converted into JSON and saved in the file.
     * @throws DukeException If there is an error writing to the file.
     */
    public void save(Object data) throws DukeException {
        try {
            createDirectory();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(data);

            writeToFile(jsonString);
        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.WRITE_FILE_ERROR);
        }
    }

    private void createDirectory() throws IOException {
        String dirPath = getDirectory(filePath);
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);
    }

    private String getDirectory(String path) {
        String dirPath;
        if (path.contains(File.separator)) {
            dirPath = path.substring(0, path.lastIndexOf(File.separator));
        } else {
            dirPath = path;
        }
        return dirPath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(textToAdd);
        fw.close();
    }


    private ArrayList<String> nusModuleListFromNusMods() throws DukeException {

        try {
            int year = LocalDate.now().getYear();

            URL url = new URL("https://api.nusmods.com/v2/" + year + "-" + (year + 1) +
                    "/moduleList.json"); // create url based on current year

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            String jsonAsString = "";
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) { // if line is empty, means finish reading
                jsonAsString += sc.nextLine();
            }
            //System.out.print(buffer.toString());
            JSONParser parse = new JSONParser();
            JSONArray moduleArray = (JSONArray) parse.parse(jsonAsString);
            ArrayList<String> moduleList = new ArrayList<>();

            for (int i = 0; i < moduleArray.size(); i++) {
                JSONObject module = (JSONObject) moduleArray.get(i);
                moduleList.add(module.get("moduleCode").toString());
            }
            return moduleList;

        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.CONNECTION_ERROR);
        } catch (ParseException e) {
            throw new DukeException(DukeExceptionType.JSON_PARSE_ERROR);
        }
    }

    public ArrayList<String> loadModuleList() throws IOException, DukeException {
        String moduleListPath = "./data/modulelist.txt";
        moduleListPath = moduleListPath.replace('/', File.separatorChar);
        File f = new File(moduleListPath);
        ArrayList<String> moduleList = new ArrayList<>();
        String s = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));

            while ((s = bufferedReader.readLine())!= null) {
                moduleList.add(s);
            }
            if (moduleList.isEmpty()) {
                throw new FileNotFoundException();
            }
            return moduleList;


        } catch (FileNotFoundException e) {

            moduleList = nusModuleListFromNusMods();

            if (moduleList!=null) { // If moduleList is successfully filled, store the list locally
                saveModuleList(moduleListPath, moduleList);
            }
            return moduleList;
        }
    }

    private void saveModuleList(String moduleListPath, ArrayList<String> moduleList)
            throws IOException {
        String dirPath = getDirectory(moduleListPath);
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);

        FileWriter fw = new FileWriter(moduleListPath, false);
        for(String str: moduleList) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }


}
