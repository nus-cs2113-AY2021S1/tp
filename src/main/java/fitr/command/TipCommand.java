package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.TipList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

public class TipCommand extends Command {
    private final TipList tips;

    public TipCommand(TipList tips) {
        this.tips = tips;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user) {
        int totalNumberOfTips = 49;
        Ui.printCustomMessage("Tip of the day!" + System.lineSeparator() + tips.getTip(totalNumberOfTips));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
