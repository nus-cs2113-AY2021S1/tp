package manager.chapter;

public class Chapter {
    private String chapterName;

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
    }

    @Override
    public String toString() {
        return "<" + chapterName + ">";
    }
}
