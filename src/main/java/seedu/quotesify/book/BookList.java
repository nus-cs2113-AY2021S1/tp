package seedu.quotesify.book;

import org.json.simple.JSONArray;
import seedu.quotesify.author.Author;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;
import java.util.stream.Collectors;

//@@author chloesyy

/**
 * Represents a BookList of books.
 */
public class BookList extends QuotesifyList<Book> {
    private ArrayList<Book> books = super.getList();
    private BookTitleComparator comparator = new BookTitleComparator();

    /**
     * Constructor for empty BookList.
     */
    public BookList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor for BookList with books.
     * @param books Books to be added into BookList.
     */
    public BookList(ArrayList<Book> books) {
        super(books);
    }

    /**
     * Adds a new book to the BookList and sorts the BookList in alphabetical order.
     *
     * @param newBook New book to be added to the BookList.
     */
    @Override
    public void add(Book newBook) {
        books.add(newBook);
        books.sort(comparator);
    }

    /**
     * Deletes a book from the BookList, specified by its index.
     *
     * @param index Item index in the list.
     */
    @Override
    public void delete(int index) {
        books.remove(index);
    }

    /**
     * Returns true if the BookList is empty.
     *
     * @return True if Booklist is empty.
     */
    public boolean isEmpty() {
        return books.isEmpty();
    }

    /**
     * Returns index of specified book in the BookList.
     *
     * @param book Book to get index.
     * @return Index of book.
     */
    public int getIndex(Book book) {
        return books.indexOf(book);
    }

    /**
     * Returns book for a specified index.
     *
     * @param index Index of book.
     * @return Book with the specified index.
     */
    public Book getBook(int index) {
        return books.get(index);
    }

    /**
     * Returns the number of books the BookList has.
     *
     * @return Number of books in the BookList.
     */
    public int getSize() {
        return books.size();
    }

    /**
     * Sorts the BookList in alphabetical order.
     */
    public void sort() {
        books.sort(comparator);
    }

    /**
     * Converts the BookList into a String.
     *
     * @return String of books in BookList.
     */
    @Override
    public String toString() {
        String booksToReturn = "";

        for (Book book : books) {
            booksToReturn += book.toString() + System.lineSeparator();
        }

        return booksToReturn;
    }

    /**
     * Converts the BookList into a String with corresponding indexing.
     *
     * @return String of books with their corresponding index in BookList.
     */
    public String toStringWithIndex() {
        String booksToReturn = "";

        for (Book book : books) {
            booksToReturn += getIndex(book) + 1 + ". " + book.getStatusIcon()
                    + book.toString() + System.lineSeparator();
        }

        return booksToReturn;
    }

    /**
     * Ensures there are no books in the BookList with the same title and author.
     *
     * @param title Title of book.
     * @param authorName Author name of book.
     * @throws QuotesifyException If there is a similar book.
     */
    public void ensureNoSimilarBooks(String title, String authorName) throws QuotesifyException {
        ArrayList<Book> similarBooks = find(title, authorName);

        if (!similarBooks.isEmpty()) {
            throw new QuotesifyException(Command.ERROR_BOOK_ALREADY_EXISTS);
        }
    }

    /**
     * Finds a list of books in the BookList with the same author and title.
     *
     * @param title Title of book.
     * @param authorName Author name of book.
     * @return List of books with the same author and title.
     */
    public ArrayList<Book> find(String title, String authorName) {
        assert !title.isEmpty() || !authorName.isEmpty();
        String lowerCaseTitle = title.toLowerCase();
        String lowerCaseAuthor = authorName.toLowerCase();

        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    Author bookAuthor = book.getAuthor();
                    String bookAuthorName = bookAuthor.getName();
                    String bookTitle = book.getTitle();
                    return bookAuthorName.toLowerCase().equals(lowerCaseAuthor)
                            && bookTitle.toLowerCase().equals(lowerCaseTitle);
                }).collect(Collectors.toList());

        return filteredBooks;
    }

    /**
     * Finds a BookList of books containing a keyword.
     *
     * @param keyword Keyword used to find the books.
     * @return BookList of books containing keyword.
     */
    public BookList findByKeyword(String keyword) {
        assert !keyword.isEmpty();
        String lowerCaseKeyword = keyword.toLowerCase();

        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    String authorName = book.getAuthor().getName();
                    String bookTitle = book.getTitle();
                    return authorName.toLowerCase().contains(lowerCaseKeyword)
                            || bookTitle.toLowerCase().contains(lowerCaseKeyword);
                }).collect(Collectors.toList());

        return new BookList(filteredBooks);
    }

    /**
     * Finds a book by its title.
     *
     * @param title Title of book.
     * @return Book with the specified title.
     */
    public Book findByTitle(String title) {
        assert !title.isEmpty();
        String lowerCaseTitle = title.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().equals(lowerCaseTitle)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Finds a book by a specified number.
     *
     * @param num Index of book + 1
     * @return Book corresponding to specified number.
     */
    public Book findByNum(int num) {
        int index = num - 1;
        if (num <= books.size()) {
            return books.get(index);
        } else {
            return null;
        }
    }

    /**
     * Finds an existing author.
     *
     * @param authorName Author to be found.
     * @return Author corresponding to authorName.
     */
    public Author findExistingAuthor(String authorName) {
        BookList filteredBooks = filterByAuthor(authorName);

        if (filteredBooks.isEmpty()) {
            return null;
        }
        Author author = filteredBooks.getBook(0).getAuthor();

        return author;
    }

    /**
     * Filters the BookList by its completion.
     *
     * @param isDone True if book is completed, false otherwise.
     * @return BookList of books corresponding to isDone boolean.
     */
    public BookList filterDone(boolean isDone) {
        ArrayList<Book> filteredBooks;

        if (isDone) {
            filteredBooks = (ArrayList<Book>) books.stream()
                    .filter(book -> book.isDone())
                    .collect(Collectors.toList());
        } else {
            filteredBooks = (ArrayList<Book>) books.stream()
                    .filter(book -> !book.isDone())
                    .collect(Collectors.toList());
        }

        return new BookList(filteredBooks);
    }

    /**
     * Filters the BookList by its author.
     *
     * @param authorName Author name to be filtered by.
     * @return BookList of books with the same author.
     */
    public BookList filterByAuthor(String authorName) {
        String lowerCaseAuthor = authorName.toLowerCase();

        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    Author bookAuthor = book.getAuthor();
                    String bookAuthorName = bookAuthor.getName();
                    return bookAuthorName.toLowerCase().equals(lowerCaseAuthor);
                }).collect(Collectors.toList());

        return new BookList(filteredBooks);
    }

    /**
     * Filters the BookList by its Categories.
     *
     * @param categoryName Category to be filtered by.
     * @return BookList of books in that category.
     */
    public BookList filterByCategory(String categoryName) {
        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    ArrayList<String> categories = book.getCategories();
                    return categories.contains(categoryName);
                }).collect(Collectors.toList());
        return new BookList(filteredBooks);
    }

    /**
     * Converts the BookList into a JSONArray.
     *
     * @return JSONArray of BookList.
     */
    @Override
    public JSONArray toJsonArray() {
        JSONArray list = new JSONArray();
        for (Book book : books) {
            list.add(book.toJson());
        }
        return list;
    }
}
