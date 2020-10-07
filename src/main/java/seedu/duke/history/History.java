package seedu.duke.history;

import java.util.ArrayList;
import seedu.duke.quiz.Quiz;

public abstract class History {
    private String userName;
    private String essayName;
    private int id;
    private String topic;
    private String date;

    public String getEssay() {
        return essayName;
    }

    public void setEssay(String essay) {
        this.essayName = essay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    @Override
    public String toString() {
        return "Date created: " + date + "\n"
                + "topic" + topic + "\n"
                + essayName;
    }

    public History(String userName, String essay, int id, String topic, String date) {
        this.userName = userName;
        this.essayName = essay;
        this.id = id;
        this.topic = topic;
        this.date = date;
    }
}
