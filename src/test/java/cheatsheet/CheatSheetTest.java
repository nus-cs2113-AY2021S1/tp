package cheatsheet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheatSheetTest {

    @Test
    void getCheatSheetName() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        assertEquals("Test1", cheatSheet1.getName());
    }

    @Test
    void setCheatSheetName() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        cheatSheet1.setName("Java 11 cheatsheet");
        assertEquals("Java 11 cheatsheet", cheatSheet1.getName());
    }

    @Test
    void getSubject() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        assertEquals("Java 11", cheatSheet1.getSubject());
    }

    @Test
    void setCheatSheetSubject() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        cheatSheet1.setSubject("C++ 11");
        assertEquals("C++ 11", cheatSheet1.getSubject());
    }

    @Test
    void getCheatSheetDetails() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        assertEquals("Java 11 is a Java programming language.", cheatSheet1.getDetails());
    }

    @Test
    void setCheatSheetDetails() {
        CheatSheet cheatSheet1 = new CheatSheet("Test1", "Java 11", "Java 11 is a Java programming language.");
        cheatSheet1.setDetails("Java 11 is an awesome language");
        assertEquals("Java 11 is an awesome language", cheatSheet1.getDetails());
    }
}