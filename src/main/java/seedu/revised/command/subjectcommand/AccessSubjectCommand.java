package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.command.taskcommand.TaskCommand;
import seedu.revised.command.topiccommand.AccessTopicCommand;
import seedu.revised.command.topiccommand.ListAllTopicCommand;
import seedu.revised.command.topiccommand.TopicCommand;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subjectexception.NoSubjectException;
import seedu.revised.parser.TopicParser;
import seedu.revised.list.TaskList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class AccessSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(AccessSubjectCommand.class.getName());
    private String fullcommand;
    private SubjectList subjectList;

    public AccessSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    /**
     * Adds an instance of the <code>Subject</code> class into a <code>SubjectList</code>.
     *
     * @param subjectList           An instance of the <code>SubjectList</code> class for the user
     *                              to find subjects that matches the user input
     * @param storage               Does nothing in this case but needed since this method was
     *                              implemented from an abstract class
     * @throws NoSubjectException   If the program does not detect the correct syntax for user input
     */
    public void execute(SubjectList subjectList, Storage storage)
            throws NoSubjectException {
        this.subjectList = subjectList;
        Subject gotoSubject = null;

        String[] message = this.fullcommand.split(" ",2);
        if (message.length == 1 || message[1].isEmpty()) {
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

    /**
     * Adds an instance of the <code>Subject</code> class into a <code>SubjectList</code>.
     *
     * @param subject An instance of the <code>Subject</code> class for the user to access
     */
    private void goToSubject(Subject subject) {
        logger.info("Begin accessing a Subject to get Subject details");
        Ui.printGoToSubject(subject);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = TopicParser.parse(fullCommand);
                if (c instanceof ListAllTopicCommand) {
                    ((ListAllTopicCommand) c).setSubjectList(subjectList);
                } else if (c instanceof AccessTopicCommand) {
                    ((AccessTopicCommand) c).setSubjectList(subjectList);
                }
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
        logger.info("Finished access into a subject, going back to main.");
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
