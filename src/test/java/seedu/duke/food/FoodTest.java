package seedu.duke.food;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
    private Food testFood;

    public static void main(String[] args){
        Food food = new Food("Kobe Beef", 480,50,40,30);
        System.out.println(food);
    }
}