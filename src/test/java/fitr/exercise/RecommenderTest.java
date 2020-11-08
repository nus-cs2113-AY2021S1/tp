package fitr.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecommenderTest {
    @Test
    public void recommenderTest() {
        Recommender recommender = new Recommender();
        assertEquals(4, recommender.recommend(1).getSize());
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
