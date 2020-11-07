import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class dineInOrderTest {

    @Test
    void getIsDineIn() {
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

        assertEquals(true, new dineInOrder(canteen2,koreanStall,Arrays.asList(kimchiRamen),
                new Customer("c",10,1600,4),true).getIsDineIn());
    }
}