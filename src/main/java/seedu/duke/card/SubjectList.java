package seedu.duke.card;

import seedu.duke.card.Subject;
import seedu.duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SubjectList {
    private List<Subject> subjectList;

    public SubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    /**
     * Adds a <code>Task</code> into a <code>TaskList</code>.
     *
     * @param subject The <code>Subject</code> to add into a <code>SubjectList</code>
     */
    public void add(Subject subject) {
        this.subjectList.add(subject);
    }

    /**
     * Gets a <code>ArrayList</code> of Subjects in a <code>SubjectList</code> instance.
     *
     * @return the <code>ArrayList</code> of Subjects
     */
    public List<Subject> getList() {
        return this.subjectList;
    }

    /**
     * Saves the Tasks in a <code>TaskList</code> into a <code>File</code>.
     *
     * @param filename of the <code>File</code> to write to
     * @throws IOException if there is an error writing to the file
     */
    public void saveSubject(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (Subject subject : this.subjectList) {
        }
        fileWriter.close();
    }
}
