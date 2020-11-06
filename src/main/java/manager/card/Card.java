package manager.card;

import java.time.LocalDate;

public class Card {
    private String question;
    private String answer;
    private int previousInterval;
    private LocalDate dueBy;
    private int rating;
    public static final int CANNOT_ANSWER = 0;
    public static final int EASY = 1;
    public static final int MEDIUM = 2;
    public static final int HARD = 3;

    public Card(String question, String answer) {
        setAnswer(answer);
        setQuestion(question);
        setDueBy(null);
        setPreviousInterval(1);
        setRating(CANNOT_ANSWER);
    }

    public Card(String question, String answer, int previousInterval, int rating) {
        setAnswer(answer);
        setQuestion(question);
        setDueBy(null);
        setPreviousInterval(previousInterval);
        setRating(rating);
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getRevisionQuestion() {
        return "[Q] " + question;
    }

    public String getRevisionAnswer() {
        return "[A] " + answer;
    }

    public LocalDate getDueBy() {
        return dueBy;
    }

    public int getPreviousInterval() {
        return previousInterval;
    }

    public void setPreviousInterval(int newInterval) {
        this.previousInterval = newInterval;
    }

    public void setDueBy(LocalDate newDueBy) {
        dueBy = newDueBy;
    }

    public String toString() {
        return "[Q] " + question + " | [A] " + answer;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
