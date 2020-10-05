package seedu.duke.bookmark;
import seedu.duke.command.AddBookmarkCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a bookmark of a URL of a webpage.
 * It stores the URL and a description of the webpage.
 * It also contains a method which can extract the URL and description from a given string in a certain format.
 */
public class Bookmark {
    private String module;
    private String description;
    private String url;
    private final String SEPARATOR = " | ";
    

    /**
     * Constructs a bookmark object containing a URL and description of the webpage.
     *
     * @param url The URL of the webpage.
     * @param description The description of the webpage.
     */
    public Bookmark(String module, String description, String url) {
        this.module = module.trim();
        this.description = description.trim();
        this.url = url.trim();
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

//    public static List<String> extractDescriptionAndURL(String input) {
//        List<String> urlAndDescription = Arrays.asList(input.split(" ", 2));
//        if (urlAndDescription.size() != 2) {
//            throw new DukeException(DukeExceptionType.INVALID_URL_AND_DESCRIPTION);
//        }
//        return urlAndDescription;
//    }
    
    /**
     * Returns the topic, URL and description that can be detected from the given input.
     *
     * @param input the string input by the user.
     * @return a list of strings containing the topic, URL and the description
     */

    public static List<String> extractModuleDescriptionAndURL (String input) throws DukeException {
        input = input.substring(AddBookmarkCommand.ADD_KW.length()).trim();
        System.out.println(input);
        List<String> moduleDescriptionURL = Arrays.asList(input.split(" ", 3));
        if (moduleDescriptionURL.size() == 2) {
            moduleDescriptionURL.add(0, "");  // No entry for module
        }
        if (moduleDescriptionURL.size() != 3) {
            throw new DukeException(DukeExceptionType.INVALID_URL_AND_DESCRIPTION);
        }
        if (moduleDescriptionURL.get(1).isBlank() || moduleDescriptionURL.get(2).isBlank()) {
           throw new DukeException(DukeExceptionType.EMPTY_DESCRIPTION);
        }
        if (!isURLValid(moduleDescriptionURL.get(2))) {
            throw new DukeException(DukeExceptionType.INVALID_URL_AND_DESCRIPTION);
        }

        return moduleDescriptionURL;
    }

    private static Boolean isURLValid(String url) {
        if (url.startsWith("www.") || url.startsWith("https://")) {
            return true;
        }

        return false;
    }
        

    /**
     * This method opens a web browser and open the URL of the bookmark.
     */
    public void launch() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            System.out.println("jippai jialat liao");
        }
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

    public String getModule() {
        return module;
    }

    public String getBookmarkAsString() {
        return  ("[" + module + "] " + description + " " +  url + System.lineSeparator());
    }

    public String getExport() {
        return module + SEPARATOR + description + SEPARATOR + url;
    }

    public static Bookmark initBookmark(String data) throws DukeException {
        List<String> details =  Arrays.asList(data.split("\\|"));
        String module = details.get(0).trim();
        String description = details.get(1).trim();
        String url = details.get(2).trim();
        Bookmark bookmark = new Bookmark(module, description, url);
        return bookmark;
    }


}
