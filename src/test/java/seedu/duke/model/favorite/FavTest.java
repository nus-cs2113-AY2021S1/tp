package seedu.duke.model.favorite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FavTest {
    Fav fav = new Fav("/liststops","List all stops");

    @Test
    void equals_favSame_expectTrue() {
        Fav userFav = new Fav("/liststops","List all stops");
        assertTrue(fav.equals(userFav,1));
    }

    @Test
    void equals_favDifferent_expectFalse() {
        Fav userFav = new Fav("/route aa2","/liststops");
        assertFalse(fav.equals(userFav,1));
    }
}