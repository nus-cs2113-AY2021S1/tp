package manager.chapter;

public class DueChapter {
    private final String parentModule;
    private final Chapter chapter;

    public DueChapter(String parentModule, Chapter chapter) {
        this.parentModule = parentModule;
        this.chapter = chapter;
    }

    public Chapter getChapter() {
        return chapter;
    }

    @Override
    public String toString() {
        return "Module: " + parentModule + "; Chapter: " + chapter.toString();
    }
}
