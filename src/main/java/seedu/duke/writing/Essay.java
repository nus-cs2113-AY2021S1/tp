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

    public int setNumberOfSentences() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == '.') {
                count++;
            }
        }
        return count;
    }

    public int setNumberOfWords() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
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
        this.numberOfSentences = setNumberOfSentences();
        return this.numberOfSentences;
    }

    @Override
    public int getNumberOfWords() {
        this.numberOfWords = setNumberOfWords();
        return this.numberOfWords;
    }

    @Override
    public String printPoemProperties() {
        return null;
    }

    @Override
    public String printEssayProperties() {
        String content = "This essay has " + getNumberOfSentences() + " sentence(s)"
                + " and " + getNumberOfWords() + " word(s)";
        System.out.println(content);
        return content;
    }
}
