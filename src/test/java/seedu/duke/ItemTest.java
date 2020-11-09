package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author pinfang
public class ItemTest {
    private LocalDate date = LocalDate.now();
    private Item rice = new Item("rice", "SGD", 2);
    private Item food = new Item("food", "SGD", 2, "Food");

    @Test
    public void testStringConversion() {
        String expectedString = date.toString() + " [Others] rice SGD 2.00";
        assertEquals(expectedString, rice.toString());
    }

    @Test
    public void editAmount() {
        String expectedString = date.toString() + " [Others] rice SGD 5.00";
        rice.editAmount(5);
        assertEquals(expectedString, rice.toString());
    }

    @Test
    public void editDescription() {
        String expectedString = date.toString() + " [Others] chicken rice SGD 2.00";
        rice.editDescription("chicken rice");
        assertEquals(expectedString, rice.toString());
    }

    @Test
    public void editCategory() {
        String expectedString = date.toString() + " [Food] rice SGD 2.00";
        rice.editCategory("Food");
        assertEquals(expectedString, rice.toString());
    }

    @Test
    public void editSymbol() {
        String expectedString = date.toString() + " [Others] rice USD 2.00";
        rice.editSymbol("USD");
        assertEquals(expectedString, rice.toString());
    }

    @Test
    public void getAmount() {
        Double expectedAmount = 2.0000;
        assertEquals(expectedAmount, rice.getAmount());
    }

    @Test
    public void getDescription() {
        String expectedDescription = "rice";
        assertEquals(expectedDescription, rice.getDescription());
    }

    @Test
    public void getSymbol() {
        String expectedSymbol = "SGD";
        assertEquals(expectedSymbol, rice.getSymbol());
    }

    @Test
    public void getDate() {
        String expectedString = date.toString();
        assertEquals(expectedString, rice.getDate());
    }

    @Test
    public void getCategory() {
        String expectedString = "Food";
        assertEquals(expectedString, food.getCategory());
    }
}
