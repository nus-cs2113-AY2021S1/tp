package fitr.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddFoodCommandTest {
    @Test
    public void testAddFoodExit() {
        Command addFood = new AddFoodCommand("apple /100 1");
        assertFalse(addFood.isExit());
    }
}
