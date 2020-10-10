package seedu.duke.book;

import seedu.duke.author.Author;
import seedu.duke.lists.QuotesifyList;

import java.lang.reflect.Array;
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

    @Override
    public String toString() {
        String booksToReturn = "";

        for (Book book : books) {
            booksToReturn += book.toString() + System.lineSeparator();
        }

        return booksToReturn;
    }

    public ArrayList<Book> find(String title, String author) {
        ArrayList<Book> filteredBooks = (ArrayList<Book>) books.stream()
                .filter(book -> {
                    Author bookAuthor = book.getAuthor();
                    return bookAuthor.getName().equals(author) && book.getTitle().equals(title);
                }).collect(Collectors.toList());
        return filteredBooks;
    }

    public Book findByTitle(String title) {
        for (Book book : books) {
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        return null;
    }
}
