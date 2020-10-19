package manager.history;

public class History {
    protected String moduleName;
    protected String chapterName;
    protected int percentage;

    public History(String moduleName, String chapterName, int percentage) {
        this.moduleName = moduleName;
        this.chapterName = chapterName;
        this.percentage = percentage;
    }

    public String toString() {
        return moduleName + "/" + chapterName + " (" + percentage + "% completed)";
    }
}
