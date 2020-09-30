package seedu.duke.question;

public class Question {
    public String correctAnswer;
    public String question;
    public boolean isCorrect;

    //public String hint; // TODO: for version 2 only delete for ver 1 submission

    public Question(String correctAnswer, String question) {
        setCorrectAnswer(correctAnswer);
        setQuestion(question);
        this.isCorrect = false;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

}
