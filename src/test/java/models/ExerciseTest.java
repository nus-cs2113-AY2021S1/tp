package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {

    @Test
    void testToString() {
        Exercise exerciseActual = new Exercise("benchpress", 432, 423.34);
        String expected = "benchpress, Repetitions:432, Weight=423.34";
        assertEquals(expected, exerciseActual.toString());
    }

    @Test
    void getDescription() {
        Exercise exerciseActual = new Exercise("benchpress", 432, 423.34);
        String expected = "benchpress";
        assertEquals(expected, exerciseActual.getDescription());
    }

    @Test
    void getRepetitions() {
        Exercise exerciseActual = new Exercise("benchpress", 432, 423.34);
        String expected = "432";
        assertEquals(expected, exerciseActual.getRepetitions());
    }

    @Test
    void getWeight() {
        Exercise exerciseActual = new Exercise("benchpress", 432, 423.34);
        String expected = "423.34";
        assertEquals(expected, exerciseActual.getWeight());
    }
}