package seedu.duke.sprint;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;
import seedu.duke.task.Task;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Member implements Jsonable {
    public String userId;
    ArrayList<Task> allocatedTask;

    public Member(String userId) {
        this.userId = userId;
        allocatedTask = new ArrayList<>(100);

    }

    public String getUserId() {
        return userId;
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

    public void addMemberTask(Task task) {
        allocatedTask.add(task);
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
        jMember.put("id", userId);
        final JsonArray jTasks = new JsonArray();
        for (Task task : allocatedTask) { //Add only the ID that reference to the actual task in ProjectBacklog
            jTasks.add(task.getId());
        }
        jMember.toJson(writer);
    }
}
