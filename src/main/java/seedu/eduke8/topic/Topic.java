package seedu.eduke8.topic;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.note.NoteList;
import seedu.eduke8.question.QuestionList;
import seedu.eduke8.ui.Ui;

import java.util.ArrayList;

public class Topic implements Displayable {
    private String description;
    private QuestionList questionList;
    private NoteList noteList;
    private boolean wasShown;

    public Topic(String description, QuestionList questionList) {
        assert description != null;
        assert questionList != null;

        this.description = description;
        this.questionList = questionList;
        this.noteList = new NoteList(new ArrayList<>());
        wasShown = false;
    }

    public Topic(String description, QuestionList questionList, NoteList noteList) {
        assert description != null;
        assert questionList != null;

        this.description = description;
        this.questionList = questionList;
        this.noteList = noteList;
        wasShown = false;
    }


    /**
     * Returns the Topic description.
     *
     * @return description of the Topic object.
     */
    @Override
    public String getDescription() {
        markAsShown();
        return description;
    }

    /**
     * Sets wasShown to be of boolean value "true".
     */
    @Override
    public void markAsShown() {
        wasShown = true;
    }

    /**
     * Returns boolean value of the Topic object's wasShown.
     *
     * @return boolean value of Topic's wasShown.
     */
    @Override
    public boolean wasShown() {
        return wasShown;
    }

    /**
     * Returns the Topic object's QuestionList.
     *
     * @return QuestionList questionList.
     */
    public QuestionList getQuestionList() {
        return questionList;
    }

    /**
     * Returns the specified Topic object's NoteList object.
     *
     * @return NoteList noteList found in this Topic object
     */
    public NoteList getNoteList() {
        return noteList;
    }
}
