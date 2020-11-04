package seedu.quotesify.store;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.quotesify.author.Author;
import seedu.quotesify.book.Book;
import seedu.quotesify.book.BookList;
import seedu.quotesify.bookmark.Bookmark;
import seedu.quotesify.bookmark.BookmarkList;
import seedu.quotesify.category.Category;
import seedu.quotesify.category.CategoryList;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.quote.Quote;
import seedu.quotesify.quote.QuoteList;
import seedu.quotesify.rating.Rating;
import seedu.quotesify.rating.RatingList;
import seedu.quotesify.todo.ToDo;
import seedu.quotesify.todo.ToDoList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents the storage for Quotesify.
 */
public class Storage {
    private static final String CURRENT_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = File.separator;

    private static final String BOOKS = "books";
    private static final String QUOTES = "quotes";
    private static final String CATEGORIES = "categories";
    private static final String RATINGS = "ratings";
    private static final String BOOKMARKS = "bookmarks";
    private static final String TODOS = "todos";
    private static final String DATA_CORRUPT_MESSAGE = "[%s] is corrupted in save file. Creating an empty list.\n";

    private File saveFile;

    /**
     * Constructor for storage with file path to save data.
     *
     * @param filePath save data directory
     */
    public Storage(String filePath) {
        initialiseSaveFile(filePath);
    }

