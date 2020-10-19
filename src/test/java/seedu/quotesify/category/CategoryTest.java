package seedu.quotesify.category;

import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    public void getBookCategoryTest() {
        Author author = new Author("me");
        Book book = new Book(author, "book1");
        ArrayList<String> categories = new ArrayList<>();
        categories.add("romance");
        book.setCategories(categories);
        Category category = new Category("romance");
        assertEquals(category.getCategoryName(), book.getCategories().get(0));
    }
}
