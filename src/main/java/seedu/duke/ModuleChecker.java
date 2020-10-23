package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class ModuleChecker {
    private boolean isValid;

    public ModuleChecker() {
        this.isValid = false;
    }

    public boolean isModuleValid(String moduleCode) {
        JSONParser parser = new JSONParser();
        JSONArray modList;
        try {
            URL url = new URL("https://api.nusmods.com/v2/2018-2019/moduleList.json");
            URLConnection myConnection = url.openConnection();
            BufferedReader in = new BufferedReader((new InputStreamReader(myConnection.getInputStream())));
            Object obj = parser.parse(in);
            modList = (JSONArray) obj;

            String convertedModList = modList.toString();
            if (convertedModList.contains(moduleCode.toUpperCase())) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
