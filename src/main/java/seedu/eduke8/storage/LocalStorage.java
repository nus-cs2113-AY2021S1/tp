package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class LocalStorage implements Storage {
    protected String filePath = new File("").getAbsolutePath();
    protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected File file;

    protected LocalStorage(String filePath) throws IOException {
        assert filePath != null;

        // Use relative path for Unix systems
        this.filePath = appendRelativePath(this.filePath, filePath);
        file = createFileIfNotExists();
    }

    @Override
    public File save() throws IOException {
        return file;
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException {
        return null;
    }

    protected JSONArray getJsonArrayFromFile() throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(filePath);

        //Read JSON file
        return (JSONArray) jsonParser.parse(reader);
    }

    private String appendRelativePath(String originalPath, String relativePath) {
        String fullPath = originalPath;
        String[] relativePathSplit = relativePath.split("/");
        for (String path: relativePathSplit) {
            fullPath += File.separator + path;
        }

        return fullPath;
    }

    private File createFileIfNotExists() throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        assert file.exists();

        return file;
    }
}
