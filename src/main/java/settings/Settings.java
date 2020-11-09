package settings;

import ui.Printer;

public class Settings {
    private boolean isDisplayingHelpMessages;
    private int colorOption;
    private Printer printer;

    public Settings(Printer printer) {
        this.printer = printer;
        colorOption = 0;
        isDisplayingHelpMessages = true;
    }

    public Settings() {
    }

    public void setDisplayingHelpMessages(boolean isDisplayingHelpMessages, boolean isInit) {
        this.isDisplayingHelpMessages = isDisplayingHelpMessages;
        if (!isInit) {
            printer.printSetDisplayingHelpMessages(isDisplayingHelpMessages);
        }
    }

    public int getColorOption() {
        return colorOption;
    }

    public boolean getDisplayingHelpMessages() {
        return this.isDisplayingHelpMessages;
    }


    public void setColor(int option, boolean isInit) {
        if (option >= 0 && option <= 3) {
            colorOption = option;
        } else {
            colorOption = 0;
        }
        printer.setColor(option, isInit);
    }
}
