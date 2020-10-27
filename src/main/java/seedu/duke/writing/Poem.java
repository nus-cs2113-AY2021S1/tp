package seedu.duke.writing;

import java.time.LocalDate;

import static seedu.duke.constants.DataFileConvention.POEM;


public class Poem extends Writings {
    private int numberOfLines;
    private int numberOfWords;
    private int countPoems;

    public Poem(String title, int id, String topic, String content, String author, LocalDate reminderDate) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setContent(content);
        setReminderDate(reminderDate);
        setAuthor(author);
        setNumberOfLines();
        setNumberOfWords();
        countPoems++;
    }

    public Poem(String title, String date, String topic, String content,
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
        countPoems++;
        setNumberOfLines();
        setNumberOfWords();
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

    @Override
    public int getNumberOfLines() {
        return this.numberOfLines;
    }

    @Override
    public int getNumberOfSentences() {
        return 0;
    }

    @Override
    public int getNumberOfWords() {
        return this.numberOfWords;
    }

    @Override
    public void printPoemProperties() {
        System.out.println("This poem has " + getNumberOfLines()
                + " and " + getNumberOfWords());
    }

    @Override
    public void printEssayProperties() {

    }

    public void setNumberOfWords() {
        int count = 0;
        for (int i = 0; i < this.content.length(); i++) {
            if (this.content.charAt(i) == ' ' || this.content.charAt(i) == '\n') {
                count++;
            }
            this.numberOfWords = count;
        }
    }

    public int getCountPoems() {
        return countPoems;
    }

    public void setType() {
        this.type = POEM;
    }

    public String getType() {
        return this.type;
    }


}