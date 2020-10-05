package seedu.duke.settings;
import seedu.duke.ui.UI;
public class Settings {
    protected String name;
    protected int dividerNum;

    // Constructor
    public Settings() {
        this.name = "";
        this.dividerNum = 1;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for divider number
    public void setDividerNum(int divider) {
        if(divider < 1 || divider > 4) {
            System.out.println("Enter a valid number 1 -4!");
        } else {
            this.dividerNum = divider;
        }
    }

    // Getter for divider number
    public int getDividerNum() {
        return this.dividerNum;
    }

    // Getter for divider itself
    public String getDivider() {
        return UI.getDivider(this.dividerNum);
    }

    // Getter for name
    public String getName() {
        return this.name;
    }
}
