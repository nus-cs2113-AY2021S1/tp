package seedu.duke.model.favorite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FavListTest {
    FavList list = new FavList();

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