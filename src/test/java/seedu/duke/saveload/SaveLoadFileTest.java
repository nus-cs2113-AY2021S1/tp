package seedu.duke.saveload;

import java.io.FileNotFoundException;

public class SaveLoadFileTest {
    public static void main(String[] args) throws FileNotFoundException {
        Saver saver = new Saver(10, 6);
        Loader loader;
        saver.add("banana", 5, 2);
        saver.add("pineapple", 7, 1);
        saver.add("cheetan", 2, 3);
        saver.add("beetles", 1, 4);
        saver.save("save_load_test","test1");

        loader = Loader.load("save_load_test","test1");
        loader.get(5, 2).ifPresent(System.out::println);
        loader.get(7, 1).ifPresent(System.out::println);
        loader.get(2, 3).ifPresent(System.out::println);
        loader.get(1, 4).ifPresent(System.out::println);
        loader.get(1, 3).ifPresent(System.out::println);
        loader.get(10, 6).ifPresent(System.out::println);
    }
}
