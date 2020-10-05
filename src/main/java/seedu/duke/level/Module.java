package seedu.duke.level;

import java.util.ArrayList;

public class Module {
    protected ArrayList<Chapter> chapters;
    protected int chapterAmount;
    protected String moduleName;
    protected int totalModule = 0;

    public Module(String moduleName) {
        this.moduleName = moduleName;
        chapters = new ArrayList<Chapter>();
        chapterAmount = 0;
        totalModule++;
    }

    public void add(Chapter chapter){
        chapters.add(chapter);
        chapters.get(chapterAmount).doneAddChapter();
        chapterAmount++;
    }

    public void doneAddModule() {
        totalModule++;
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + getModule());
        System.out.println("    Now you have " + totalModule +" modules in the list.");
    }

    private String getModule() {
        return moduleName;
    }
}
