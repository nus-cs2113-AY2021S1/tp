package seedu.duke.model.member;

import com.github.cliftonlabs.json_simple.JsonObject;
import seedu.duke.storage.JsonableObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Member implements JsonableObject {

    private String userId;
    private ArrayList<Integer> taskList;
    
    public Member() {
    }

    public Member(String userId) {
        this.userId = userId;
        taskList = new ArrayList<>();

    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Integer> getTaskList() {
        return taskList;
    }

    public void allocateTask(int taskid) {
        taskList.add(taskid);
    }

    public void deallocateTask(int taskid) {
        taskList.remove((Object) taskid);
    }


    @Override
    public boolean equals(Object o) {
        /* Check if o is an instance of Member or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Member)) {
            return false;
        }
        Member v = (Member) o;
        // Compare the userId of members and return accordingly
        return userId.equals(v.userId);
    }

    @Override
    public String toJson() {
        final StringWriter writeable = new StringWriter();
        try {
            this.toJson(writeable);
        } catch (IOException e) {
            System.out.println("[Error] Cannot convert this project to JSON");
            e.printStackTrace();
        }
        return writeable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject jMember = new JsonObject();
        jMember.put("userId", userId);
        jMember.put("taskList", taskList);
        jMember.toJson(writer);
    }

    @Override
    public void fromJson(JsonObject jsonObj) {
        userId = JsonableObject.parseString(jsonObj, "userId");
        taskList = JsonableObject.parseIntegerList(jsonObj, "taskList");
    }
}
