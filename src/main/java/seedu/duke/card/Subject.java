package seedu.duke.card;

import seedu.duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String title;
    private TopicList topics;
    private TaskList tasks;

    public Subject(String title) {
        this(title, new ArrayList<>());
    }

    public Subject(String title, List<Topic> topics) {
        this.title = title;
        this.topics = new TopicList(topics);
        tasks = new TaskList(new ArrayList<>());
    }

    public Subject(String title, TopicList topics) {
        this.title = title;
        this.topics = topics;
        tasks = new TaskList(new ArrayList<>());
    }

    public Subject(String title, TopicList topics, TaskList tasks) {
        this.title = title;
        this.topics = topics;
        this.tasks = tasks;
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

    @Override
    public String toString() {
        return this.title;
    }
}
