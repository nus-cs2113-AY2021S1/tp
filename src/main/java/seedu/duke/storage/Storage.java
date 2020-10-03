package seedu.duke.storage;

import seedu.duke.tasks.TaskList;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private File file;
    private String fileName;

    public Storage(String fileName){
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * gets the name of the file
     * @return the name of the file
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Tries to get the file to write on, if not create a new file
     * @param taskList Writes contents of the file into a <code>TaskList</code>
     */
    public void load(TaskList taskList) {
        try {
            getFile(taskList);
        }catch(FileNotFoundException e){
            try{
                createFile();
                Ui.fileNotFoundError();
            }catch(IOException e1){
                Ui.createFileError();
            }
        }
    }

    /**
     * Reads the file contents and writes in onto a <code>TaskList</code>
     * @param taskList
     * @throws FileNotFoundException When there are no files found
     */
    private void getFile(TaskList taskList) throws FileNotFoundException {
        Scanner scan = new Scanner(this.file);
        while(scan.hasNextLine()){
            //add to TaskList
        }
    }

    /**
     * Creates a file
     * @throws IOException When there is an error in creating a file
     */
    private void createFile() throws IOException{
        Path path = Paths.get(this.fileName);
        Files.createDirectory(path.getParent());
    }
}