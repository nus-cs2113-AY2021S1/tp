package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * A module checker class to check if the module code entered by the user is valid.
 */
public class ModuleChecker {
    public static final String LINK = "https://api.nusmods.com/v2/2018-2019/moduleList.json";

    private boolean isValid;

    public ModuleChecker() {
        this.isValid = false;
    }

    /**
     * check if a given module code is valid with reference to NUSMod API.
     *
     * @param moduleCode the module code provided by the user.
     * @return isValid   true if it is valid, false otherwise.
     */
    public boolean isModuleValid(String moduleCode) {
        String modList = generateNusModsString(LINK);

        if (moduleCode.length() < 6 || moduleCode.length() > 8) {
            isValid = false;
        } else {
            isValid = modList.contains(moduleCode.toUpperCase());
        }
        return isValid;
    }

    /**
     *  Generate a string which contains all available module codes provided by NUSMod's API.
     *
     * @param onlineLink             the URL link to the JSON file generated from NUSMod's API.
     * @return generatedNusModsList  a string containing all the module information.
     */
    private static String generateNusModsString(String onlineLink) {
        int httpEcho;
        JSONArray modList;
        JSONParser parser = new JSONParser();
        String generatedNusModsList = "";
        try {
            URL url = new URL(onlineLink);// create new url
            URLConnection myConnection = url.openConnection();// try to connect and echo back
            HttpURLConnection httpUrlConnection = (HttpURLConnection) myConnection;
            httpEcho = httpUrlConnection.getResponseCode();
            if (httpEcho != HttpURLConnection.HTTP_OK) {
                System.out.println("Cannot connect website!");
            } else {
                BufferedReader in = new BufferedReader((new InputStreamReader(myConnection.getInputStream())));
                Object obj = parser.parse(in);
                modList = (JSONArray) obj;
                generatedNusModsList = modList.toString();
                return generatedNusModsList;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generatedNusModsList;
    }
}



