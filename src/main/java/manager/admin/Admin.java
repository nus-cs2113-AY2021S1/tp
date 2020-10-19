package manager.admin;

import manager.history.HistoryList;
import manager.module.Module;

import java.util.ArrayList;

public class Admin {
    protected ModuleList modules;
    protected HistoryList histories;

    //if there is no storage
    public Admin() {
        modules = new ModuleList();
    }

    //if there is storage
    public Admin(ArrayList<Module> modules) {
        this.modules = new ModuleList(modules);
    }

    public ModuleList getModules() {
        return modules;
    }

    public HistoryList getHistories() {
        return histories;
    }
}
