package seedu.duke.card;

import seedu.duke.list.TaskList;

import java.util.List;

public class Subject {
    private String title;
    private List<Topic> topics;
    private TaskList tasks;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public TaskList getTasks() {
        return tasks;
    }
}
