package manager.admin;

import manager.module.Module;

import java.util.ArrayList;

public class Admin {
    protected ModuleList modules;

    //if there is no storage
    public Admin() {
        modules = new ModuleList();
    }

    //if there is storage
    public Admin(ModuleList modules) {
        this.modules = modules;
    }

    public ModuleList getModules() {
        return modules;
    }
}
