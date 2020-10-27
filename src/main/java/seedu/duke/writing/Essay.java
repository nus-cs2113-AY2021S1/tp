package seedu.duke.writing;

import java.time.LocalDate;

import static seedu.duke.constants.DataFileConvention.ESSAY;

public class Essay extends Writings {
    private int numberOfSentences;
    private int numberOfWords;
    private int countEssays;

    public Essay(String title, int id, String topic, String content, String author, LocalDate reminderDate) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setReminderDate(reminderDate);
        setContent(content);
        setAuthor(author);
        countEssays++;
    }

    public Essay(String title, String date, String topic, String content,
                 String author, int id, LocalDate reminderDate) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setContent(content);
        setAuthor(author);
        setReminderDate(reminderDate);
        this.date = date;
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

    public void setType() {
        this.type = ESSAY;
    }

    @Override
    public int getNumberOfLines() {
        return 0;
    }

    @Override
    public int getNumberOfSentences() {
        return this.numberOfSentences;
    }

    @Override
    public int getNumberOfWords() {
        return this.numberOfWords;
    }

    @Override
    public void printPoemProperties() {

    }

    @Override
    public void printEssayProperties() {
        System.out.println("This essay has " + getNumberOfSentences()
                + " and " + getNumberOfWords());
    }
}
