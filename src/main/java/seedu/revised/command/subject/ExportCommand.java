package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.File;
import java.io.IOException;

public class ExportCommand extends SubjectCommand {
    public Subject execute(SubjectList subjectList, Storage storage) throws IOException {
        File exportFile = storage.export(subjectList.getList());
        Ui.printExportSuccessful(exportFile);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
