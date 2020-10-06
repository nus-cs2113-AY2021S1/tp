package seedu.duke;

import seedu.duke.card.Subject;
import seedu.duke.command.subjectcommand.ReturnSubjectCommand;
import seedu.duke.command.subjectcommand.SubjectCommand;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;
import seedu.duke.exception.SubjectException;
import seedu.duke.exception.TaskException;
import seedu.duke.card.SubjectList;
import seedu.duke.parser.SubjectParser;
import seedu.duke.storage.SubjectStorage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static String FILENAME = "data/duke.txt";

    private SubjectStorage subjectStorage;
    private SubjectList subjects;
    private TaskList tasks;

    /**
     * Initialises Duke.
     *
     * @param filename of the <code>File</code> that stores the text data of the to-do list
     */
    public Duke(String filename) {
        subjectStorage = new SubjectStorage(filename);
        subjects = new SubjectList(new ArrayList<>());
        tasks = new TaskList(new ArrayList<>());
        subjectStorage.load(subjects);
    }

    /**
     * Runs the Duke program until the user exits the program.
     */
    public void run() {
        Ui.printStart();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                SubjectCommand c = SubjectParser.parse(fullCommand);
                if (c instanceof ReturnSubjectCommand) {
                    Subject subject = c.execute(subjects);
                    ((ReturnSubjectCommand) c).goToSubject(subject, tasks);
                } else {
                    c.execute(subjects);
                }
                isExit = c.isExit();
                subjects.saveSubject(subjectStorage.getFileName());
            } catch (IOException e) {
                Ui.printWritingError();
            } catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException e) {
                Ui.printNoSubjectError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedSubjectError();
            }
        }
        Ui.printBye();
    }

    public static void main(String[] args) {
        new Duke(FILENAME).run();
    }
}
