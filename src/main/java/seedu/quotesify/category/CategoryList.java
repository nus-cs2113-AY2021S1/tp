package seedu.quotesify.category;

import org.json.simple.JSONArray;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.lists.QuotesifyList;
import seedu.quotesify.quote.QuoteList;

import java.util.ArrayList;

public class CategoryList extends QuotesifyList<Category> {
    private ArrayList<Category> categories = super.getList();

    public CategoryList() {
        super(new ArrayList<>());
    }

    public CategoryList(ArrayList<Category> categories) {
        super(categories);
    }

    public boolean isExistingCategory(String name) {
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Category getCategoryByName(String name) throws QuotesifyException {
        for (Category category : categories) {
            if (category.getCategoryName().equals(name)) {
                return category;
            }
        }
        throw new QuotesifyException("Category [" + name + "] does not exist!");
    }

    public void updateListsInAllCategories() {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);

        for (Category category : categories) {
            category.setBookList(bookList.filterByCategory(category.getCategoryName()));
            category.setQuoteList(quoteList.filterByCategory(category.getCategoryName()));
        }
    }

    public void updateListInCategory(Category category) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        category.setBookList(bookList.filterByCategory(category.getCategoryName()));
        category.setQuoteList(quoteList.filterByCategory(category.getCategoryName()));
    }

    public void updateCategoryInBooksAndQuotes(String oldCategory, String newCategory) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        bookList.getList().forEach(book -> {
                    if (book.getCategories().contains(oldCategory)) {
                        book.getCategories().remove(oldCategory);
                        book.getCategories().add(newCategory);
                    }
                });

        quoteList.getList().forEach(quote -> {
                    if (quote.getCategories().contains(oldCategory)) {
                        quote.getCategories().remove(oldCategory);
                        quote.getCategories().add(newCategory);
                    }
                });
    }

    @Override
    public void add(Category category) {
        categories.add(category);
    }

    @Override
    public void delete(int index) {
        categories.remove(index);
    }

    @Override
    public String toString() {
        String list = "";
        int index = 0;
        for (Category category : categories) {
            list += String.format("%d. %s\n", ++index, category.toString());
        }
        return list;
    }

    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Category category : categories) {
            list.add(category.toJson());
        }
        return list;
    }
}
