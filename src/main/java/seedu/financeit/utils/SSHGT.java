package seedu.financeit.utils;

import java.io.IOException;

public class SSHGT extends SaveStateHandler{

    public SSHGT (){
        super();
    }

    public SSHGT (String filepath, String directory){
        super(filepath, directory);
    }

    public void save() throws IOException {
        buildFile();
    }

    public void load() throws IOException {
        buildFile();
    }
}
