package manager.module;

import manager.chapter.Chapter;

import java.util.ArrayList;

public class Module {
    protected ChapterList chapters;
    protected String moduleName;

    public Module(String moduleName) {
        this.moduleName = moduleName;
        chapters = new ChapterList();
    }

    public Module(String moduleName, ArrayList<Chapter> chapters) {
        this.moduleName = moduleName;
        this.chapters = new ChapterList(chapters);
    }


    public String getModuleName() {
        return moduleName;
    }

    public ChapterList getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = new ChapterList(chapters);
    }

    public String toString() {
        return moduleName;
    }
}