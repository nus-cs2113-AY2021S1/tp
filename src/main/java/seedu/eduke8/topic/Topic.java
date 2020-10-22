package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.note.NoteList;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class Topic implements Displayable {
    private String description;
    private QuestionList questionList;
    private ArrayList<Displayable> noteList;
    private boolean wasShown;

    public Topic(String description, QuestionList questionList) {
        assert description != null;
        assert questionList != null;

        this.description = description;
        this.questionList = questionList;
        wasShown = false;
    }

    public Topic(String description, QuestionList questionList, ArrayList<Displayable> noteList) {
        assert description != null;
        assert questionList != null;

        this.description = description;
        this.questionList = questionList;
        this.noteList = noteList;
        wasShown = false;
    }

    @Override
    public String getDescription() {
        wasShown = true;
        return description;
    }

    @Override
    public boolean wasShown() {
        return wasShown;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public void showNotes(Ui ui) {
        ui.printNoteList(noteList);
    }
}
