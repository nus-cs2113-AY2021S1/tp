package seedu.duke.tool;

public class Access {
    protected String level;
    protected String adminLevel;
    protected String moduleLevel;
    protected String chapterLevel;
    protected String cardLevel;

    public Access(String level) {
        this.level = level;
    }

    public Access() {
        this.level = "admin";
        this.adminLevel = "admin";
        this.cardLevel = "";
        this.moduleLevel = "";
        this.chapterLevel = "";
    }

    public void incrementLevel(String newLevel) {
        this.level = level + "/" + newLevel;
    }

    public void decreaseLevel(String currentLevel) {
        level.replace("/" + currentLevel, "");
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public String getLevel() {
        return level;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public String getChapterLevel() {
        return chapterLevel;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setModuleLevel(String moduleLevel) {
        if(moduleLevel != "") {
            this.moduleLevel = moduleLevel;
            this.level = level + "/" + moduleLevel;
            return;
        }
        String replacement = "/" + this.moduleLevel;
        this.level = level.replace(replacement, "");
        this.moduleLevel = moduleLevel;
    }
}
