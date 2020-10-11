package manager.module;

import manager.chapter.Chapter;

import java.util.ArrayList;

public class ChapterList {
    private final ArrayList<Chapter> chapters;

    public ChapterList() {
        chapters = new ArrayList<>();
    }

    public ChapterList(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public ArrayList<Chapter> getAllChapters() {
        return chapters;
    }

    public int getChapterCount() {
        return chapters.size();
    }
}
