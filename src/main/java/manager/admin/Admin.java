package manager.admin;

import manager.module.Module;

import java.util.ArrayList;

public class Admin {
    protected ArrayList<Module> modules;
    protected int moduleAmount = 0;

    //if there is no storage
    public Admin() {
        modules = new ArrayList<>();
        moduleAmount = 0;
    }

    //if there is storage
    public Admin(ArrayList<Module> modules) {
        this.modules = new ArrayList<>(modules);
        moduleAmount = modules.size();
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void add(Module module) {
        modules.add(module);
        modules.get(moduleAmount).doneAddModule();
        moduleAmount++;
    }
}
