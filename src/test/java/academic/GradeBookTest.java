package academic;

import exceptions.RepeatedGradeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GradeBookTest {
    private GradeBook gradeBook = new GradeBook();
    private ArrayList<Grade> currentGrade = new ArrayList<>();
    private Grade testGrade = new Grade("CS2101",4,"A-");



    @Test
    void evaluateInput_addGrade_success() throws RepeatedGradeException {
        String[] inputVars = {"CS2101","4","A-","false","false"};
        gradeBook.addGrade(inputVars,currentGrade);
        assertEquals(Grade.printIndividualGrade(testGrade),Grade.printIndividualGrade(currentGrade.get(0)));
    }

    @Test
    void evaluateInput_printCap_success() throws RepeatedGradeException {
        String[] inputVars = {"CS2101","4","A-","false","false"};
        gradeBook.addGrade(inputVars,currentGrade);
        String result = gradeBook.printCap(currentGrade);
        assertEquals("Current CAP is 4.5.",result);
    }

    @Test
    void evaluateInput_printListOfGrades_success() throws RepeatedGradeException {
        String[] inputVars = {"CS2101","4","A-","false","false"};
        gradeBook.addGrade(inputVars,currentGrade);
        String result = gradeBook.printListOfGrades(currentGrade);
        assertEquals("1.[CS2101] [4MC] [A-]",result);
    }

    @Test
    void evaluateInput_combineGradeDetails_success() {
        String result = gradeBook.combineGradeDetails(testGrade);
        assertEquals("[CS2101] [4MC] [A-]",result);
    }

    @Test
    void evaluateInput_deleteGrade_success() throws RepeatedGradeException {
        String[] inputVars = {"CS2101","4","A-","false","false"};
        gradeBook.addGrade(inputVars,currentGrade);
        gradeBook.deleteGrade(1,currentGrade);
        assertTrue(currentGrade.size() == 0);
    }

}

