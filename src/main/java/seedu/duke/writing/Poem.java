package seedu.duke.writing;

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

    public Poem(String title, String date, String topic, String content, String author, int id) {
        setDate();
        setType();
        setTitle(title);
        setId(id);
        setTopic(topic);
        setContent(content);
        setAuthor(author);
        this.date = date;
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

    public void setType() {
        this.type = POEM;
    }

    public String getType() {
        return this.type;
    }


}