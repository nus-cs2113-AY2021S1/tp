package seedu.financeit.utils;

import java.io.IOException;

public class SSHAT extends SaveStateHandler {

    public SSHAT (){
        super();
    }

    public SSHAT (String filepath, String directory){
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
    }

    public void load() throws IOException {
        buildFile();
    }
}
