import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class deliveryOrderTest {

    @Test
    void getIsDeliveryOrder() {
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

        assertEquals(true, new deliveryOrder(canteen1,claypotRice,Arrays.asList(curryChickenRice),
                new Customer("r",4,900,6),true).getIsDeliveryOrder());
    }

    @Test
    void getCarrierFee() {
        Dish kimchiFriedRice = new Dish("Kimchi Fried Rice", 3.50,
                "As a korean, I find the taste in my home country.");
        Dish spicyChickenSet = new Dish("Spicy Chicken Set", 5.00,
                "The chicken tastes good but it is too spicy for me.");
        Dish kimchiRamen = new Dish("Kimchi Ramen", 4.00,
                "The kimchi is really delicious as this is my first time to eat this.");
        Stall koreanStall = new Stall("KoreanStall", 4, "35 Students Walk",
                830, 1730, Arrays.asList(1,2,3,4),
                Arrays.asList(kimchiFriedRice, spicyChickenSet, kimchiRamen), 2);

        Dish pepperoni = new Dish("Pepperoni", 8.50,
                "This dish made me feel like diving in the sea.");
        Dish hawaiian = new Dish("Hawaiian", 9.50,
                "I love this hawaii pizza. I want to marry it and eat it at the wedding.");
        Dish aglioOlio = new Dish("Aglio Olio",10.50,
                "The preparation of this dish is easy and quick. Pasta is made in many colors, different shapes" +
                        " and is known by many names in a different country.");
        Stall pizzaHut = new Stall("PizzaHut",5, "35 Students Walk", 900, 1800,
                Arrays.asList(1,2,4,5,6), Arrays.asList(pepperoni, hawaiian, aglioOlio),2);

        Dish mcChicken = new Dish("Mc Chicken", 2,
                "The most classic hamburger in the world!");
        Dish mcNuggets = new Dish("Mc Nuggets", 4,
                "This is really my favorite among all fast food.");
        Dish applePie = new Dish("Apple Pie", 2,
                "this one is good but i prefer the other flavor's pie :)");
        Stall macDonalds = new Stall("MacDonalds",6, "35 Students Walk",
                930, 1830, Arrays.asList(2,3,4,7),
                Arrays.asList(mcChicken, mcNuggets, applePie),2);

        Canteen canteen2 =new Canteen("Canteen 2",Arrays.asList(koreanStall,pizzaHut,macDonalds),
                Arrays.asList(830,830,830,830,900,900,930), Arrays.asList(1730,1830,1830,1830,1800,1800,1830));

        assertEquals(0.3, new deliveryOrder(canteen2,macDonalds,Arrays.asList(mcNuggets),
                new Customer("t",5,1000,2),true).getCarrierFee());
    }

    @Test
    void getDeliveryFee() {
        Dish beefNoodle = new Dish("Beef Noodle",3,
                "The beef is really good, but I think this dish is a bit salty :(");
        Dish tomatoEggNoodle = new Dish("Tomato Egg Noodle", 2.5,
                "Really tasteful!!! After this, I know the reason why most Chinese like tomato and egg.");
        Dish noodlesWithSoyBeanPaste = new Dish ("Noodles with Soy Bean Paste",3,
                "Although my Chinese friends like this very much, but as an Indian I do not like this.");
        Stall noodles = new Stall("Noodles",7,"20 Nanyang Cres",
                830,1930,Arrays.asList(1,2,3,6),
                Arrays.asList(beefNoodle,tomatoEggNoodle,noodlesWithSoyBeanPaste),3);

        Dish sweetAndSourMeet = new Dish("Sweet&Sour Meet",5,
                "The taste of the sweetness and sour are just right.");
        Dish kungPaoTofu = new Dish("Kung Pao Tofu", 4.5,
                "Really good, worth try, but not understand the name of this dish.");
        Dish boiledFish = new Dish("Boiled Fish", 5.5,
                "First try! Really spicy!!");
        Stall siChuan = new Stall("Si Chuan",8,"24 Nanyang Ave",
                900,1830,Arrays.asList(1,2,3,4,5),
                Arrays.asList(sweetAndSourMeet,kungPaoTofu,boiledFish),3);

        Dish spicyChicken = new Dish("Spicy Chicken",3.5,
                "Very crispy and spicy!");
        Dish saltedEggChicken = new Dish("Salted Egg Chicken",4,
                "Really like this taste of chicken with salted egg.");
        Dish mapoTofu = new Dish("Mapo Tofu",2.5,
                "Heard of this dish before, really good.");
        Stall mixedRice = new Stall("Mixed Rice",9,"32 Nanyang Cres",
                800,1900,Arrays.asList(1,2,5,7),
                Arrays.asList(spicyChicken,saltedEggChicken,mapoTofu),3);

        Canteen canteen3 = new Canteen("Canteen 3",Arrays.asList(noodles,siChuan,mixedRice),
                Arrays.asList(800,800,830,900,800,830,800), Arrays.asList(1830,1830,1830,1830,1830,1930,1900));

        assertEquals(2.0, new deliveryOrder(canteen3,siChuan,Arrays.asList(boiledFish ),
                new Customer("a",6,1200,5),true).getDeliveryFee());
    }
}