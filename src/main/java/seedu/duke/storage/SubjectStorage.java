package seedu.duke.storage;

import seedu.duke.card.SubjectList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SubjectStorage {

    private File file;
    private String fileName;

    public SubjectStorage(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * gets the name of the file.
     *
     * @return the name of the file
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Tries to get the file to write on, if not create a new file.
     *
     * @param subjectList Writes contents of the file into a <code>TaskList</code>
     */
    public void load(SubjectList subjectList) {
        try {
            getFile(subjectList);
        } catch (FileNotFoundException e) {
            try {
                createFile();
                //Ui.fileNotFoundError();
            } catch (IOException e1) {
                Ui.createFileError();
            }
        }
    }

    /**
     * Reads the file contents and writes in onto a <code>TaskList</code>.
     *
     * @param subjectList the taskList which data will be read into
     * @throws FileNotFoundException When there are no files found
     */
    private void getFile(SubjectList subjectList) throws FileNotFoundException {
        Scanner scan = new Scanner(this.file);
        while (scan.hasNextLine()) {

        }
    }

    /**
     * Creates a file.
     *
     * @throws IOException When there is an error in creating a file
     */
    private void createFile() throws IOException {
        Path path = Paths.get(this.fileName);
        Files.createDirectory(path.getParent());
    }
}
