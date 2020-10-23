package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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

    /**
     * Makes a connection to the NUSMODS API URL and retrieves the JSON
     * file moduleList. Then converts JSON to an Array<String></String>
     * of module codes to be returned.
     *
     * @return Array<String></String> of module codes
     * @throws DukeException CONNECTION_ERROR and JSON_PARSE_ERROR
     */
    // Solution below adapted from AY2021S1-CS2113T-T09-2
    // https://github.com/AY2021S1-CS2113T-T09-2/tp/../data/storage/Decoder.java
    private ArrayList<String> nusModuleListFromNusMods() throws DukeException {
        String weblink = ""; // For exception messages
        try {
            URL url = getNusModsUrl();
            weblink = url.toString();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) { //Unable to connect
                return null;
            }
            String jsonAsString = "";
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) { // if line is empty, means finish reading
                jsonAsString += sc.nextLine();
            }

            return jsonToArrayList(jsonAsString);

        } catch (IOException e) {
            throw new DukeException(DukeExceptionType.CONNECTION_ERROR, weblink);
        } catch (ParseException e) {
            throw new DukeException(DukeExceptionType.JSON_PARSE_ERROR, weblink);
        }
    }

    /**
     * Converts the moduleList JSON to Array<String></String> of module codes.
     *
     * @return Array<String></String> of module codes
     * @throws ParseException When unable to parse JSON files from NUSMODS
     */
    private ArrayList<String> jsonToArrayList(String jsonAsString) throws ParseException {
        JSONParser parse = new JSONParser();
        JSONArray moduleArray = (JSONArray) parse.parse(jsonAsString);
        ArrayList<String> moduleList = new ArrayList<>();

        for (int i = 0; i < moduleArray.size(); i++) {
            JSONObject module = (JSONObject) moduleArray.get(i);
            moduleList.add(module.get("moduleCode").toString());
        }
        return moduleList;
    }

    /**
     * Returns the url of moduleList JSON file from NUSMODS API for the current year.
     *
     * @return URL
     * @throws MalformedURLException If URL is invalid
     */
    private URL getNusModsUrl() throws MalformedURLException {
        int year = LocalDate.now().getYear();

        return new URL("https://api.nusmods.com/v2/" + year + "-" + (year + 1)
                + "/moduleList.json");
    }

    /**
     * Loads modulelist from the txt file if the file exists locally and returns
     * it as an Array<String></String>.
     * Else tries to retrieve online to be returned. If retrieved successfully,
     * save the information locally as txt file.
     *
     * @return Array<String></String> of module codes or null if unable to make
     *     connection and file does not exist locally.
     */
    public ArrayList<String> loadModuleList() throws IOException, DukeException {
        String moduleListPath = "./data/modulelist.txt";
        moduleListPath = moduleListPath.replace('/', File.separatorChar);
        File f = new File(moduleListPath);
        ArrayList<String> moduleList = new ArrayList<>();
        String s = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));

            while ((s = bufferedReader.readLine()) != null) {
                moduleList.add(s);
            }
            if (moduleList.isEmpty()) {
                throw new FileNotFoundException();
            }
            return moduleList;


        } catch (FileNotFoundException e) {

            moduleList = nusModuleListFromNusMods();

            if (moduleList != null) { // If moduleList is successfully filled, store the list locally
                saveModuleList(moduleListPath, moduleList);
            }
            return moduleList;
        }
    }

    /**
     * Saves the moduleList locally.
     * @param  moduleListPath Filepath for local txt file of module codes
     * @param  moduleList The list of module codes
     */
    private void saveModuleList(String moduleListPath, ArrayList<String> moduleList)
            throws IOException {
        String dirPath = getDirectory(moduleListPath);
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);

        FileWriter fw = new FileWriter(moduleListPath, false);
        for (String str: moduleList) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }


}
