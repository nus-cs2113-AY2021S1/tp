package workout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkOutRecordTest {

    @Test
    void testToString() {
        WorkOutRecord wr = new WorkOutRecord(BodyPart.LEG, "haha");
        assertEquals("LEG: haha", wr.toString());
    }
}