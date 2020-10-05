package seedu.duke.level;

import seedu.duke.level.Module;

import java.util.ArrayList;

public class Admin {
    protected ArrayList<Module> modules;
    protected int moduleAmount = 0;

    public Admin() {
        modules = new ArrayList<Module>();
        moduleAmount = 0;
    }

    public void addModule(Module module){
        modules.add(module);
       // modules.get(moduleAmount).doneAddModule();
        moduleAmount++;
    }

    public void addChapter(String filter, String moduleLevel) {
        int index = modules.indexOf(moduleLevel);
        modules.get(index).add(new Chapter(filter));
    }
}
