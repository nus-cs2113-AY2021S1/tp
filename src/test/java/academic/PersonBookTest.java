package academic;

import exceptions.InvalidEmailException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

class PersonBookTest {
    private PersonBook personBook = new PersonBook();
    private ArrayList<Person> listOfPerson = new ArrayList<>();
    private Person testPerson = new Person("Prof Lim", "81234567", "E7654321@u.nus.edu");


    @Test
    void evaluateInput_addPerson_success() throws InvalidEmailException {
        String[] inputVars = {"Prof Lim","81234567","E7654321@u.nus.edu","false"};
        personBook.addPerson(inputVars,listOfPerson);
        assertEquals(Person.printIndividualPerson(testPerson),Person.printIndividualPerson(listOfPerson.get(0)));
    }

    @Test
    void evaluateInput_incorrectAddPerson_invalidEmailExceptionThrown() {
        String[] inputVars = {"Prof Lim","81234567","thisIsAEmail","false"};
        assertThrows(InvalidEmailException.class, () -> {
            personBook.addPerson(inputVars,listOfPerson);
        });
    }

    @Test
    void evaluateInput_printPersonBook_success() throws InvalidEmailException {
        String[] inputVars = {"Prof Lim","81234567","E7654321@u.nus.edu","false"};
        personBook.addPerson(inputVars,listOfPerson);
        String result = personBook.printPersonBook(listOfPerson);
        assertEquals("1.[Prof Lim] [81234567] [E7654321@u.nus.edu]",result);
    }

    @Test
    void evaluateInput_combinePersonDetails_success() {
        String result = personBook.combinePersonDetails(testPerson);
        assertEquals("[Prof Lim] [81234567] [E7654321@u.nus.edu]",result);
    }

    @Test
    void evaluateInput_deletePerson_success() throws InvalidEmailException {
        String[] inputVars = {"Prof Lim","81234567","E7654321@u.nus.edu","false"};
        personBook.addPerson(inputVars,listOfPerson);
        personBook.deletePerson(1,listOfPerson);
        assertTrue(listOfPerson.size() == 0);
    }




}