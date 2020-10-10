package seedu.duke.todo;

public class ToDo {
    private String name;
    private String deadline;
    private boolean isDone;

    public ToDo(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString(){
        return "T | " + ((this.isDone)? "1" : "0")
                + " | " + this.name + " | " + this.deadline;
    }
}
