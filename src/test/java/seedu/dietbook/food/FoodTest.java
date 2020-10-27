package seedu.dietbook.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodTest {
    private Food testFood;

    public static void main(String[] args) {
        Food food = new Food("Kobe Beef", 480,50,40,30);
        System.out.println(food);
    }
}