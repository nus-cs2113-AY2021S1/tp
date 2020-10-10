package seedu.duke.ui;

import seedu.duke.book.Book;
import seedu.duke.category.Category;
import seedu.duke.category.CategoryList;
import seedu.duke.quote.QuoteList;
import seedu.duke.rating.Rating;
import seedu.duke.rating.RatingList;
import seedu.duke.todo.ToDo;
import seedu.duke.todo.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {
    private static final String LOGO = "________                __                .__  _____       \n"
            + "\\_____  \\  __ __  _____/  |_  ____   _____|__|/ ____\\__.__.\n"
            + " /  / \\  \\|  |  \\/  _ \\   __\\/ __ \\ /  ___/  \\   __<   |  |\n"
            + "/   \\_/.  \\  |  (  <_> )  | \\  ___/ \\___ \\|  ||  |  \\___  |\n"
            + "\\_____\\ \\_/____/ \\____/|__|  \\___  >____  >__||__|  / ____|\n"
            + "       \\__>                      \\/     \\/          \\/    ";

    private static final String WELCOME_MESSAGE = "Welcome to Quotesify!";
    private static final String GOODBYE_MESSAGE = "Have a nice day!";
    private static final String PROMPT_MESSAGE = "\nWhat would you like to do with Quotesify?";
    private static final String ADD_BOOK = "The book [%s] has been added!";
    private static final String DELETE_BOOK = "The book [%s] has been deleted!";
    private static final String ADD_CATEGORY_MESSAGE = "I have tagged \"%s\" category to \"%s\"!";
    private static final String DELETE_CATEGORY_MESSAGE = "I have removed \"%s\" category from \"%s\"!";
    private static final String CATEGORY_SIZE_MESSAGE = "You have a total of %d item(s) tagged as \"%s\".";
    private static final String LIST_CATEGORIES_MESSAGE = "Here is the list of all categories:";
    private static final String ADD_RATING_MESSAGE = "You have just rated %s %d star!";
    public static final String DELETE_RATING_MESSAGE = "Rating for %s has been deleted!";
    private static final String LIST_ALL_RATINGS_MESSAGE = "Planning to recommend some books?"
            + " Here are your rated books!";
    private static final String LIST_SPECIFIED_RATING_MESSAGE = "Here are the books you rated as %d star!";
    private static final String ADD_TODO = "The task [%s] has been added!";
    private static final String TODO_SIZE_MESSAGE = "You have a total of %d task(s) recorded.";
    private static final String LIST_TODOS_MESSAGE = "Here is the list of all tasks recorded:";
    private static final String DELETE_TODO_MESSAGE = "The Task [%s] has been deleted!";


    private final Scanner in;

    public TextUi() {
        in = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public String getUserCommand() {
        System.out.println(PROMPT_MESSAGE);
        return in.nextLine().trim();
    }

    public void printAddBook(Book book) {
        System.out.printf(ADD_BOOK + "\n", book.toString());
    }

    public void printDeleteBook(Book book) {
        System.out.printf(DELETE_BOOK + "\n", book.toString());
    }

    public void printAllQuotes(QuoteList quotes) {
        System.out.println("Here are your quotes:");
        System.out.println(quotes);
    }

    public void printAddCategoryToBook(String bookTitle, String categoryName) {
        System.out.printf((ADD_CATEGORY_MESSAGE) + "\n", categoryName, bookTitle);
    }

    public void printAddCategoryToQuote(String quote, String categoryName) {
        System.out.printf((ADD_CATEGORY_MESSAGE) + "\n", categoryName, quote);
    }

    public void printRemoveCategoryFromBook(String bookTitle, String categoryName) {
        System.out.printf((DELETE_CATEGORY_MESSAGE) + "\n", categoryName, bookTitle);
    }

    public void printRemoveCategoryFromQuote(String quote, String categoryName) {
        System.out.printf((DELETE_CATEGORY_MESSAGE) + "\n", categoryName, quote);
    }

    public void printCategorySize(Category category) {
        System.out.printf((CATEGORY_SIZE_MESSAGE) + "\n", category.getSize(), category.getCategoryName());
    }

    public void printAllCategories(CategoryList categoryList) {
        System.out.println(LIST_CATEGORIES_MESSAGE);
        System.out.println(categoryList.toString());
    }

    public void printCategory(Category category) {
        System.out.println(category.toString());
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printBook(Book book) {
        System.out.println(book.toString());
    }

    public void printAddRatingToBook(int ratingScore, String titleOfBookToRate) {
        System.out.printf((ADD_RATING_MESSAGE) + "\n", titleOfBookToRate, ratingScore);
    }

    public void printAllRatings(RatingList ratingList) {
        System.out.println(LIST_ALL_RATINGS_MESSAGE);
        System.out.println(ratingList.toString());
    }

    public void printSpecifiedRating(RatingList ratings, int ratingToList) {
        System.out.printf((LIST_SPECIFIED_RATING_MESSAGE) + "\n", ratingToList);
        for (Rating rating : ratings.getList()) {
            if (rating.getRating() == ratingToList) {
                System.out.println(rating.getTitleOfRatedBook());
            }
        }
    }

    public void printDeleteRating(String bookTitle) {
        System.out.printf((DELETE_RATING_MESSAGE) + "\n", bookTitle);
    }

    public void printAddToDo(ToDo toDo) {
        System.out.printf(ADD_TODO + "\n", toDo.toString());
    }

    public void printToDo(ToDo toDo) {
        System.out.println(toDo.toString());
    }

    public void printAllToDos(ToDoList toDoList){
        System.out.println(LIST_TODOS_MESSAGE);
        System.out.println(toDoList.toString());
    }
}
