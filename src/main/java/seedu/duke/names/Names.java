package seedu.duke.names;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Names {
    protected static List<String> names;

    public static void readNames() {
        Path relativePath = Paths.get("");
        String filePath = relativePath.toAbsolutePath().toString();
        filePath = filePath + File.separator + "Names.txt";
        relativePath = Paths.get(filePath);
        //System.out.println("Current relative path is: " + filePath);
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        /*
        File myFile = new File(relativePath.toAbsolutePath().toString());
        FileOutputStream oFile;
        try {
            myFile.createNewFile();
            oFile = new FileOutputStream(myFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        Charset charset = StandardCharsets.UTF_8;
        try {
            names = Files.readAllLines(relativePath, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getName() {
        readNames();
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(names.size());
        System.out.println(names.get(index));
    }


    public static void filterNames(String match) {
        String toMatch = match.toLowerCase().replace("filter names","").trim();
        //System.out.println("Match: " + toMatch);
        readNames();
        System.out.println(names.stream()
                .filter(x -> x.toLowerCase().contains(toMatch))
                .collect(Collectors.toList()));
    }

    public static void listNames() {
        readNames();
        System.out.println(names);
    }
}
