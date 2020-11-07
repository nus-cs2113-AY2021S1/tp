import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void getDishName() {
        Dish dish = new Dish("Chicken rice",5,"so nice!");
        assertEquals("Chicken rice",dish.getDishName());
    }

    @Test
    void getPrice() {
        Dish dish = new Dish("Chicken rice",5,"so nice!");
        assertEquals(5,dish.getPrice());
    }

    @Test
    void getComment() {
        Dish dish = new Dish("Chicken rice",5,"so nice!");
        assertEquals("so nice!",dish.getComment());
    }

    @Test
    void testToString() {
    }
}