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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
        this.type = POEM;
        this.title = title;
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.author = new User(author);
        countPoems++;
    }

    public Poem(String title, int id, String topic, String content) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
        this.type = POEM;
        this.title = title;
        this.id = id;
        this.topic = topic;
        this.content = content;
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
}