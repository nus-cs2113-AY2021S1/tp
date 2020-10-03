package seedu.duke.storage;

import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.TaskList;
import seedu.duke.tasks.Todo;
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
            String content = scan.nextLine();
            String[] contents = content.split("\\s\\|\\s");
            String legend = contents[0].trim();
            Boolean done = (Integer.valueOf(contents[1].trim()) == 1) ? true : false;
            String action = contents[2].trim();
            String action2 = "";
            if(legend.equals("D") || legend.equals("E")){
                action2 = contents[3].trim();
            }
            if(legend.equals("T")){
                taskList.add(new Todo(action,done));
            }else if(legend.equals("D")){
                taskList.add(new Deadline(action,done,action2));
            }else if(legend.equals("E")){
                taskList.add(new Event(action,done,action2));
            }
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