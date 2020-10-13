package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;

public class QuestionList implements DisplayableList {

    private ArrayList<Displayable> questions;  // list of questions for the particular topic

    public QuestionList(ArrayList<Displayable> questions) {
        // assert questions.size() > 0;     // for another pull request
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
    public void add(Displayable question) {
        questions.add(question);
    }

    @Override
    public void delete(int index) {
        questions.remove(index);
    }

    @Override
    public Displayable find(String description) {
        for (Displayable question : questions) {
            if (description.equals(question.getDescription())) {
                return question;
            }
        }
        return null;
    }
}
