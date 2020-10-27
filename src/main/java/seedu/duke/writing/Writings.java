package seedu.duke.writing;

import seedu.duke.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.duke.constants.DataFileConvention.LENGTH_OF_DATE;
import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public abstract class Writings {
    protected String title;
    protected int id;
    protected String topic;
    protected String date;
    protected LocalDate reminderDate;
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

    public LocalDate getReminderDate() {
        return reminderDate;
    }

    public String getReminderDateString() {
        return getReminderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
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
        DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(outputDateFormat);
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

    public String getType() {
        return type;
    }

    public void setAuthor(String author) {
        this.author = new User(author);
    }

    public abstract int getNumberOfLines();

    public abstract int getNumberOfSentences();

    public abstract int getNumberOfWords();

    public abstract void printPoemProperties();

    public abstract void printEssayProperties();

    public void printWritingsProperties() {
        System.out.println("This is a " + getType());
        System.out.println("Written by " + getAuthor().getName() + "\n");
        System.out.println("Id: " + getId());
        System.out.println(getTitle().toUpperCase() + "\n");
        System.out.println(getContent());
        System.out.println("This writing was created on " + date);
        System.out.println("You want to continue on this writing on " +
                getReminderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(PLAIN_TEXT_DIVIDER);
    }
}