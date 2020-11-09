package seedu.eduke8.storage;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;
import seedu.eduke8.exception.Eduke8Exception;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class LocalStorage implements Storage {
    protected static final String KEY_TOPIC = "topic";
    protected static final String KEY_QUESTIONS = "questions";
    protected static final String KEY_DESCRIPTION = "description";
    protected static final String KEY_OPTIONS = "options";
    protected static final String KEY_HINT = "hint";
    protected static final String KEY_EXPLANATION = "explanation";
    protected static final String KEY_CORRECT = "correct";
    protected static final String KEY_NOTES = "notes";
    protected static final String KEY_BOOKMARKED = "bookmarked";
    protected static final String KEY_TEXT = "text";
    protected static final String PATH_SEPARATOR = "/";
    protected String filePath = new File("").getAbsolutePath();
    protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected File file;

    protected LocalStorage(String filePath) {
        assert filePath != null;

        // Use relative path for Unix systems
        this.filePath = appendRelativePath(this.filePath, filePath);
        file = new File(this.filePath);
    }

    @Override
    public File save() throws IOException {
        return createFileIfNotExists();
    }

    @Override
    public ArrayList<Displayable> load()
            throws IOException, ParseException, Eduke8Exception, ClassCastException, NullPointerException {
        return new ArrayList<>();
    }

    protected JSONArray getJsonArrayFromFile(String fallbackResource) throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        if (file.exists()) {
            FileReader reader = new FileReader(filePath);

            //Read JSON file
            return (JSONArray) jsonParser.parse(reader);
        } else {
            InputStream is = getClass().getResourceAsStream(fallbackResource);
            if (is == null) {
                throw new IOException();
            }
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);

            //Read JSON file
            return (JSONArray) jsonParser.parse(reader);
        }
    }

    private String appendRelativePath(String originalPath, String relativePath) {
        String fullPath = originalPath;
        String[] relativePathSplit = relativePath.split(PATH_SEPARATOR);
        for (String path: relativePathSplit) {
            fullPath += File.separator + path;
        }

        return fullPath;
    }

    private File createFileIfNotExists() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        assert file.exists();

        return file;
    }
}
