package anichan.anime;

import anichan.exception.AniException;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimeTest {

    @Test
    void setReleaseDate_singleDigits_usableDateFormat() throws java.text.ParseException, AniException {
        Anime testAnime = new Anime();
        String[] inputDate = {"2020", "5", "1"};
        testAnime.setReleaseDate(inputDate);
        System.out.println(testAnime.getReleaseDate());
        SimpleDateFormat stringToDate = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = stringToDate.parse("2020-05-01");
        assertEquals(testAnime.getReleaseDate(), testDate);
    }

    @Test
    void setRating_greaterThan100_ratingWillBeZero() {
        Anime testAnime = new Anime();
        int largeRating = 9999;
        testAnime.setRating(largeRating);
        assertEquals(testAnime.getRating(), 0);
    }

    @Test
    void setRating_negativeNumbers_ratingWillBeZero() {
        Anime testAnime = new Anime();
        int negativeRating = -1;
        testAnime.setRating(negativeRating);
        assertEquals(testAnime.getRating(), 0);
    }

    @Test
    void setReleaseDate_invalidDate_throwAniException() {
        Anime testAnime = new Anime();
        String[] invalidDate = { "Not", "Valid", "Date" };
        assertThrows(AniException.class, () -> {
            testAnime.setReleaseDate(invalidDate);
        });
    }
}
