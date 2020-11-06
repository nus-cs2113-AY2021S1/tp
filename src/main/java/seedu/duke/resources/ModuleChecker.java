package seedu.duke.resources;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

/**
 * A module checker class to check if the module code entered by the user is valid.
 */
public class ModuleChecker {
    public static final String LINK = "https://api.nusmods.com/v2/2018-2019/moduleList.json";
    private static final String LINE_SEPARATOR = " ";

    private boolean isValid;
    private boolean isOnWifi;

    public ModuleChecker() {
        this.isValid = false;
        this.isOnWifi = isConnectedToWifi();
    }

    /**
     * Check if a given module code is valid with reference to the module list provided by NUSMod API.
     *
     * @param moduleCode the module code provided by the user.
     * @return isValid   true if it is valid, false otherwise.
     */
    public boolean isModuleValid(String moduleCode) {
        HashMap<String, NusModule> modListMap = new HashMap<>();
        modListMap = generateNusModsMap();

        isValid = modListMap.containsKey(moduleCode.toUpperCase());

        return isValid;
    }

    /**
     * Generate the NUS module HashMap using 2 different methods.
     * If there is internet connection, NUS module HashMap is generated from the NUSMod API directly.
     * If there is no internet, NUS module HashMap is generated from the local data file.
     *
     * @return NUS module HashMap.
     */
    private HashMap<String, NusModule> generateNusModsMap() {
        if (isOnWifi) {
            return generateNusModsMapFromOnlineApi();
        } else {
            return generateNusModsMapUsingLocalData();
        }
    }

    /**
     * Checks if the user's computer is connected to wifi and make sure the HTTP request to NUSMod API is successful.
     *
     * @return TRUE if there is internet connect, FALSE otherwise.
     */
    private boolean isConnectedToWifi() {
        int httpEcho;
        try {
            URL url = new URL(LINK);
            URLConnection myConnection = url.openConnection();// try to connect and echo back
            HttpURLConnection httpUrlConnection = (HttpURLConnection) myConnection;
            httpEcho = httpUrlConnection.getResponseCode();
            if (httpEcho != HttpURLConnection.HTTP_OK) {
                System.out.println("Cannot connect website!");
                isOnWifi = false;
            }
        } catch (UnknownHostException e) {
            System.out.println("No internet connection! Verifying module code using local data....\n");
            isOnWifi = false;
        } catch (NoRouteToHostException e){
            System.out.println("No internet connection! Verifying module code using local data....\n");
            isOnWifi = false;
        } catch (IOException e) {
            e.printStackTrace();
            isOnWifi = false;
        }
        return isOnWifi;
    }

    /**
     * Generate a hashmap which maps the module code to a NusModule object/class.
     *
     * @return generatedNusModsList  a hash map containing the module code information only.
     */
    private HashMap<String, NusModule> generateNusModsMapFromOnlineApi() {
        HashMap<String, NusModule> mappedNusModuleList = new HashMap<>();
        int httpEcho;
        String generatedNusModsList;
        try {
            URL url = new URL(LINK);
            URLConnection myConnection = url.openConnection();// try to connect and echo back
            HttpURLConnection httpUrlConnection = (HttpURLConnection) myConnection;
            httpEcho = httpUrlConnection.getResponseCode();
            if (httpEcho != HttpURLConnection.HTTP_OK) {
                System.out.println("Cannot connect website!");
            } else {
                InputStreamReader inputStreamReader = new InputStreamReader(myConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                generatedNusModsList = bufferedReader.readLine();

                while (generatedNusModsList != null) {
                    stringBuffer.append(generatedNusModsList);
                    stringBuffer.append(LINE_SEPARATOR);//must add new line or else it will be read as an entire string
                    generatedNusModsList = bufferedReader.readLine();
                }
                generatedNusModsList = stringBuffer.toString();

                List<NusModule> moduleList = JSON.parseArray(generatedNusModsList, NusModule.class);

                for (NusModule a : moduleList) {
                    mappedNusModuleList.put(a.getModuleCode(), a);
                }
            }
        } catch (IOException e) {
            isOnWifi = false;
        }

        return mappedNusModuleList;
    }

    /**
     * Generate a hashmap which maps the module code to a NusModule object/class.
     *
     * @return generatedNusModsList  a hash map containing the module code information only.
     */
    private HashMap<String, NusModule> generateNusModsMapUsingLocalData() {
        HashMap<String, NusModule> mappedNusModuleListWithLocalData = new HashMap<>();
        String content = "";
        try {
            FileReader fileReader = new FileReader("NUS_Mod_info.json");
            BufferedReader reader = new BufferedReader(fileReader);
            StringBuffer buffer = new StringBuffer();
            String line;
            line = reader.readLine();

            while (line != null) {
                buffer.append(line);
                buffer.append(LINE_SEPARATOR);
                line = reader.readLine();
            }

            content = buffer.toString();
            List<NusModule> modulesList = JSON.parseArray(content, NusModule.class);// extractModules(jsonStr);
            for (NusModule a : modulesList) {
                mappedNusModuleListWithLocalData.put(a.getModuleCode(), a);
            }

        } catch (FileNotFoundException e) {
            System.out.println("The Json data file does not exist! Please make sure you read the User Guide"
                    + " and download\nthe Json data file from our Github page!\n"
                    + "The Json data file and the JAR file must be placed in the same folder!\n"
                    + "Please omit the error message below and carry out the above mentioned steps!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mappedNusModuleListWithLocalData;
    }
}