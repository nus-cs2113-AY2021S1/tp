//@@author TomLBZ

package messages;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Message format.
 */
public class MessageFormat {
    private final ArrayList<MessageOptions> messageOptionsList;

    /**
     * Instantiates a new Message format.
     *
     * @param options the options
     */
    public MessageFormat(MessageOptions[] options) {
        messageOptionsList = new ArrayList<>(Arrays.asList(options));
    }

    /**
     * Add message option.
     *
     * @param option the option
     */
    public void addMessageOption(MessageOptions option) {
        messageOptionsList.add(option);
    }

    /**
     * Remove message option.
     *
     * @param option the option
     */
    public void removeMessageOption(MessageOptions option) {
        messageOptionsList.remove(option);
    }

    /**
     * Get message options message options [ ].
     *
     * @return the message options [ ]
     */
    public MessageOptions[] getMessageOptions() {
        MessageOptions[] output = new MessageOptions[messageOptionsList.size()];
        return messageOptionsList.toArray(output);
    }
}
