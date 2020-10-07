package seedu.duke.category;

import org.junit.jupiter.api.Test;
import seedu.duke.author.Author;
import seedu.duke.book.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    public void getBookCategoryTest() {
        Author author = new Author("me");
        Book book = new Book(author, "book1");
        Category category = new Category("romance");
        book.setCategory(category);
        assertEquals(category.getCategoryName(), book.getCategory().getCategoryName());
    }
}
