package seedu.dietbook.saveload;

import java.io.FileNotFoundException;
import java.util.Optional;

abstract class Loader {
    public static Loader load(String folderName, String fileName) throws FileNotFoundException {
        return new FileLoader(folderName, fileName);
    }

    public static Loader loadEmpty() {
        return new EmptyLoader();
    }

    abstract Optional<String> get(int xposition, int yposition) throws IllegalAccessException;

    abstract int getHeight() throws IllegalAccessException;

    abstract int getWidth() throws IllegalAccessException;
}
