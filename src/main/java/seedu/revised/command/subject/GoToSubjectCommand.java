package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.command.task.TaskCommand;
import seedu.revised.command.topic.TopicCommand;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.parser.TopicParser;
import seedu.revised.list.TaskList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.time.format.DateTimeParseException;

public class GoToSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public GoToSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public void execute(SubjectList subjectList, Storage storage) throws NoSubjectException {
        Subject gotoSubject = null;

        String[] message = this.fullcommand.split(" ");
        if (message[1].isEmpty()) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                gotoSubject = subject;
                break;
            }
        }

        if (gotoSubject == null) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }

        goToSubject(gotoSubject);
    }

    private void goToSubject(Subject subject) {
        Ui.printGoToSubject(subject);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = TopicParser.parse(fullCommand);
                if (c instanceof TopicCommand) {
                    ((TopicCommand) c).execute(subject);
                } else if (c instanceof TaskCommand) {
                    TaskList taskList = subject.getTasks();
                    ((TaskCommand) c).execute(taskList);
                }

                isSubjectExit = c.isExit();

            } catch (NumberFormatException e) {
                Ui.printErrorMsg(Ui.INDEX_FORMAT_EXCEPTION);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMsg(Ui.INDEX_OUT_OF_BOUND_EXCEPTION);
            } catch (IllegalArgumentException | DateTimeParseException d) {
                Ui.printErrorMsg(Ui.INVALID_DATETIME_EXCEPTION);
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
        Ui.printBackToSubjects();

    }

    public boolean isExit() {
        return false;
    }
}
