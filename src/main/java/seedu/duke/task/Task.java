package seedu.duke.task;

public class Task {

    protected String title;
    protected String description;
    protected Priority priority;
    protected boolean isDone;

    public Task(String title, String description, String level) {
        this.title = title;
        this.description = description;
        this.priority = Priority.valueOf(level);
        this.isDone = false;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setPriority(String input) {
        priority = Priority.valueOf(input);
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

    //Delete seedu.duke.task handled outside using ArrayList remove() function
    //iD handled outside. (seedu.duke.task number)
    //valueOf for enum explained here:https://www.baeldung.com/java-string-to-enum


}
