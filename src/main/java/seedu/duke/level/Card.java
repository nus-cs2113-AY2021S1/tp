package seedu.duke.level;

public class Card {
    protected String question;
    protected String answer;
    protected int totalCard = 0;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        totalCard++;
    }


    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void doneAddCard() {
        System.out.println("    Got it. I've added this card:");
        System.out.println("    " + getQuestion() + "   " + getAnswer());
        System.out.println("    Now you have " + totalCard +" cards in the list.");
    }
}
