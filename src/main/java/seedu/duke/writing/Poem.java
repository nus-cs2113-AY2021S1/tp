package seedu.duke.writing;

import seedu.duke.user.User;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;
import static seedu.duke.constants.DataFileConvention.POEM;


public class Poem extends Writings {
    private int numberOfLines;
    private int numberOfWords;
    private int countPoems;

    public Poem(String title, int id, String topic, String content, String author) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setContent(content);
        setAuthor(author);
        countPoems++;
    }

    public Poem(String title, int id, String topic, String content) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setContent(content);
        this.author = getAuthor();
        countPoems++;
    }

    public void setNumberOfLines() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == '\n') {
                count++;
            }
        }
        this.numberOfLines = count;
    }

    public int getNumberOfLines() {
        return this.numberOfLines;
    }

    public void setNumberOfWords() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == ' ' || this.content.charAt(i) == '\n') {
                count++;
            }
        }
    }

    public int getCountPoems() {
        return countPoems;
    }

    public String getTitle() {
        return this.title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
    }

    public void setType() {
        this.type = POEM;
    }

    public String getType() {
        return this.type;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setAuthor(String author) {
        this.author = new User(author);
    }

}