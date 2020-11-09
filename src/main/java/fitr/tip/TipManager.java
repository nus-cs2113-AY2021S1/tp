package fitr.tip;

import fitr.list.TipList;
import fitr.ui.Ui;

public class TipManager {
    private final TipList tips;

    public TipManager(TipList tips) {
        this.tips = tips;
    }

    public void execute() {
        int totalNumberOfTips = 99;
        Ui.printMessageInYellow("Tip of the day!" + System.lineSeparator() + tips.getTip(totalNumberOfTips));
    }

}
