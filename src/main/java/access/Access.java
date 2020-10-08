package access;

import manager.admin.Admin;
import manager.chapter.Chapter;
import manager.module.Module;

public class Access {
    protected String level;
    protected String adminLevel;
    protected String moduleLevel;
    protected String chapterLevel;
    protected Chapter chapter;
    protected Module module;
    protected Admin admin;

    public Access(Admin admin) {
        this.admin = admin;
        this.module = null;
        this.chapter = null;
        this.level = "admin";
        this.adminLevel = "admin";
        this.moduleLevel = "";
        this.chapterLevel = "";
    }

    public Access() {
        this.level = "admin";
        this.adminLevel = "admin";
        this.moduleLevel = "";
        this.chapterLevel = "";
        this.module = null;
        this.chapter = null;
        this.admin = new Admin();
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public String getLevel() {
        return level;
    }

    public String getChapterLevel() {
        return chapterLevel;
    }

    public Module getModule() {
        return module;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public void setModuleLevel(String moduleLevel) {
        if (this.chapterLevel.equals("")) {
            System.out.println("Sorry, you currently are in the chapter level, "
                    + "please go back to admin level first.");
        } else if (!(this.moduleLevel.equals(""))) {
            if (moduleLevel.equals("")) {
                String replacement = "/" + this.moduleLevel;
                this.level = level.replace(replacement, "");
                this.moduleLevel = moduleLevel;
                this.module = null;
            } else {
                System.out.println("Sorry, you are already in the module level, "
                        + "please go back to admin level first.");
            }
        } else {
            this.moduleLevel = moduleLevel;
            this.level = level + "/" + moduleLevel;
            this.module = new Module(moduleLevel);
        }
    }

    public void setChapterLevel(String chapterLevel) {
        if (this.moduleLevel == "") { //wrong level
            System.out.println("Sorry, you currently are in the admin level, please enter module level first.");
        } else {
            if (this.chapterLevel != "") {
                if (chapterLevel == "") {
                    String replacement = "/" + this.chapterLevel;
                    this.level = level.replace(replacement, "");
                    this.chapterLevel = chapterLevel;
                    this.chapter = null;
                } else {
                    System.out.println("Sorry, you are already in the chapter level, "
                            + "please go back to module level first.");
                }
            } else {
                this.chapterLevel = chapterLevel;
                this.level = level + "/" + chapterLevel;
                this.chapter = new Chapter(chapterLevel);
            }
        }
    }

}
