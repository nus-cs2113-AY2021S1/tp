package manager.history;

/**
 * Represents a Revision which contains module and chapter name.
 */
public class History {
    protected String moduleName;
    protected String chapterName;

    /**
     * Constructs a History.
     *
     * @param moduleName the name of the module which contains the revised chapter
     * @param chapterName the name of the revised chapter
     */
    public History(String moduleName, String chapterName) {
        this.moduleName = moduleName;
        this.chapterName = chapterName;
    }

    public String toString() {
        return moduleName + "/" + chapterName;
    }
}
