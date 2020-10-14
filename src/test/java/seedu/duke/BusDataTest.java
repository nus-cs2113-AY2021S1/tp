package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusDataTest {

    @Test
    void busAtStop_invalidStop_expectEmptyArrayList() {
        String input = "NTU North Spine";
        ArrayList<Bus> busList = BusData.busAtStop(input);
        assertEquals(0, busList.size());
    }

    @Test
    void busAtStop_empty_expectEmptyArrayList() {
        String input = " ";
        ArrayList<Bus> busList = BusData.busAtStop(input);
        assertEquals(0, busList.size());
    }

    @Test
    void busAtStop_validStopRandomCaps_expectAA1() {
        String input = "KeNt RiDge";
        ArrayList<Bus> busList = BusData.busAtStop(input);
        assertEquals(1, busList.size());
        assertEquals("AA1", busList.get(0).getBusNumber());
    }

    @Test
    void busAtStop_validStopExactCaps_expectAA2() {
        String input = "Kent Vale";
        ArrayList<Bus> busList = BusData.busAtStop(input);
        assertEquals(1, busList.size());
        assertEquals("AA2", busList.get(0).getBusNumber());
    }

    @Test
    void busAtStop_validStopAllCaps_expectAA1AA2() {
        String input = "UNIVERSITY TOWN";
        ArrayList<Bus> busList = BusData.busAtStop(input);
        assertEquals(2, busList.size());
        assertEquals("AA1", busList.get(0).getBusNumber());
        assertEquals("AA2", busList.get(1).getBusNumber());
    }
    
}
