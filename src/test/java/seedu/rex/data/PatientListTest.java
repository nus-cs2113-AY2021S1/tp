package seedu.rex.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientListTest {

    @Test
    void getSize_onePatient_1() {
        PatientList patientList = new PatientList();
        patientList.addNewPatient("test", "S9911444D", LocalDate.now());
        assertEquals(patientList.getSize(), 1);
    }
}