    /**
     * Checks for save file existence and manages its creation.
     *
     * @param filePath save data directory
     */
    public void initialiseSaveFile(String filePath) {
        try {
            filePath = CURRENT_DIR + filePath.replace("/", SEPARATOR).replace("\\", SEPARATOR);
            saveFile = new File(filePath);

            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }

            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(filePath + " creation failed.");
        }
    }

    /**
     * Saves the data into hard disk.
     */
    public void save() {
        try {
            JSONObject json = new JSONObject();
            json.put("books", ListManager.getList(ListManager.BOOK_LIST).toJsonArray());
            json.put("quotes", ListManager.getList(ListManager.QUOTE_LIST).toJsonArray());
            json.put("categories", ListManager.getList(ListManager.CATEGORY_LIST).toJsonArray());
            json.put("bookmarks", ListManager.getList(ListManager.BOOKMARK_LIST).toJsonArray());
            json.put("ratings", ListManager.getList(ListManager.RATING_LIST).toJsonArray());
            json.put("todos", ListManager.getList(ListManager.TODO_LIST).toJsonArray());

            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(json.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Saving to file failed!");
            e.printStackTrace();
        }
    }

    /**
     * Loads data from hard disk.
     */
    public void load() {
        try {
            FileReader fileReader = new FileReader(saveFile);
            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject) jsonParser.parse(fileReader);
            updateListManager(json);
            fileReader.close();
        } catch (ParseException | IOException e) {
            // e.printStackTrace();
            ListManager.initialiseAllLists();
        }
    }

    /**
     * Updates all lists in list manager with saved data.
     *
     * @param json JSON serialized save data
     */
    private void updateListManager(JSONObject json) {
        try {
            ListManager.addToList(ListManager.BOOK_LIST, parseBookList(json));
            ListManager.addToList(ListManager.QUOTE_LIST, parseQuoteList(json));
            ListManager.addToList(ListManager.CATEGORY_LIST, parseCategoryList(json));
            ListManager.addToList(ListManager.RATING_LIST, parseRatingList(json));
            ListManager.addToList(ListManager.BOOKMARK_LIST, parseBookmarkList(json));
            ListManager.addToList(ListManager.TODO_LIST, parseTodoList(json));
        } catch (NullPointerException e) {
            // e.printStackTrace();
        }
    }

    /**
     * Parses save data into book list.
     *
     * @param json JSON serialized save data
     * @return list of saved books
     */
    private BookList parseBookList(JSONObject json) {
        try {
            JSONArray books = (JSONArray) json.get(BOOKS);
            ArrayList<Book> bookList = new ArrayList<>();
            for (Object book : books) {
                bookList.add(parseBookObject((JSONObject) book));
            }
            return new BookList(bookList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, BOOKS);
        }
        return new BookList();
    }

    /**
     * Parses save data into quote list.
     *
     * @param json JSON serialized save data
     * @return list of saved quotes
     */
    private QuoteList parseQuoteList(JSONObject json) {
        try {
            JSONArray quotes = (JSONArray) json.get(QUOTES);
            ArrayList<Quote> quoteList = new ArrayList<>();
            for (Object quote : quotes) {
                quoteList.add(parseQuoteObject((JSONObject) quote));
            }
            return new QuoteList(quoteList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, QUOTES);
        }
        return new QuoteList();
    }

    /**
     * Parses save data into category list.
     *
     * @param json JSON serialized save data
     * @return list of saved categories
     */
    private CategoryList parseCategoryList(JSONObject json) {
        try {
            JSONArray categories = (JSONArray) json.get(CATEGORIES);
            ArrayList<Category> categoryList = new ArrayList<>();
            for (Object category : categories) {
                categoryList.add(parseCategoryObject((JSONObject) category));
            }
            return new CategoryList(categoryList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, CATEGORIES);
        }
        return new CategoryList();
    }

    /**
     * Parses save data into rating list.
     *
     * @param json JSON serialized save data
     * @return list of saved ratings
     */
    private RatingList parseRatingList(JSONObject json) {
        try {
            JSONArray ratings = (JSONArray) json.get(RATINGS);
            ArrayList<Rating> ratingList = new ArrayList<>();
            for (Object rating : ratings) {
                ratingList.add(parseRatingObject((JSONObject) rating));
            }
            return new RatingList(ratingList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, RATINGS);
        }
        return new RatingList();
    }

    /**
     * Parses save data into bookmark list.
     *
     * @param json JSON serialized save data
     * @return list of saved bookmarks
     */
    private BookmarkList parseBookmarkList(JSONObject json) {
        try {
            JSONArray bookmarks = (JSONArray) json.get(BOOKMARKS);
            ArrayList<Bookmark> bookmarkList = new ArrayList<>();
            for (Object bookmark : bookmarks) {
                bookmarkList.add(parseBookmarkObject((JSONObject) bookmark));
            }
            return new BookmarkList(bookmarkList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, BOOKMARKS);
        }
        return new BookmarkList();
    }

    /**
     * Parses save data into todo list.
     *
     * @param json JSON serialized save data
     * @return list of saved todos
     */
    private ToDoList parseTodoList(JSONObject json) {
        try {
            JSONArray todos = (JSONArray) json.get(TODOS);
            ArrayList<ToDo> todoList = new ArrayList<>();
            for (Object todo : todos) {
                todoList.add(parseTodoObject((JSONObject) todo));
            }
            return new ToDoList(todoList);
        } catch (NullPointerException e) {
            System.out.printf(DATA_CORRUPT_MESSAGE, TODOS);
        }
        return new ToDoList();
    }

    /**
     * Parses save data into a book.
     *
     * @param json JSON serialized save data
     * @return saved book details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private Book parseBookObject(JSONObject json) throws NullPointerException {
        JSONObject authorObj = (JSONObject) json.get("author");
        Author author = parseAuthorObject(authorObj);
        String title = (String) json.get("title");
        boolean isDone = (boolean) json.get("isDone");
        JSONArray array = (JSONArray) json.get("categories");
        long rating = (long) json.get("rating");
        ArrayList<String> categories = (ArrayList<String>) array.stream()
                .collect(Collectors.toList());
        return new Book(author, title, isDone, categories, (int) rating);
    }

    /**
     * Parses save data into a quote.
     *
     * @param json JSON serialized save data
     * @return saved quote details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private Quote parseQuoteObject(JSONObject json) throws NullPointerException {
        JSONObject authorObj = (JSONObject) json.get("author");
        Author author = parseAuthorObject(authorObj);
        String quote = (String) json.get("quote");
        String reference = (String) json.get("reference");
        String reflection = (String) json.get("reflection");
        JSONArray array = (JSONArray) json.get("categories");
        ArrayList<String> categories = (ArrayList<String>) array.stream()
                .collect(Collectors.toList());
        return new Quote(author, quote, categories, reference, reflection);
    }

    /**
     * Parses save data into a category.
     *
     * @param json JSON serialized save data
     * @return saved category details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private Category parseCategoryObject(JSONObject json) throws NullPointerException {
        String name = (String) json.get("category");
        BookList bookList = (BookList) ListManager.getList(ListManager.BOOK_LIST);
        QuoteList quoteList = (QuoteList) ListManager.getList(ListManager.QUOTE_LIST);

        Category category = new Category(name);
        category.setBookList(bookList.filterByCategory(name));
        category.setQuoteList(quoteList.filterByCategory(name));
        return category;
    }

    /**
     * Parses save data into a rating.
     *
     * @param json JSON serialized save data
     * @return saved rating details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private Rating parseRatingObject(JSONObject json) throws NullPointerException {
        String title = (String) json.get("titleOfRatedBook");
        String name = (String) json.get("authorOfRatedBook");
        Author author = new Author(name);
        long rating = (long) json.get("rating");
        return new Rating(new Book(author, title), (int) rating);
    }

    /**
     * Parses save data into a bookmark.
     *
     * @param json JSON serialized save data
     * @return saved bookmark details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private Bookmark parseBookmarkObject(JSONObject json) throws NullPointerException {
        JSONObject bookObj = (JSONObject) json.get("book");
        Book book = parseBookObject(bookObj);
        long pageNum = (long) json.get("pageNum");
        return new Bookmark(book, (int) pageNum);
    }

    /**
     * Parses save data into a todo.
     *
     * @param json JSON serialized save data
     * @return saved todo details
     * @throws NullPointerException if fields are missing or corrupted
     */
    private ToDo parseTodoObject(JSONObject json) throws NullPointerException {
        String name = (String) json.get("name");
        String deadline = (String) json.get("deadline");
        boolean isDone = (boolean) json.get("isDone");
        ToDo newToDo = new ToDo(name, deadline, isDone);
        newToDo.updateDateFormat();
        return newToDo;
    }

    /**
     * Parses save data into an author.
     *
     * @param json JSON serialized save data
     * @return saved author details
     */
    private Author parseAuthorObject(JSONObject json) {
        try {
            String authorName = (String) json.get("name");
            return new Author(authorName);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
