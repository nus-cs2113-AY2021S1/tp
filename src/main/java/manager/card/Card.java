package manager.card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Card {
    private String question;
    private String answer;
    private int previousInterval;
    private LocalDate dueBy;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.dueBy = null;
        this.previousInterval = 1;
    }

    public Card(String question, String answer, LocalDate dueBy) {
        this.question = question;
        this.answer = answer;
        this.dueBy = dueBy;
        this.previousInterval = 1;
    }

    public Card(String question, String answer, LocalDate dueBy, int previousInterval) {
        this.question = question;
        this.answer = answer;
        this.dueBy = dueBy;
        this.previousInterval = previousInterval;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return "[Q] " + question;
    }

    public String getAnswer() {
        return "[A] " + answer;
    }

    public LocalDate getDueBy() {
        return dueBy;
    }

    public int getPreviousInterval() {
        return previousInterval;
    }

    public void setDueBy(LocalDate newDueBy) {
        dueBy = newDueBy;
    }

    public String toString() {
        String dueDate = dueBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[Q] " + question + " | [A] " + answer +"(due date:" + dueDate +")";
    }
}
