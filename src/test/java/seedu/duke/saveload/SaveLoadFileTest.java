package seedu.duke.saveload;

public class SaveLoadFileTest {
    public static void main(String[] args){
        Saver saver = new Saver(10, 6);
        saver.add("banana", 5, 2);
        saver.add("pineapple", 7, 1);
        saver.add("cheetan", 2, 3);
        saver.add("beetles", 1, 4);
        saver.save("save_load_test","test1");
    }
}
