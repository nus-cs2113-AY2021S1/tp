package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.topic.TopicList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserStorage extends LocalStorage {
    private DisplayableList bookmarkList;
    private DisplayableList userList;
    private TopicList topicList;

    public UserStorage(String filePath, TopicList topicList) {
        super(filePath);
        this.topicList = topicList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public File save() throws IOException {
        file = super.save();

        // First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Write JSON file
        try (FileWriter writer = new FileWriter("employees.json")) {

            writer.write(employeeList.toJSONString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException {
        if (!file.exists()) {
            return new ArrayList<>();
        }

        JSONArray objectsAsJsonArray = getJsonArrayFromFile();

        /*
        * [{question: "", bookmarked: "", note: ""}]
        * */


        return null;
    }
}
