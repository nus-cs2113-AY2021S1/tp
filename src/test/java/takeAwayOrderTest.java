import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class takeAwayOrderTest {

    @Test
    void getIsTakeAway() {
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

        assertEquals(true, new takeAwayOrder(canteen4,drinks,Arrays.asList(milo),
                new Customer("u",9,800,4),true).getIsTakeAway());
    }

    @Test
    void getCarrierFee() {
        Dish porkChop = new Dish("Pork Chop",4,
                "Sugar, spice, and everything nice.");
        Dish fishNChip = new Dish("Fish & Chip",4,
                "Very crispy, makes me feel the happiness of eating!");
        Dish chickenCutLet = new Dish("Chicken Cutlet",3.5,
                "Not very good tasting, I think the meat is a bit hard to eat.");
        Stall westernStall = new Stall("Western Stall", 1,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                900,1800, Arrays.asList(1,2,3,5,6,7),
                Arrays.asList(porkChop,fishNChip,chickenCutLet),1);

        Dish espresso = new Dish("Espresso",5,
                "The coffee smells good but it is a bit bitter for me.");
        Dish caramelFrappuccino = new Dish("Caramel Frappuccino",7.5,
                "I don't taste any coffee. Extremely sweet");
        Dish signatureHotChocolateckenCutLet = new Dish("Signature Hot Chocolate",6,
                "The cake is accompanied by a slight bitterness from the dark chocolate " +
                        "that lingers at the back of your throat. It is super addictive for a chocolate lover like myself.");
        Stall starbucks = new Stall("Starbucks", 2,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                900,1830, Arrays.asList(2,3,4,5,6,7),
                Arrays.asList(espresso,caramelFrappuccino,signatureHotChocolateckenCutLet),1);

        Dish sweetNSourPorkRice = new Dish("Sweet & Sour Pork Rice",4.5,
                "The meat is good taste, but the rice is not very good");
        Dish curryChickenRice = new Dish("Curry Chicken Rice",3.5,
                "The curry is sooooo delicious!!!!");
        Dish friedRiceWithBeef = new Dish("Fried Rice with Beef",5,
                "I think there is too much oil in this dish");
        Stall claypotRice = new Stall("ClaypotRice", 3,
                "Hall of Residence 1, 21 Nanyang Cir, Singapore 639778",
                800,2000, Arrays.asList(3,4,5,6,7),
                Arrays.asList(sweetNSourPorkRice,curryChickenRice,friedRiceWithBeef),1);

        Canteen canteen1 = new Canteen("Canteen 1", Arrays.asList(westernStall,starbucks,claypotRice),
                Arrays.asList(900,900,800,800,800,800,800),Arrays.asList(1800,1830,2000,2000,2000,2000,2000));

        assertEquals(0.3, new takeAwayOrder(canteen1,westernStall,Arrays.asList(fishNChip),
                new Customer("d",11,1500,7),true).getCarrierFee());
    }
}