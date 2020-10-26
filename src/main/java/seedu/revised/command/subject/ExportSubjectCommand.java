package seedu.revised.command.subject;

import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ExportSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ExportSubjectCommand.class.getName());

    /**
     * Stores an instance of the <code>SubjectList</code> into a file.
     *
     * @param subjectList  An instance of the <code>SubjectList</code> class to be stored
     * @param storage      An instance of the <code>Storage</code> class to store the
     *                     information in
     * @throws IOException If there is an error accessing the storage location
     */
    public void execute(SubjectList subjectList, Storage storage) throws IOException {
        logger.info("Begin exporting all subjects into a file.");
        File exportFile = storage.export(subjectList.getList());
        Ui.printExportSuccessful(exportFile);
        logger.info("Finished exporting all subjects into a file.");
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
