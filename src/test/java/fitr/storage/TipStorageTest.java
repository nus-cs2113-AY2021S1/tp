package fitr.storage;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipStorageTest {
    @Test
    public void loadTipList_validTipData_success() throws Exception {
        TipStorage tipStorage = new TipStorage();
        ArrayList<String> actualGoalList = tipStorage.loadTipList();
        assertEquals("Being dehydrated reduces exercise performance.", actualGoalList.get(22));
    }
}
