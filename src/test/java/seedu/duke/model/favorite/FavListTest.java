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
        int index = 3;
        String description = "Hello";
        try {
            FavList.changeDesc(index, description);
        } catch (CustomException error) {
            assertEquals("Sorry, that isn't the index of any command in the list.", error.toString());
        }
    }

    @Test
    void changeDesc_sameDesc_expectException() {
        int index = 2;
        String description = "NTUC here";
        try {
            FavList.changeDesc(index, description);
        } catch (CustomException error) {
            assertEquals("No change needed! You already have that description for your favourite command.",
                    error.toString());
        }
    }

    @Test
    void changeDesc_validIndexAndDesc_success() throws CustomException {
        int index = 1;
        String newDesc = "Good stuff!";
        FavList.changeDesc(index, newDesc);
        assertEquals(newDesc, FavList.getList().get(0).getDesc());
    }

    @Test
    void contains_duplicateFav_expectTrue() {
        Fav fav = new Fav("/allbus","List out all buses in nus");
        FavList.addFav(fav);
        assertTrue(FavList.contains(fav));
    }

    @Test
    void contains_newFav_expectFalse() {
        Fav fav1 = new Fav("/allbus","List out all buses in nus");
        FavList.addFav(fav1);
        Fav fav2 = new Fav("/liststops","/liststops");
        assertFalse(FavList.contains(fav2));
    }
}