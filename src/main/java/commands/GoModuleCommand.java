package commands;

import access.Access;
import common.KajiLog;
import manager.admin.ModuleList;
import manager.chapter.Chapter;
import manager.module.Module;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static common.Messages.MESSAGE_INVALID_INDEX_RANGE;
import static common.Messages.MODULE;

/**
 * Access an existing module level from admin level.
 */
public class GoModuleCommand extends GoCommand {
    private static Logger logger = KajiLog.getLogger(GoModuleCommand.class.getName());

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to module level. \n"
            + "Parameters:" + MODULE_PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " 2\n";

    private final int moduleIndex;

    public GoModuleCommand(int moduleIndex) {
        this.moduleIndex = moduleIndex;
    }

    @Override
    public void execute(Ui ui, Access access, Storage storage) throws IOException {
        String result = goModule(access, storage);
        if (result.equals("")) {
            return;
        }
        ui.showToUser(result);
    }

    /**
     * Goes to a module level.
     *
     * @param access temporary access data about user's current access level
     * @param storage file storage and file management of Kaji
     * @return result to be displayed
     * @throws IOException if there is an error writing to the storage file
     */
    private String goModule(Access access, Storage storage) throws IOException {
        assert access.isAdminLevel() : "Not admin level";
        String result = "";
        try {
            ModuleList modules = access.getAdmin().getModules();
            ArrayList<Module> allModules = modules.getAllModules();
            Module module = allModules.get(moduleIndex);
            ArrayList<Chapter> chapters = storage.loadChapter(module.getModuleName());
            if (chapters.size() == 0) {
                result = "This is a new module, you can try to add chapters inside!";
            }
            access.setModuleLevel(module.getModuleName());
            module.setChapters(chapters);
            access.setModule(module);
            logger.info("Going into module: " + module.toString());
            return result;
        } catch (IndexOutOfBoundsException e) {
            result = String.format(MESSAGE_INVALID_INDEX_RANGE, MODULE);
            logger.info(result);
            return result;
        } catch (FileNotFoundException e) {
            result = "The module folder cannot be found.";
            logger.info(result);
            return result;
        }
    }
}
