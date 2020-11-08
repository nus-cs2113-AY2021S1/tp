package fitr.exercise;

import fitr.exception.InvalidRecommendationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommenderTest {
    @Test
    public void recommenderTest() throws InvalidRecommendationException {
        Recommender recommender = new Recommender();
        assertEquals(4, recommender.recommend("upperbody").getSize());
        assertEquals(4, recommender.recommend("lowebody").getSize());
        assertEquals(4, recommender.recommend("aerobic").getSize());
        assertEquals(4, recommender.recommend("stretch").getSize());
        assertEquals(4, recommender.recommend("").getSize());
    }

    @Test
    public void recommenderParserTest() {
        Recommender recommender = new Recommender();
        assertEquals(1, recommender.recommendParser("aerobic"));
        assertEquals(2, recommender.recommendParser("upperbody"));
        assertEquals(3, recommender.recommendParser("lowerbody"));
        assertEquals(4, recommender.recommendParser("stretch"));
        assertEquals(5, recommender.recommendParser("default"));
    }
}
