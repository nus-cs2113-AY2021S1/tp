package fitr.exercise;

import fitr.exception.InvalidRecommendationException;
import fitr.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecommenderTest {
    @Test
    public void recommenderTest() {
        Recommender recommender = new Recommender();
        try {
            assertEquals(4, recommender.recommend("upperbody").getSize());
            assertEquals(4, recommender.recommend("lowerbody").getSize());
            assertEquals(4, recommender.recommend("aerobic").getSize());
            assertEquals(4, recommender.recommend("stretch").getSize());
            assertEquals(4, recommender.recommend("").getSize());
        } catch (InvalidRecommendationException e) {
            Ui.printCustomError("This should never happen");
        }
    }

    @Test
    public void recommenderParserTest() {
        Recommender recommender = new Recommender();
        assertEquals(0, recommender.recommendParser(""));
        assertEquals(1, recommender.recommendParser("aerobic"));
        assertEquals(2, recommender.recommendParser("upperbody"));
        assertEquals(3, recommender.recommendParser("lowerbody"));
        assertEquals(4, recommender.recommendParser("stretch"));
        assertEquals(5, recommender.recommendParser("default"));
    }

}
