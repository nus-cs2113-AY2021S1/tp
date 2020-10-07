package seedu.duke.level;

import java.util.ArrayList;

public class Module {
    protected ArrayList<Chapter> chapters;
    protected int chapterAmount;
    protected String moduleName;
    protected static int totalModule = 0;

    public Module(String moduleName) {
        this.moduleName = moduleName;
        chapters = new ArrayList<Chapter>();
        chapterAmount = 0;
        totalModule++;
    }

    public Module(String moduleName, ArrayList<Chapter> chapters) {
        this.moduleName = moduleName;
        this.chapters = new ArrayList<>(chapters);
        chapterAmount = chapters.size();
        totalModule++;
    }

    public void add(Chapter chapter){
        chapters.add(chapter);
        chapters.get(chapterAmount).doneAddChapter();
        chapterAmount++;
    }

    public void doneAddModule() {
        System.out.println("    Got it. I've added this module:");
        System.out.println("    " + getModule());
        System.out.println("    Now you have " + totalModule +" modules in the list.");
    }

    public String getModule() {
        return moduleName;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public ArrayList<Chapter> getChapter() {
        return chapters;
    }
}
