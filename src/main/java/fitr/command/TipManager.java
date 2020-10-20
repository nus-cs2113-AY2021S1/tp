package fitr.command;

import fitr.list.TipList;
import fitr.ui.Ui;

public class TipManager {
    private final TipList tips;

    public TipManager(TipList tips) {
        this.tips = tips;
    }

    public void execute() {
        int totalNumberOfTips = 49;
        Ui.printCustomMessage("Tip of the day!" + System.lineSeparator() + tips.getTip(totalNumberOfTips));
    }

}
