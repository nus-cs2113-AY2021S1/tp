import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CanteenTest {

    @Test
    void getOpenTime() {
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
        assertEquals(730,canteen4.getOpenTime(1));
        assertEquals(730,canteen4.getOpenTime(2));
        assertEquals(730,canteen4.getOpenTime(3));
        assertEquals(730,canteen4.getOpenTime(4));
        assertEquals(730,canteen4.getOpenTime(5));
        assertEquals(1100,canteen4.getOpenTime(6));
        assertEquals(1000,canteen4.getOpenTime(7));

    }

    @Test
    void getCloseTime() {
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
        assertEquals(2130,canteen4.getCloseTime(1));
        assertEquals(2130,canteen4.getCloseTime(2));
        assertEquals(2200,canteen4.getCloseTime(3));
        assertEquals(2200,canteen4.getCloseTime(4));
        assertEquals(2200,canteen4.getCloseTime(5));
        assertEquals(2200,canteen4.getCloseTime(6));
        assertEquals(2000,canteen4.getCloseTime(7));
    }

    @Test
    void getStallList() {
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
        assertEquals(indian,canteen4.getStallList().get(0));
        assertEquals(waffles,canteen4.getStallList().get(1));
        assertEquals(drinks,canteen4.getStallList().get(2));

    }

    @Test
    void isOpen() {
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
        assertEquals(true,canteen4.isOpen(1,1200));
        assertEquals(false,canteen4.isOpen(1,0000));
        assertEquals(true,canteen4.isOpen(5,1500));
    }

    @Test
    void getCanteenName() {
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
        assertEquals("Canteen 4",canteen4.getCanteenName());

    }
}