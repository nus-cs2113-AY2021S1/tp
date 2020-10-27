package seedu.quotesify.category;

import org.json.simple.JSONArray;
import seedu.quotesify.book.BookList;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.lists.QuotesifyList;
import seedu.quotesify.quote.QuoteList;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public void updateListsInCategory(Category category) {
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);
        category.setBookList(bookList.filterByCategory(category.getCategoryName()));
        category.setQuoteList(quoteList.filterByCategory(category.getCategoryName()));
    }

    public CategoryList findByKeyword(String keyword) {
        ArrayList<Category> list = (ArrayList<Category>) categories.stream()
                .filter(category -> category.getCategoryName().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return new CategoryList(list);
    }

    public void remove(Category category) {
        categories.remove(category);
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
