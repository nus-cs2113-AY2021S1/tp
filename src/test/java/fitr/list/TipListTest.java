package fitr.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipListTest {
    ArrayList<String> tips = new ArrayList<>();

    @Test
    public void getTipFromNonEmptyTipList_validTotalNumber_success() {
        tips.add("Let's start exercising!");
        TipList tipList = new TipList(tips);
        assertEquals("Let's start exercising!", tipList.getTip(1));
    }

    @Test
    public void getTipFromEmptyTipList_fail() {
        TipList tipList = new TipList(tips);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tipList.getTip(1); });
    }
}
