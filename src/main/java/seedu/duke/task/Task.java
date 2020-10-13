package seedu.duke.task;

public class Task {

public class Task implements Jsonable {

    protected int id;
    protected String title;
    protected String description;
    protected Priority priority;
    protected boolean isDone;

    public Task(String title, String description, String level) {
        this(-1, title, description, level, false);
    }

    public Task(int id, String title, String description, String level, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(level);
        this.isDone = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setPriority(String input) {
        priority = Priority.valueOf(input);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority.toString();
    }

    public String getTitle() {
        return title;
    }

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return title + " " + priority + " " + isDone;
    }


    //Delete seedu.duke.task handled outside using ArrayList remove() function
    //iD handled outside. (seedu.duke.task number)
    //valueOf for enum explained here:https://www.baeldung.com/java-string-to-enum

}
