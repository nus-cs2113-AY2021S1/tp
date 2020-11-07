import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    @Test
    void getName() {
        Customer customer = new Customer("Louis",1,1200,1);
        assertEquals("Louis",customer.getName());
    }

    @Test
    void getID() {
        Customer customer = new Customer("Louis",1,1200,1);
        assertEquals(1,customer.getID());
    }

    @Test
    void getArriveTime() {
        Customer customer = new Customer("Louis",1,1200,1);
        assertEquals(1200,customer.getArriveTime());
    }

    @Test
    void getDayOfWeek() {
        Customer customer = new Customer("Louis",1,1200,1);
        assertEquals(1,customer.getDayOfWeek());
    }

    @Test
    void checkOpenStalls() {
        Customer customer = new Customer("Louis",1,1200,1);
        Dish pattaya = new Dish("Pattaya", 5.5,
                "First try, quite impressive to me!");
        Dish plainThosai = new Dish("Plain Thosai", 2.0,
                "Quite good, bu I like butter one more.");
        Dish prata = new Dish("Prata", 3.0,
                "Recommended by my friends, after this try, I will also recommend this to others.");
        Stall indian = new Stall("Indian", 10,"Jurong West 964",
                1000,2000, Arrays.asList(2,3,5,7),
                Arrays.asList(pattaya,plainThosai,prata),4);

        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130,Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);

        Dish milo = new Dish("Coffee",1.2,
                "Normal milo lol");
        Dish watermelon = new Dish("Watermelon Drink", 1.8,
                "Really like this since eat watermelon is quite mafan.");
        Dish milkshade = new Dish("Milk shade",1.8,
                "A bit too sweet for me.");
        Stall drinks = new Stall("Drinks",12,"Jurong West Ave 5",
                1100,2200,Arrays.asList(3,4,5,6),
                Arrays.asList(milo,watermelon,milkshade),4);

        Canteen canteen4 = new Canteen("Canteen 4",Arrays.asList(indian,waffles,drinks),
                Arrays.asList(730,730,730,730,730,1100,1000), Arrays.asList(2130,2130,2200,2200,2200,2200,2000));
        assertEquals(1,customer.checkOpenStalls(canteen4).size());
        assertEquals(waffles,customer.checkOpenStalls(canteen4).get(0));


    }

    @Test
    void checkOpenCanteens() {
        Customer customer = new Customer("Louis",1,1200,1);
        Dish pattaya = new Dish("Pattaya", 5.5,
                "First try, quite impressive to me!");
        Dish plainThosai = new Dish("Plain Thosai", 2.0,
                "Quite good, bu I like butter one more.");
        Dish prata = new Dish("Prata", 3.0,
                "Recommended by my friends, after this try, I will also recommend this to others.");
        Stall indian = new Stall("Indian", 10,"Jurong West 964",
                1000,2000, Arrays.asList(2,3,5,7),
                Arrays.asList(pattaya,plainThosai,prata),4);

        Dish plainWaffle = new Dish("Plain Waffle", 1.8,
                "Like other flavor more, but this one is cheap :)");
        Dish chocoBanaWaffle = new Dish("Choco Banana Waffle", 2.2,
                "The flavor of the chocolate and banana are so good!!!");
        Dish oreoCheese = new Dish("Oreo Cheese Waffle", 2.2,
                "Very suitable for the cheese lovers.");
        Stall waffles = new Stall("Waffles",11,"North Spine Level 1",
                730,2130,Arrays.asList(1,2,3,4,5),
                Arrays.asList(plainWaffle,chocoBanaWaffle,oreoCheese),4);

        Dish milo = new Dish("Coffee",1.2,
                "Normal milo lol");
        Dish watermelon = new Dish("Watermelon Drink", 1.8,
                "Really like this since eat watermelon is quite mafan.");
        Dish milkshade = new Dish("Milk shade",1.8,
                "A bit too sweet for me.");
        Stall drinks = new Stall("Drinks",12,"Jurong West Ave 5",
                1100,2200,Arrays.asList(3,4,5,6),
                Arrays.asList(milo,watermelon,milkshade),4);

        Canteen canteen4 = new Canteen("Canteen 4",Arrays.asList(indian,waffles,drinks),
                Arrays.asList(730,730,730,730,730,1100,1000), Arrays.asList(2130,2130,2200,2200,2200,2200,2000));
        List<Canteen> canteenList = new ArrayList<>();
        canteenList.add(canteen4);
        assertEquals(1,customer.checkOpenCanteens(canteenList).size());
        assertEquals(canteen4,customer.checkOpenCanteens(canteenList).get(0));
    }


}