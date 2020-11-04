package seedu.quotesify.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryListTest {
    private Book book1;
    private BookList bookList;
    private Quote quote1;
    private QuoteList quoteList;
    private Category category1;

    @BeforeEach
    public void setUp() {
        bookList = new BookList();
        quoteList = new QuoteList();

        Author author = new Author("me");
        book1 = new Book(author, "book1");
        bookList.add(book1);

        quote1 = new Quote("quote1");
        quoteList.add(quote1);

        ListManager.addToList(ListManager.BOOK_LIST, bookList);
        ListManager.addToList(ListManager.QUOTE_LIST, quoteList);

        category1 = new Category("romance");
        category1.setBookList(bookList);
        category1.setQuoteList(quoteList);
    }

    @Test
    public void isExistingCategory_invalidName() {
        CategoryList categoryList = new CategoryList();
        categoryList.add(category1);
        assertFalse(categoryList.isExistingCategory("action"));
        assertTrue(categoryList.isExistingCategory("romance"));
    }

    @Test
    public void getCategoryByName_invalidName_throwsQuotesifyException() {
        CategoryList categoryList = new CategoryList();
        categoryList.add(category1);
        Throwable exception = assertThrows(QuotesifyException.class, () -> {
            categoryList.getCategoryByName("action");
        });
        assertEquals("Category [action] does not exist!", exception.getMessage());
    }

    @Test
    public void findByKeyword_invalidKeyword() {
        CategoryList categoryList = new CategoryList();
        categoryList.add(category1);
        assertTrue(categoryList.findByKeyword("act").getList().isEmpty());
        assertFalse(categoryList.findByKeyword("man").getList().isEmpty());
    }
}
