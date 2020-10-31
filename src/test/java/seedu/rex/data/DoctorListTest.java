package seedu.rex.data;

import org.junit.jupiter.api.Test;
import seedu.rex.data.exception.RexException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DoctorListTest {

    @Test
    void deleteDoctor_noDoctor_expectException() {
        DoctorList doctorList = new DoctorList();
        assertThrows(RexException.class, () -> doctorList.deleteDoctor(""));
    }
}