package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ListSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ListSubjectCommand.class.getName());

    public void execute(SubjectList subjectList, Storage storage) {
            logger.info("Begin listing subjects in the SubjectList.");
            Ui.printSubjectList(subjectList.getList());
            logger.info("Finish listing the subjects in the SubjectList.");
    }

    public boolean isExit() {
        return false;
    }
}
