package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import seedu.duke.backend.FileManager;


import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileManagerTest {

    @Test
    public void fileManagerBasicTest() {
        // Set up test folder
        String path = (int) (Math.random() * 1000000) + "/";
        FileManager fm = new FileManager(path);
        // Test 1, basic write
        try {
            FileManager.saveFile(path + "Test1.txt", "Sample 1");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Test 01: Saving to file failed!");
        }

        // Test 2, Read fail
        try {
            assertEquals(3, fm.readAll());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test 02: Reading non-existent data failed!");
        }

        // Test 3, Empty write
        try {
            fm.saveAll();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test 03: Empty write failed!");
        }

        // Test 4, Empty read
        try {
            assertEquals(0, fm.readAll());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Test 04: Reading file with no data failed!");
        }

        // Clean up. If this raises an exception, it means we forgot to close either the writer or reader
        deleteDir(path);
    }

    void deleteDir(String file) {
        Path directory = Paths.get(file);
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
