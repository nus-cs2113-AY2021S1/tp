//@@author TomLBZ

package visualize;

import constants.Constants;
import data.Data;
import messages.MessageFormat;
import messages.MessageOptions;
import messages.MessageWrapper;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Cli.
 */
public class Cli extends UI {

    /**
     * The Msg format.
     */
    protected MessageFormat msgFormat;
    /**
     * The Msg wrapper.
     */
    protected MessageWrapper msgWrapper;

    /**
     * Instantiates a new Cli.
     *
     * @param stream the stream
     * @param input  the input
     */
    public Cli(PrintStream stream, InputStream input) {
        super(stream, input);
        msgFormat = new MessageFormat(new MessageOptions[]{
            MessageOptions.LINE_INDENT_1,
            MessageOptions.LINE_BEFORE,
            MessageOptions.INDENTED_2,
            MessageOptions.AUTO_RETURN,
            MessageOptions.LINE_AFTER
        });
        msgWrapper = new MessageWrapper(Constants.LINE_REPETITION, Constants.LINE_UNIT);
    }

    @Override
    public void showWelcome() {
        msgWrapper.show(stream, Constants.WELCOME, msgFormat.getMessageOptions());
    }

    @Override
    public void showText(String input) {
        String wrappedText = wrapStringArray(input.split(Constants.WIN_NEWLINE));
        String[] lines = wrappedText.split(Constants.WIN_NEWLINE);
        msgWrapper.show(stream, lines, msgFormat.getMessageOptions());
    }

    /**
     * Show list text.
     *
     * @param input       the input
     * @param indexOption the index option
     */
    public void showListText(String input, MessageOptions indexOption) {
        String wrappedText = wrapStringArray(input.split(Constants.WIN_NEWLINE));
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(wrappedText.split(Constants.WIN_NEWLINE)));
        String head = lines.get(0);
        lines.remove(head);
        msgFormat.removeMessageOption(MessageOptions.LINE_AFTER);
        msgWrapper.show(stream, head, msgFormat.getMessageOptions());
        msgFormat.addMessageOption(MessageOptions.LINE_AFTER);
        msgFormat.removeMessageOption(MessageOptions.LINE_BEFORE);
        msgFormat.addMessageOption(indexOption);
        String[] strings = new String[0];
        strings = lines.toArray(strings);
        msgWrapper.show(stream, strings, msgFormat.getMessageOptions());
        msgFormat.addMessageOption(MessageOptions.LINE_BEFORE);
        msgFormat.removeMessageOption(indexOption);
    }

    @Override
    public void update(String input, Data data) {
        String wrappedInput = wrapStringArray(input.split(Constants.WIN_NEWLINE));
        if (freshlySwitched) {
            String replay = data.lastInput;
            MessageOptions replayOption = data.lastIndexOption;
            if (replay == null || replay.equals(Constants.ZERO_LENGTH_STRING)) {
                showWelcome();
            } else {
                replay = "Keeping last output: " + replay;
                showListText(replay, replayOption);
            }
            freshlySwitched = false;
            return;
        }
        if (wrappedInput.equals(Constants.ZERO_LENGTH_STRING)) {
            showText(Constants.ZERO_LENGTH_STRING);
        } else if (wrappedInput.contains(Constants.BMP_LIST_SWITCH)
                || wrappedInput.contains(Constants.BMP_SEL_SWITCH)) {
            data.lastInput = Constants.FANCY_ONLY;
            showText(Constants.FANCY_ONLY);
        } else if (wrappedInput.contains(Constants.FANCY_CLEARED)) {
            data.lastInput = Constants.FANCY_ONLY;
            showText(Constants.FANCY_ONLY);
        } else {
            showListText(wrappedInput, data.indexOption);
            data.lastInput = wrappedInput;
            data.lastIndexOption = data.indexOption;
        }
        data.indexOption = MessageOptions.NOT_INDEXED;
    }

}
