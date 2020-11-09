package seedu.revised.card.quizcard;

import java.util.logging.Logger;

public class Result {
    private static final Logger logger = Logger.getLogger(Result.class.getName());
    private double score = 0;
    private double maxScore;
    private String description;

    public Result(float score, int maxScore) {
        this.score = score;
        this.maxScore = maxScore;
        this.description = "Fail";

    }

    public double getScore() {
        return score;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public String getDescription() {
        return description;
    }


    /**
     * Updates the current score and sets the description of the result.
     *
     * @param score The <code>score</code> to replace the current score
     */
    public void updateResult(double score) {
        logger.info("Update the result and description of the result");
        this.score = score;
        if (this.score >= this.maxScore / 2) {
            this.description = "Pass";
        }
        if (this.score == this.maxScore) {
            this.description = "Excellent";
        }
        logger.info("Finish updating the result of the quiz");
        logger.fine(String.format("The description of the quiz is %s",this.description));
    }

    public String toString() {
        return this.score + "/" + this.maxScore + " -- " + this.description;
    }
}
