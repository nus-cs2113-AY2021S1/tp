package manager.module;

import manager.chapter.Chapter;

import java.util.ArrayList;

/**
 * A list of chapters.
 */
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

    public Chapter getChapter(int chapterIndex) {
        return chapters.get(chapterIndex);
    }

    /**
     * Checks if the list contains an equivalent chapter that has the same {@code newChapterName}.
     *
     * @param newChapterName new name for a chapter
     * @return true if the list contains an equivalent chapter that has the same name as the given argument.
     */
    public boolean checkChapterExistence(String newChapterName) {
        for (Chapter chapter : chapters) {
            String chapterName = chapter.getChapterName().toLowerCase();
            if (chapterName.equals(newChapterName)) {
                return true;
            }
        }
        return false;
    }
}
