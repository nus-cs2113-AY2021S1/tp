package seedu.duke.writing;

import seedu.duke.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.duke.constants.DataFileConvention.ESSAY;
import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;


public class Essay extends Writings {
    private int numberOfSentences;
    private int numberOfWords;
    private int countEssays;

    public Essay(String title, int id, String topic, String content, String author) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
        this.type = ESSAY;
        this.title = title;
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.author = new User(author);
        countEssays++;
    }

    public Essay(String title, int id, String topic, String content) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.toString().substring(0, LENGTH_OF_DATE);
        this.type = ESSAY;
        this.title = title;
        this.id = id;
        this.topic = topic;
        this.content = content;
        this.author = getAuthor();
        countEssays++;
    }

    public void setNumberOfSentences() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == '.') {
                count++;
            }
        }
        this.numberOfSentences = count;
    }

    public void setNumberOfWords() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == ' ') {
                count++;
            }
        }
    }

    public int getCountEssays() {
        return countEssays;
    }
}
