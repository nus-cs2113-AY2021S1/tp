package seedu.duke.card;

import seedu.duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String title;
    private TopicList topics = new TopicList(new ArrayList<>());
    private TaskList tasks;

    public Subject(String title) {
        this.title = title;
    }

    public Subject(String title, TopicList topics) {
        this.title = title;
        this.topics = topics;
    }

    public void printSubject(SubjectList subjectList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this subject:\n  " + this + "\n"
                + "Now you have " + subjectList.getList().size() + (subjectList.getList().size() == 1
                ? " subject in the list.\n" : " subjects in the list.\n")
                + "____________________________________________________________");
    }

    public String getTitle() {
        return title;
    }

    public TopicList getTopics() {
        return topics;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public String toString() {
        return this.title;
    }
}
