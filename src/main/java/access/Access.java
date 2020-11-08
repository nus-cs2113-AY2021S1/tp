package access;

import manager.admin.Admin;
import manager.chapter.Chapter;
import manager.module.Module;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MODULE;

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

    public void setModuleLevel(String moduleLevel) {
        if (isAdminLevel) {
            setGoModuleLevel(moduleLevel);
            return;
        }
        setBackAdminLevel(moduleLevel);

    }

    public void setGoModuleLevel(String moduleLevel) {
        this.moduleLevel = moduleLevel;
        this.level = level + "/" + moduleLevel;
        this.module = new Module(moduleLevel);
        setIsModuleLevel();
    }

    public void setBackAdminLevel(String moduleLevel) {
        this.level = adminLevel;
        this.moduleLevel = moduleLevel;
        this.module = null;
        setIsAdminLevel();
    }

    public void setChapterLevel(String chapterLevel) {
        if (isChapterLevel) {
            setBackModuleLevel(chapterLevel);
            return;
        }
        setGoChapterLevel(chapterLevel);
    }

    public void setGoChapterLevel(String chapterLevel) {
        this.chapterLevel = chapterLevel;
        this.level = level + "/" + chapterLevel;
        this.chapter = new Chapter(chapterLevel);
        setIsChapterLevel();
    }

    public void setBackModuleLevel(String chapterLevel) {
        this.level = adminLevel + "/" + moduleLevel;
        this.chapterLevel = chapterLevel;
        this.chapter = null;
        setIsModuleLevel();
    }

    public String getAccessLevel() {
        String accessLevel;
        if (isAdminLevel()) {
            accessLevel = ADMIN;
        } else if (isModuleLevel()) {
            accessLevel = MODULE;
        } else if (isChapterLevel()) {
            accessLevel = CHAPTER;
        } else {
            accessLevel = "No level";
        }
        return accessLevel;
    }
}