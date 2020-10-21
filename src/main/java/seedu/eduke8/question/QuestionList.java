package seedu.eduke8.question;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;

import java.util.ArrayList;

public class QuestionList implements DisplayableList {

    private ArrayList<Displayable> questions;  // list of questions for the particular topic

    public QuestionList(ArrayList<Displayable> questions) {
        // The question list for topics must contain some questions about the topic
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
    public void add(Displayable question) {
        assert question != null;  // Exception in future if user can add question
        questions.add(question);
    }

    @Override
    public void delete(int index) {
        assert (index > 0 && index < questions.size());   // Exception in future if user can delete question
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
