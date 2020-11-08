package manager.history;

public class History {
    protected String moduleName;
    protected String chapterName;

    public History(String moduleName, String chapterName) {
        this.moduleName = moduleName;
        this.chapterName = chapterName;
    }

    public String toString() {
        return moduleName + "/" + chapterName;
    }
}
