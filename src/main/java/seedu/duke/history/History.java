package seedu.duke.history;

import java.util.ArrayList;
import seedu.duke.quiz.Quiz;

public class History {
    String userName;
    int numberQuizzesTaken;
    ArrayList<Quiz> quizzesTaken;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNumberQuizzesTaken(int num) {
        numberQuizzesTaken = num;
    }

    public void setQuizzesTaken(ArrayList<Quiz> quizzesTaken) {
        this.quizzesTaken = quizzesTaken;
    }
}
