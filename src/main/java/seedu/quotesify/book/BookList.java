package seedu.quotesify.book;

import seedu.quotesify.author.Author;
import seedu.quotesify.lists.QuotesifyList;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookList extends QuotesifyList<Book> {
    private ArrayList<Book> books = super.getList();

    public BookList() {
        super(new ArrayList<>());
    }

    public BookList(ArrayList<Book> books) {
        super(books);
    }

    @Override
    public void add(Book newBook) {
        books.add(newBook);
    }

    @Override
    public void delete(int index) {
        books.remove(index);
    }

    public void deleteByBook(Book book) {
        books.remove(book);
    }

    public boolean isEmpty() {
        return books.isEmpty();
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    @Override
    public String toString() {
        String booksToReturn = "";

        for (Book book : books) {
            booksToReturn += book.toString() + System.lineSeparator();
        }

        return booksToReturn;
    }

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

    public BookList filterByCategory(String categoryName) {
        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    ArrayList<String> categories = book.getCategory();
                    return categories.contains(categoryName);
                }).collect(Collectors.toList());
        return new BookList(filteredBooks);
    }
}
