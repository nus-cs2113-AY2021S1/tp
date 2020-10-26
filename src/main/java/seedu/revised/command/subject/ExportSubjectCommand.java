package seedu.revised.command.subject;

import seedu.revised.list.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.File;
import java.io.IOException;

public class ExportSubjectCommand extends SubjectCommand {

    public void execute(SubjectList subjectList, Storage storage) throws IOException {
        File exportFile = storage.export(subjectList.getList());
        Ui.printExportSuccessful(exportFile);
    }

    public boolean isExit() {
        return false;
    }
}
