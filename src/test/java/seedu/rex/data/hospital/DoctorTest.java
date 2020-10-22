package seedu.rex.data.hospital;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTest {

    @Test
    void getName_sam_sam() {
        Doctor sam = new Doctor("Sam");
        assertEquals("Sam", sam.getName());
    }

    @Test
    void testToString_sam_sam() {
        Doctor sam = new Doctor("Sam");
        assertEquals("Sam", sam.toString());
    }
}