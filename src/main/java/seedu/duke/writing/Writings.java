package seedu.duke.writing;

import seedu.duke.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;

public abstract class Writings {
    protected String title;
    protected int id;
    protected String topic;
    protected String date;
    protected String content;
    protected User author;
    protected String type;

    public String getTitle() {
        return title;
    }

    public static void createTitle(String input) {
        String[] words = input.split(" ", 2);

    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDate() {
        return date;
    }

    public void setDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }
}