package manager.card;

import java.time.LocalDate;

public class Card {
    private String question;
    private String answer;
    private int previousInterval;
    private LocalDate date;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.date = LocalDate.now();
        this.previousInterval = 1;
    }

    public String getQuestion() {
        return "[Q] " + question;
    }

    public String getAnswer() {
        return "[A] " + answer;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return "[Q] " + question + " | [A] " + answer;
    }
}
