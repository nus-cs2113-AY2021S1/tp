package zeronote.userinterface.command.notebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import zeronote.exceptions.InvalidKeywordTagException;
import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.userinterface.AppState;
import zeronote.userinterface.CliMessages;
import zeronote.userinterface.command.CliCommand;

//@@author Lusi711
/**
 * Command class to find all notebooks, sections and pages with titles containing a given keyword or
 * have a given tag. There is no restriction on the current mode of the app. The search is non-case-sensitive.
 */
public class FindCommandNotebookMode extends CliCommand {
    public static final String COMMAND_WORD = "find";
    private String keyword;
    private String tag;

    private ArrayList<String> notebookMessages = new ArrayList<>();
    private ArrayList<String> sectionMessages = new ArrayList<>();
    private ArrayList<String> pageMessages = new ArrayList<>();

    public FindCommandNotebookMode(String keyword, String tag, AppState appState) {
        this.keyword = keyword.toLowerCase();
        this.tag = tag.toLowerCase();
        this.appState = appState;
        PRINTS_PERSONAL_MESSAGE = true;
    }

    /**
     * Determines whether the user is searching by keyword or by tag and executes the corresponding command.
     */
    public void execute() {
        try {
            if (tag.equals("") && !keyword.equals("")) {
                getAllWithTitleContainingKeyword();
                System.out.println("I've found these for keyword: " + keyword);
            } else if (!tag.equals("") && keyword.equals("")) {
                getAllWithTag();
                System.out.println("I've found these for tag: " + tag);
            } else {
                throw new InvalidKeywordTagException(keyword + "\t" + tag);
            }
        } catch (ZeroNoteException zne) {
            zne.printErrorMessage();
            return;
        }

        print();
    }

    /**
     * Prints all the notebooks, sections and pages that are found.
     */
    private void print() {
        CliMessages cliMessages = new CliMessages();
        if (notebookMessages.isEmpty() && sectionMessages.isEmpty() && pageMessages.isEmpty()) {
            System.out.println("Nothing is found!");
        }
        if (!notebookMessages.isEmpty()) {
            System.out.println("Notebooks:");
            cliMessages.printFoundNotebooksMessages(notebookMessages);
        }
        if (!sectionMessages.isEmpty()) {
            System.out.println("Sections:");
            cliMessages.printFoundNotebooksMessages(sectionMessages);
        }
        if (!pageMessages.isEmpty()) {
            System.out.println("Pages:");
            cliMessages.printFoundNotebooksMessages(pageMessages);
        }
    }

    /**
     * Search and store all notebooks, sections and pages with the title containing the specified keyword.
     */
    private void getAllWithTitleContainingKeyword() {
        NotebookShelf currentNotebookShelf = appState.getCurrentBookShelf();
        for (Notebook notebook : currentNotebookShelf.getNotebooksArrayList()) {
            String notebookTitle = notebook.getTitle();
            if (isMatching(notebookTitle, keyword)) {
                notebookMessages.add(notebookTitle);
            }
            getSectionsWithTitleContainingKeyword(notebook);
        }
    }

    private void getSectionsWithTitleContainingKeyword(Notebook notebook) {
        for (Section section : notebook.getSectionArrayList()) {
            String sectionTitle = section.getTitle();
            if (isMatching(sectionTitle, keyword)) {
                sectionMessages.add(notebook.getTitle() + " |-- " + sectionTitle);
            }
            getPagesWithTitleContainingKeyword(notebook, section);
        }
    }

    private void getPagesWithTitleContainingKeyword(Notebook notebook, Section section) {
        for (Page page : section.getPageArrayList()) {
            String pageTitle = page.getTitle();
            if (isMatching(pageTitle, keyword)) {
                pageMessages.add(notebook.getTitle() +  " |-- " + section.getTitle() + " |-- " + page.getTitle());
            }
        }
    }

    /**
     * Search and store all notebooks, sections and pages with the specified tag (non-case sensitive).
     */
    private void getAllWithTag() {
        NotebookShelf currentNotebookShelf = appState.getCurrentBookShelf();
        for (Notebook notebook : currentNotebookShelf.getNotebooksArrayList()) {
            String notebookTag = notebook.getTag().toLowerCase();
            if (!notebookTag.equals("") && notebookTag.equals(tag)) {
                notebookMessages.add(notebook.getTitle());
            }
            getSectionsWithTag(notebook);
        }
    }

    private void getSectionsWithTag(Notebook notebook) {
        for (Section section : notebook.getSectionArrayList()) {
            String sectionTag = section.getTag().toLowerCase();
            if (!sectionTag.equals("") && sectionTag.equals(tag)) {
                sectionMessages.add(notebook.getTitle() + " |-- " + section.getTitle());
            }
            getPagesWithTag(notebook, section);
        }
    }

    private void getPagesWithTag(Notebook notebook, Section section) {
        for (Page page : section.getPageArrayList()) {
            String pageTag = page.getTag().toLowerCase();
            if (!pageTag.equals("") && pageTag.equals(tag)) {
                pageMessages.add(notebook.getTitle() +  " |-- " + section.getTitle() + " |-- " + page.getTitle());
            }
        }
    }

    private boolean isMatching(String title, String keyword) {
        Set<String> wordsInTitle = new HashSet<>(getWordsInTitle(title));
        for (String word : wordsInTitle) {
            if (word.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all notebooks that were found.
     *
     * @return the titles of the found notebooks.
     */
    public ArrayList<String> getNotebookMessages() {
        return notebookMessages;
    }

    /**
     * Gets all sections that were found.
     *
     * @return the titles of the found sections.
     */
    public ArrayList<String> getSectionMessages() {
        return sectionMessages;
    }

    /**
     * Gets all pages that were found.
     *
     * @return the pages of the found sections.
     */
    public ArrayList<String> getPageMessages() {
        return pageMessages;
    }

    private List<String> getWordsInTitle(String title) {
        return Arrays.asList(title.toLowerCase().split(" "));
    }

    public boolean isPersonalised() {
        return PRINTS_PERSONAL_MESSAGE;
    }
}
