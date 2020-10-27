package seedu.duke.model.favorite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FavListTest {

    @BeforeAll
    public static void makeList() {
        new FavList();
        FavList.addFav(new Fav("/route PGP /to University Hall", "Awesome place"));
        FavList.addFav(new Fav("/bus Kent Ridge MRT Station", "NTUC here"));
    }

    @Test
    void changeDesc_indexOutOfBounds_expectException() {
        assertThrows(CustomException.class, FavListTest::performCheck_indexOutOfBounds);
    }

    static void performCheck_indexOutOfBounds() throws CustomException {
        int index = 3;
        String description = "Hello";
        String oldDesc = FavList.changeDesc(index, description);
    }

    @Test
    void changeDesc_sameDesc_expectException() {
        assertThrows(CustomException.class, FavListTest::performCheck_sameDesc);
    }

    static void performCheck_sameDesc() throws CustomException {
        int index = 2;
        String description = "NTUC here";
        String oldDesc = FavList.changeDesc(index, description);
    }

    @Test
    void changeDesc_validIndexAndDesc_success() throws CustomException {
        int index = 1;
        String newDesc = "Good stuff!";
        FavList.changeDesc(index, newDesc);
        assertEquals(newDesc, FavList.getList().get(0).getDesc());
    }

    @Test
    void contains_duplicateFav_expectTrue(){
        Fav fav = new Fav("/allbus","List out all buses in nus");
        FavList.addFav(fav);
        assertTrue(FavList.contains(fav));
    }

    @Test
    void contains_newFav_expectFalse(){
        Fav fav1 = new Fav("/allbus","List out all buses in nus");
        FavList.addFav(fav1);
        Fav fav2 = new Fav("/liststops","/liststops");
        assertFalse(FavList.contains(fav2));
    }
}