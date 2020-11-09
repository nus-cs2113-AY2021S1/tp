package seedu.rex.data.hospital;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    @Test
    void getDateOfBirth_todayDate_todayDate() {
        Patient patient = new Patient("a", "b", LocalDate.now());
        assertEquals(LocalDate.now(), patient.getDateOfBirth());
    }

    @Test
    void setDateOfBirth_todayDate_todayDate() {
        Patient patient = new Patient("a", "b", LocalDate.MIN);
        patient.setDateOfBirth(LocalDate.now());
        assertEquals(LocalDate.now(), patient.getDateOfBirth());
    }

    @Test
    void getName_a_a() {
        Patient patient = new Patient("a", "b", LocalDate.MIN);
        assertEquals("a", patient.getName());
    }

    @Test
    void setName_a_a() {
        Patient patient = new Patient("z", "b", LocalDate.MIN);
        patient.setName("a");
        assertEquals("a", patient.getName());
    }

    @Test
    void getNric_b_b() {
        Patient patient = new Patient("z", "b", LocalDate.MIN);
        assertEquals("b", patient.getNric());
    }

    @Test
    void setNric_z_z() {
        Patient patient = new Patient("z", "b", LocalDate.MIN);
        patient.setNric("z");
        assertEquals("z", patient.getNric());
    }

    @Test
    void testToString_zbTodayDate_zbTodayDate() {
        Patient patient = new Patient("z", "b", LocalDate.now());
        assertEquals("z | b | " + LocalDate.now(), patient.toString());
    }
}