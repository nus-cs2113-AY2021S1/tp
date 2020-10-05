package seedu.duke.bookmark;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a bookmark of a URL of a webpage.
 * It stores the URL and a description of the webpage.
 * It also contains a method which can extract the URL and description from a given string in a certain format.
 */
public class Bookmark {
    private String url;
    private String description;

    /**
     * Constructs a bookmark object containing a URL and description of the webpage.
     *
     * @param url The URL of the webpage.
     * @param description The description of the webpage.
     */
    public Bookmark(String url, String description) {
        this.url = url.trim();
        this.description = description.trim();
    }

    /**
     * Constructs a bookmark object containing a URL.
     *
     * @param url The URL of the webpage.
     */
    public Bookmark(String url) {
        this.url = url.trim();
    }

    /**
     * Returns the URL and description that can be detected from the given input.
     *
     * @param input the string input by the user.
     * @return a list of strings containing the URL and the description
     */
    public static List<String> extractUrlAndDescription(String input) {
        List<String> urlAndDescription = Arrays.asList(input.split(" ", 2));
        if (urlAndDescription.size() != 2) {
            // throw new DukeException(DukeExceptionType.INVALID_URL_AND_DESCRIPTION); for example
        }
        return urlAndDescription;
    }

    /**
     * Returns the topic, URL and description that can be detected from the given input.
     *
     * @param input the string input by the user.
     * @return a list of strings containing the topic, URL and the description
     */
    public static List<String> extractTopicUrlAndDescription(String input) {
        List<String> topicUrlAndDescription = Arrays.asList(input.split(" ", 3));
        if (topicUrlAndDescription.size() != 3) {
            // throw exception
        } else if (topicUrlAndDescription.get(1).isBlank() || topicUrlAndDescription.get(2).isBlank()) {
            // throw exception
        }
        return topicUrlAndDescription;
    }

    /**
     * This method opens a web browser and open the URL of the bookmark.
     */
    public void launch() {
        // launch
    }

    /**
     * Returns the URL of the bookmark.
     *
     * @return a string of the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the description of the bookmark.
     *
     * @return a string of the description of the bookmark.
     */
    public String getDescription() {
        return description;
    }
}
