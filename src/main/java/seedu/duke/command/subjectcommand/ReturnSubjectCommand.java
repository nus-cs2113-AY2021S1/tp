package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.command.taskcommand.TaskCommand;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.TaskException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;
    private TaskList tasks;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                return subject;
            }
        }
        throw new NoSubjectException();
    }

    public void goToSubject(Subject subject, TaskList tasks) {
        Ui.printGoToSubject(subject);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            // TODO: implement the same thing in Duke run in here, but for Tasks instead
            String fullCommand = Ui.readCommand();
            TaskCommand c = TaskParser.parse(fullCommand);
            isSubjectExit = c.isExit();
            try {
                c.execute(tasks);
            } catch (TaskException e) {
                Ui.printError();
            }

        }
        Ui.printExitToMain();

    }

    public boolean isExit() {
        return false;
    }
}
