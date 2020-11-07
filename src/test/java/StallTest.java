import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StallTest {

    @Test
    void getOpenTime() {
        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130, Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);
        assertEquals(730,waffles.getOpenTime());
    }

    @Test
    void getCloseTime() {
        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130, Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);
        assertEquals(2130,waffles.getCloseTime());

    }

    @Test
    void getDish() {
        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130, Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);
        assertEquals(plainWaffle,waffles.getDish().get(0));
        assertEquals(chocoBanaWaffle,waffles.getDish().get(1));
        assertEquals(oreoCheese,waffles.getDish().get(2));
    }



    @Test
    void isOpen() {
        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130, Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);
        assertEquals(true,waffles.isOpen(2,1200));
    }


}