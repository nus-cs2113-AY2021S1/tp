package seedu.zoomaster.slot;

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

    public static SlotContainer sortSlotsByTime(SlotContainer slotContainer) {
        ArrayList<Slot> thisDaySlots = slotContainer.getSlotList();
        ArrayList<String> thisDayModuleCodesList = slotContainer.getModuleCodesList();
        ArrayList<Slot> sortedThisDaySlots = new ArrayList<>();
        ArrayList<String> sortedThisDayModuleCodesList = new ArrayList<>();
        int indexEarliestTimeSlot;
        Slot earliestTimeSlot;

        while (thisDaySlots.size() != 0) {
            earliestTimeSlot = thisDaySlots.get(0);
            indexEarliestTimeSlot = 0;
            for (int i = 1; i < thisDaySlots.size(); i++) {
                if (earliestTimeSlot.getStartTime().isAfter(thisDaySlots.get(i).getStartTime())) {
                    earliestTimeSlot = thisDaySlots.get(i);
                    indexEarliestTimeSlot = i;
                }
            }
            sortedThisDaySlots.add(thisDaySlots.get(indexEarliestTimeSlot));
            sortedThisDayModuleCodesList.add(thisDayModuleCodesList.get(indexEarliestTimeSlot));
            thisDaySlots.remove(indexEarliestTimeSlot);
            thisDayModuleCodesList.remove(indexEarliestTimeSlot);
        }


        return new SlotContainer(sortedThisDaySlots, sortedThisDayModuleCodesList);
    }
}
