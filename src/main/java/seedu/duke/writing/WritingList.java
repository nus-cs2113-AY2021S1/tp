package seedu.duke.writing;

import java.util.ArrayList;

import static seedu.duke.constants.Logos.PLAIN_TEXT_DIVIDER;

public class WritingList {
    private int countWriting;
    public static ArrayList<Writings> writing;

    public WritingList() {
        countWriting = 0;
        this.writing = new ArrayList<>();
    }

    public void add(Writings w) {
        countWriting++;
        writing.add(w);
    }

    public Writings get(int i) {
        return writing.get(i);
    }

    public void remove(int i) {
        writing.remove(i);
    }

    public int getCountWritings() {
        return this.countWriting;
    }

    /**
     *  print all of the current writings in the Arraylist with details.
     */
    public static void printWritings() {
        for (int i = 0; i < writing.size(); i++) {
            System.out.println("This is a " + writing.get(i).getType());
            System.out.println("Written by " + writing.get(i).getAuthor().getName() + "\n");
            System.out.println(writing.get(i).title.toUpperCase() + "\n");
            System.out.println(writing.get(i).content);
            System.out.println("This writing is created on " + writing.get(i).date);
            System.out.println(PLAIN_TEXT_DIVIDER);
        }
    }

    public static void printAskForType() {
        System.out.println("Please let us know your type of writings, either poem or essay");
    }

    public static void printAskForTitle() {
        System.out.println("Please let us know the title of your writing");
    }

    public static int getWritingSize() {
        return writing.size();
    }
}
