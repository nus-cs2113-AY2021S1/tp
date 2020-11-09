package seedu.zoomaster.bookmark;

import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a bookmark of a URL of a webpage.
 * It stores the URL and a description of the webpage.
 * It also contains a method which can extract the URL and description from a given string in a certain format.
 */
//@@author xingrong123
public class Bookmark {
    public static final String WWW_KW = "www.";
    public static final String HTTPS_KW = "https://";
    public static final String HTTP_KW = "http://";
    public static final String WINDOWS_LAUNCH_COMMAND = "rundll32 url.dll,FileProtocolHandler ";
    public static final String MAC_LAUNCH_COMMAND = "open ";
    public static final String LINUX_LAUNCH_COMMAND = "xdg-open ";
    private String description;
    private String url;

    /**
     * Constructs a bookmark object containing a URL and description of the webpage.
     *
     * @param url The URL of the webpage.
     * @param description The description of the webpage.
     */
    public Bookmark(String description, String url) {
        assert description != null : "description should not be null";
        assert url != null : "url should not be null";

        this.description = description.trim();
        this.url = url.trim();
    }

    /**
     * Returns the description and url that can be detected from the given input.
     *
     * @param input the string input by the user.
     * @return a list of strings containing the description and url.
     * @throws ZoomasterException if the command format is invalid or if the url is invalid.
     */
    public static List<String> extractDescriptionAndUrl(String input) throws ZoomasterException {
        List<String> descriptionUrl = Arrays.asList(input.split("\\s+", 2));
        if (descriptionUrl.size() != 2) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT);
        }
        if (!isUrlValid(descriptionUrl.get(1))) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_URL);
        }
        return descriptionUrl;
    }

    //@@author Speedweener
    public static Boolean isUrlValid(String url) {
        if (url.contains(" ")) {
            return false;
        }
        return url.startsWith(WWW_KW) || url.startsWith(HTTPS_KW) || url.startsWith(HTTP_KW);
    }

    /**
     * This method opens the URL of the bookmark in a web browser.
     *
     * @return a string
     * @throws ZoomasterException if there is an error launching the URL.
     */
    public String launch() throws ZoomasterException {
        try {
            launchUrl();
        } catch (IOException e) {
            throw new ZoomasterException(ZoomasterExceptionType.ERROR_LAUNCHING_URL);
        }
        return "  [" + description + "] " + url;
    }

    //@@author xingrong123
    private void launchUrl() throws IOException {
        Runtime rt = Runtime.getRuntime();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            launchUrlForWindows(rt);
        } else if (os.contains("mac")) {
            launchUrlForMac(rt);
        } else if (os.contains("nix") || os.contains("nux")) {
            launchUrlForLinux(rt);
        }
    }

    private void launchUrlForWindows(Runtime rt) throws IOException {
        rt.exec(WINDOWS_LAUNCH_COMMAND + url);
    }

    private void launchUrlForMac(Runtime rt) throws IOException {
        String link = getFullLink();
        rt.exec(MAC_LAUNCH_COMMAND + link);
    }

    private void launchUrlForLinux(Runtime rt) throws IOException {
        String link = getFullLink();
        rt.exec(LINUX_LAUNCH_COMMAND + link);
    }

    private String getFullLink() {
        String link;
        if (url.startsWith(WWW_KW)) {
            link = HTTPS_KW + url;
        } else {
            link = url;
        }
        return link;
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
     * Sets the URL of the bookmark.
     *
     * @param url String of the URL.
     */
    public void setUrl(String url) throws ZoomasterException {
        if (!isUrlValid(url)) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_URL);
        }
        this.url = url;
    }

    /**
     * Returns the description of the bookmark.
     *
     * @return a string of the description of the bookmark.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookmarkAsString() {
        return  ("[" + description + "] " + url + System.lineSeparator());
    }

}