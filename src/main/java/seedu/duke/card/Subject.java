package seedu.duke.card;

import seedu.duke.task.TaskList;

import java.util.List;

public class Subject {
    private String title;
    private List<Topic> topics;
    private TaskList tasks;

    public Subject(String title){
        this.title = title;
    }

    public void printSubject(SubjectList subjectList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n  " + this + "\n"
                + "Now you have " + subjectList.getList().size() + (subjectList.getList().size() == 1
                ? " task in the list.\n" : " tasks in the list.\n")
                + "____________________________________________________________");
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public boolean removeTopic(Topic topic) {
        return topics.remove(topic);
    }

    public Topic removeTopic(int index) {
        return topics.remove(index);
    }

    public String getTitle() {
        return title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public String toString(){
        return this.title;
    }
}
