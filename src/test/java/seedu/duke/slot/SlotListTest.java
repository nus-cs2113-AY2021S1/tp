package seedu.duke.slot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;


class SlotListTest {
//
//    @Test
//    void testAddSlot() {
//        SlotList slots = new SlotList();
//        LocalTime startTime = LocalTime.parse("12:00");
//        LocalTime endTime = LocalTime.parse("14:00");
//        String day = "mon";
//        String title = "CS2101";
//        Slot slot = new Slot(startTime, endTime, day, title);
//        slots.addSlot(slot);
//        assertEquals(slot, slots.getSlot(0));
//    }
//
//    @Test
//    void testDeleteSlot() {
//        SlotList slots = new SlotList();
//        Slot slot0 = new Slot(LocalTime.parse("16:00"), LocalTime.parse("18:00"), "fri", "CS2113T");
//        Slot slot1 = new Slot(LocalTime.parse("14:00"), LocalTime.parse("15:00"), "mon", "CG2028");
//        Slot slot2 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("12:00"), "tue", "CS2101");
//        slots.addSlot(slot0);
//        slots.addSlot(slot1);
//        slots.addSlot(slot2);
//
//        slots.deleteSlot(slots.getSlot(1));
//        assertEquals(slot2, slots.getSlot(1));
//
//        slots.deleteSlot(slots.getSlot(0));
//        assertEquals(slot2, slots.getSlot(0));
//    }
//
//    @Test
//    void testGetSize() {
//        SlotList slots = new SlotList();
//        Slot slot0 = new Slot(LocalTime.parse("16:00"), LocalTime.parse("18:00"), "fri", "CS2113T");
//        Slot slot1 = new Slot(LocalTime.parse("14:00"), LocalTime.parse("15:00"), "mon", "CG2028");
//        slots.addSlot(slot0);
//        slots.addSlot(slot1);
//        assertEquals(2, slots.getSize());
//    }
//
//    @Test
//    void testGetSlot() {
//        SlotList slots = new SlotList();
//        Slot slot0 = new Slot(LocalTime.parse("16:00"), LocalTime.parse("18:00"), "fri", "CS2113T");
//        Slot slot1 = new Slot(LocalTime.parse("14:00"), LocalTime.parse("15:00"), "mon", "CG2028");
//        slots.addSlot(slot0);
//        slots.addSlot(slot1);
//        assertEquals(slot0, slots.getSlot(0));
//        assertEquals(slot1, slots.getSlot(1));
//    }
//
//    @Test
//    void testGetSlotList() {
//        Slot slot0 = new Slot(LocalTime.parse("16:00"), LocalTime.parse("18:00"), "fri", "CS2113T");
//        Slot slot1 = new Slot(LocalTime.parse("14:00"), LocalTime.parse("15:00"), "mon", "CG2028");
//
//        SlotList slots = new SlotList();
//        slots.addSlot(slot0);
//        slots.addSlot(slot1);
//
//        ArrayList<Slot> slot = new ArrayList<>();
//        slot.add(slot0);
//        slot.add(slot1);
//        assertEquals(slot, slots.getSlotList());
//    }

}