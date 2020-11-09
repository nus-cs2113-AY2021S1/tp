//@@author TomLBZ

package command;

/**
 * The interface Help.
 */
public interface Help {

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName();

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription();

    /**
     * Get syntax string [ ].
     *
     * @return the string [ ]
     */
    public String[] getSyntax();

    public String[] getNotes();

    /**
     * Get usages string [ ].
     *
     * @return the string [ ]
     */
    public String[] getUsages();

    /**
     * Gets help.
     *
     * @return the help
     */
    public String getHelp();

}
