package seedu.financeit.common;

public abstract class Item extends ParamHandler {

    protected int index = -1;

    public Item() {
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public abstract String getName();
}
