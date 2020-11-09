//@@author TomLBZ

package io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type File loader.
 */
public class FileLoader extends IO {

    /**
     * Instantiates a new File loader.
     *
     * @param path     the path
     * @param fileName the file name
     */
    public FileLoader(String path, String fileName) {
        super(path, fileName);
    }

    /**
     * Load all lines string [ ].
     *
     * @return the string [ ]
     */
    public String[] loadAllLines() {
        try {
            if (isFileInvalid()) {
                throw new IOException();
            }
            String[] lines = new String[0];
            lines = Files.readAllLines(
                    Paths.get(path + "/" + fileName), StandardCharsets.UTF_8).toArray(lines);
            return lines;
        } catch (IOException e) {
            return null;
        }
    }

}
