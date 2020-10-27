package seedu.duke.resources;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

/**
 * A module checker class to check if the module code entered by the user is valid.
 */
public class ModuleChecker {
    public static final String LINK = "https://api.nusmods.com/v2/2018-2019/moduleList.json";
    private static final String LINE_SEPARATOR = " ";

    private boolean isValid;

    public ModuleChecker() {
        this.isValid = false;
    }

    /**
     * Check if a given module code is valid with reference to NUSMod API.
     *
     * @param moduleCode the module code provided by the user.
     * @return isValid   true if it is valid, false otherwise.
     */
    public boolean isModuleValid(String moduleCode) {
        HashMap<String, NusModule> modListMap = new HashMap<>();
        modListMap = generateNusModsMap(LINK);

        isValid = modListMap.containsKey(moduleCode.toUpperCase());

        return isValid;
    }

    /**
     *  Generate a hashmap which maps the module code to a NusModule object/class.
     *
     * @param onlineLink             the URL link to the JSON file generated from NUSMod's API.
     * @return generatedNusModsList  a hash map containing the module code information only.
     */
    private static HashMap<String, NusModule> generateNusModsMap(String onlineLink) {
        HashMap<String, NusModule> mappedNusModuleList = new HashMap<>();
        int httpEcho;
        String generatedNusModsList;
        try {
            URL url = new URL(onlineLink);
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
            e.printStackTrace();
        }
        return mappedNusModuleList;
    }
}


//the following method only reads the JSON file as a string and cannot be used for parser
//    private static String generateNusModsString(String onlineLink) {
//        int httpEcho;
//        JSONArray modList;
//        JSONParser parser = new JSONParser();
//        String generatedNusModsList = "";
//        try {
//            URL url = new URL(onlineLink);// create new url
//            URLConnection myConnection = url.openConnection();// try to connect and echo back
//            HttpURLConnection httpUrlConnection = (HttpURLConnection) myConnection;
//            httpEcho = httpUrlConnection.getResponseCode();
//            if (httpEcho != HttpURLConnection.HTTP_OK) {
//                System.out.println("Cannot connect website!");
//            } else {
//                BufferedReader in = new BufferedReader((new InputStreamReader(myConnection.getInputStream())));
//                Object obj = parser.parse(in);
//                modList = (JSONArray) obj;
//                generatedNusModsList = modList.toString();
//                return generatedNusModsList;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return generatedNusModsList;
//    }
//}



