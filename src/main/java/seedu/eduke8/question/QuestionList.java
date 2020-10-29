package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;

import static seedu.eduke8.exception.ExceptionMessages.ERROR_QUESTION_DOES_NOT_EXIST;

import java.util.ArrayList;

public class QuestionList implements DisplayableList {

    private ArrayList<Displayable> questions;  // List of questions for a particular topic

    public QuestionList(ArrayList<Displayable> questions) {
        assert questions != null;
        assert questions.size() > 0;

        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public ArrayList<Displayable> getInnerList() {
        return questions;
    }

    @Override
    public Displayable find(String description) throws Eduke8Exception {
        for (Displayable question : questions) {
            if (description.equalsIgnoreCase(question.getDescription())) {
                return question;
            }
        }
        throw new Eduke8Exception(ERROR_QUESTION_DOES_NOT_EXIST);
    }
}
