package seedu.duke.command;

import seedu.duke.level.Chapter;
import seedu.duke.tool.Access;
import seedu.duke.tool.Storage;
import seedu.duke.tool.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class goChapterCommand extends Command{

    public goChapterCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(Access access, Ui ui, Storage storage) {
        String filter = fullCommand.replace("goChapter ", "");
        boolean isLevelExist = false;
        ArrayList<Chapter> chapters = access.getModule().getChapter();
        for (Chapter chapter : chapters) {
            if(filter.equalsIgnoreCase(chapter.getChapter())) {
                access.setModuleLevel(filter);
                isLevelExist = true;
                try {
                    Chapter newChapter = new Chapter(chapter.getChapter(), storage.loadCard(access.getModuleLevel(), chapter.getChapter()));
                    access.setChapter(newChapter);
                } catch (FileNotFoundException e) {
                    Chapter newChapter = new Chapter(chapter.getChapter());
                    access.setChapter(newChapter);
                    System.out.println("Hihi, seems like it is a new module, you can try to add chapter inside!");
                }
                break;
            }
        }
        if (isLevelExist == false) {
            System.out.println("Sorry, I cannot find this module, please add this module first");
        }
    }
}
