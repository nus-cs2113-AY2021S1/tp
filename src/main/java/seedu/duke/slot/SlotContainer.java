package seedu.duke.slot;

import java.util.ArrayList;

public class SlotContainer {
    private ArrayList<Slot> thisDaySlots;
    private ArrayList<String> thisDayModuleCodesList;

    public SlotContainer(ArrayList<Slot> slotsListInput, ArrayList<String> moduleCodesListInput) {
        this.thisDaySlots = slotsListInput;
        this.thisDayModuleCodesList = moduleCodesListInput;
    }

    public ArrayList<Slot> getSlotList() {
        return thisDaySlots;
    }

    public void setToSlotList(ArrayList<Slot> slotsListInput) {
        thisDaySlots = slotsListInput;
    }

    public ArrayList<String> getModuleCodesList() {
        return thisDayModuleCodesList;
    }

    public void setModuleCodesList(ArrayList<String> moduleCodeListInput) {
        thisDayModuleCodesList = moduleCodeListInput;
    }
}
