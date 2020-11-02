package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.data.RepaymentList;
import seedu.duke.data.SpendingList;
import seedu.duke.exceptions.InvalidStorageFileExtensionException;
import seedu.duke.exceptions.InvalidStorageFilePathException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Storage {
    private final File file;
    private final Gson gson;

    /**
     * Creates the Storage object based on the user-specified file path.
     * @param path relative path to json file
     * @throws InvalidStorageFilePathException for empty or blank file path
     * @throws InvalidStorageFileExtensionException for non-json file path
     */
    public Storage(String path) throws InvalidStorageFilePathException, InvalidStorageFileExtensionException {
        // Validate path
        String[] parts = path.split("\\.");
        if (path.isBlank() || parts.length == 0) {
            throw new InvalidStorageFilePathException();
        }
        String extension = parts[parts.length - 1];
        if (parts.length == 1 || !extension.equals("json")) {
            throw new InvalidStorageFileExtensionException();
        }
        // Create or Initialise object
        file = new File(path);
        gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();
        if (file.exists()) {
            return;
        }
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new InvalidStorageFilePathException();
        }
    }

    /**
     * Exposes location of storage for Duke applications.
     * @return file path
     */
    public String getFilePath() {
        return file.getPath();
    }

    public SpendingList loadSpendingList() {
        SpendingList sl;
        try {
            Scanner s = new Scanner(file);
            String jsonContent = s
                    .useDelimiter("\\Z")
                    .next();
            sl = gson.fromJson(jsonContent, SpendingList.class);
            if (sl == null) {
                throw new InvalidStorageFilePathException();
            }
        } catch (Exception e) {
            System.out.println("error");
            sl = new SpendingList(this);
        }
        sl.storage = this;
        return sl;
    }

    public RepaymentList loadRepaymentList() {
        RepaymentList rl;
        try {
            Scanner s = new Scanner(file);
            String jsonContent = s
                .useDelimiter("\\Z")
                .next();
            rl = gson.fromJson(jsonContent, RepaymentList.class);
            if (rl == null) {
                throw new InvalidStorageFilePathException();
            }
        } catch (Exception e) {
            rl = new RepaymentList(this);
        }
        rl.storage = this;
        return rl;
    }

    public void save(SpendingList spendingList) throws IOException {
        String jsonContent = gson.toJson(spendingList);
        FileWriter fw = new FileWriter(file, false);
        fw.write(jsonContent);
        fw.close();
    }

    public void save(RepaymentList repaymentList) throws IOException {
        String jsonContent = gson.toJson(repaymentList);
        FileWriter fw = new FileWriter(file, false);
        fw.write(jsonContent);
        fw.close();
    }
}
