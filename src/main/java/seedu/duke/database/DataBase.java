package seedu.duke.database;

import java.io.File;

public class DataBase {
    private static final String root_directory = System.getProperty("user.home");
    private static final String duke_folder_name =  root_directory + File.separator + "diet-duke";
    private static final String default_file_path = root_directory + File.separator + "diet-duke" + File.separator + "data-base";

}
