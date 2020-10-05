package seedu.duke.question;

public class Mcq extends Question {
    private String topic;
    private String subTopic;
    private String[] wrongAnswers = new String[4];
    private String correctAnswer;
    int numWrongAnswers;

    public Mcq(String correctAnswer, String question, int numWrongAnswers) {
        super(correctAnswer, question);
        setNumWrongAnswers(numWrongAnswers);
    }

    private void setNumWrongAnswers(int numWrongAnswers) {
        this.numWrongAnswers = numWrongAnswers;
    }

    public void setWrongAnswer1(String w1) {
        this.wrongAnswers[0] = w1;
    }

    public void setWrongAnswer2(String w2) {
        this.wrongAnswers[1] = w2;
    }

    public void setWrongAnswer3(String w3) {
        this.wrongAnswers[2] = w3;
    }

    public void setWrongAnswer4(String w4) {
        this.wrongAnswers[3] = w4;
    }

    public String getWrongAnswer1() {
        return wrongAnswers[0];
    }

    public String getWrongAnswer2() {
        return wrongAnswers[1];
    }

    public String getWrongAnswer3() {
        return wrongAnswers[2];
    }

    public String getWrongAnswer4() {
        return wrongAnswers[3];
    }

    public boolean returnResult() {
        return this.isCorrect;
    }



    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Create respective topic name folder in the database (might refer to the loaders).
     *
     * @param topic name of the stated topic
     */
    public void createFolder(String topic) {

    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }
}
