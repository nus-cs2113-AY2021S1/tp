package seedu.financeit.utils.storage;

import java.io.IOException;

public class SaveStateHandlerGoalTracker extends SaveStateHandler{

    public SaveStateHandlerGoalTracker(){
        super();
    }

    public SaveStateHandlerGoalTracker(String filepath, String directory){
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
    }

    public void load() throws IOException {
        buildFile();
    }
}
