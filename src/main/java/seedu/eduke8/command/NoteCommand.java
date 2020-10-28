package seedu.eduke8.command;

import seedu.eduke8.common.Displayable;
import seedu.eduke8.common.DisplayableList;
import seedu.eduke8.exception.Eduke8Exception;
import seedu.eduke8.note.Note;
import seedu.eduke8.topic.TopicList;
import seedu.eduke8.ui.Ui;

public class NoteCommand extends Command {

    private static final String NOTE_ADD = "add";
    private static final String NOTE_DELETE = "delete";
    private static final String NOTE_LIST = "list";

    private String typeOfNoteCommand;
    private TopicList topics;

    public NoteCommand(String typeOfNoteCommand, TopicList topics) {
        this.typeOfNoteCommand = typeOfNoteCommand;
        this.topics = topics;
    }

    @Override
    public void execute(DisplayableList displayableList, Ui ui) {
        switch (typeOfNoteCommand) {
        case (NOTE_ADD):
            ui.addNoteInteractions(topics);
            break;
        case (NOTE_DELETE):
            ui.deleteNoteInteractions(topics);
            break;
        case (NOTE_LIST) :
            ui.listInteraction(topics);
            break;
        }
    }
}
