package access;

import exception.IncorrectAccessLevelException;
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
    protected boolean isAdminLevel;
    protected boolean isModuleLevel;
    protected boolean isChapterLevel;

    public Access(Admin admin) {
        this.admin = admin;
        this.module = null;
        this.chapter = null;
        this.level = "admin";
        this.adminLevel = "admin";
        this.moduleLevel = "";
        this.chapterLevel = "";
        this.isAdminLevel = true;
        this.isModuleLevel = false;
        this.isChapterLevel = false;
    }

    public Access() {
        this.level = "admin";
        this.adminLevel = "admin";
        this.moduleLevel = "";
        this.chapterLevel = "";
        this.module = null;
        this.chapter = null;
        this.admin = new Admin();
        this.isAdminLevel = true;
        this.isModuleLevel = false;
        this.isChapterLevel = false;
    }

    public String getModuleLevel() {
        return moduleLevel;
    }

    public String getLevel() {
        return level;
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

    public boolean isAdminLevel() {
        return isAdminLevel;
    }

    public boolean isModuleLevel() {
        return isModuleLevel;
    }

    public boolean isChapterLevel() {
        return isChapterLevel;
    }

    public void setIsAdminLevel() {
        this.isAdminLevel = true;
        this.isModuleLevel = false;
        this.isChapterLevel = false;
    }

    public void setIsModuleLevel() {
        this.isAdminLevel = false;
        this.isModuleLevel = true;
        this.isChapterLevel = false;
    }

    public void setIsChapterLevel() {
        this.isAdminLevel = false;
        this.isModuleLevel = false;
        this.isChapterLevel = true;
    }

    public void setModuleLevel(String moduleLevel) throws IncorrectAccessLevelException {
        if (isAdminLevel) {
            this.moduleLevel = moduleLevel;
            this.level = level + "/" + moduleLevel;
            this.module = new Module(moduleLevel);
            this.isModuleLevel = true;
            this.isAdminLevel = false;
            return;
        }

        String replacement = "/" + this.moduleLevel;
        this.level = level.replace(replacement, "");
        this.moduleLevel = moduleLevel;
        this.module = null;
        this.isModuleLevel = false;
        this.isAdminLevel = true;
    }

    public void setChapterLevel(String chapterLevel) throws IncorrectAccessLevelException {
        if (isChapterLevel) {
            this.level = adminLevel + "/" + moduleLevel;
            this.chapterLevel = chapterLevel;
            this.chapter = null;
            this.isChapterLevel = false;
            this.isModuleLevel = true;
            return;
        }

        this.chapterLevel = chapterLevel;
        this.level = level + "/" + chapterLevel;
        this.chapter = new Chapter(chapterLevel);
        this.isChapterLevel = true;
        this.isModuleLevel = false;
    }

}