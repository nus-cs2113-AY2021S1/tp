package manager.card;

import java.time.LocalDate;

public class Card {
    private String question;
    private String answer;
    private int previousInterval;
    private LocalDate dueBy;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.dueBy = LocalDate.now();
        this.dueBy = null;
        this.previousInterval = 1;
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
        return "[Q] " + question + " | [A] " + answer;
    }
}
