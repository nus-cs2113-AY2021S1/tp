package command;

import cheatsheet.CheatSheet;
import cheatsheet.CheatSheetList;
import editor.Editor;
import exception.CommandException;
import exception.EditorException;
import parser.CommandFlag;
import ui.Printer;

public class AddCommand extends Command {
    private final Editor editor;
    public static final String invoker = "/add";

    public AddCommand(Printer printer, CheatSheetList cheatSheetList, Editor editor) {
        super(printer);
        this.cheatSheetList = cheatSheetList;
        this.editor = editor;

        flagsToDescriptions.put(CommandFlag.NAME, null);
        flagsToDescriptions.put(CommandFlag.SUBJECT, null);
        alternativeArguments.add(CommandFlag.NAME);
        alternativeArguments.add(CommandFlag.SUBJECT);
    }

    @Override
    public void execute() throws CommandException {
        String name = flagsToDescriptions.get(CommandFlag.NAME).trim();
        if (cheatSheetList.exists(name)) {
            throw new CommandException("Name already existed, please enter another name");
        }
        if (name.isEmpty() || name.isBlank()) {
            throw new CommandException("Name cannot be blank");
        }

        String subject = flagsToDescriptions.get(CommandFlag.SUBJECT).trim();
        if (subject.isEmpty() || subject.isBlank()) {
            throw new CommandException("Subject cannot be blank");
        }
        if (subject != null) {
            subject = convertToPascalCaseNoSpace(subject);
        } else {
            subject = "Unsorted";
        }

        callContentEditor();
        try {
            String description = editor.getContent();
            CheatSheet cheatSheet = new CheatSheet(name, subject, description);
            cheatSheetList.add(cheatSheet);
            printer.printAddNewCheatSheetMessage(cheatSheet, cheatSheetList);
        } catch (EditorException | NullPointerException e) {
            throw new CommandException(e.getMessage());
        }
    }

    private void callContentEditor() {
        editor.open();
        editor.waitForClose();
    }

    private String convertToPascalCaseNoSpace(String input) {
        String[] splitInput = input.split("\\p{IsWhite_Space}+");
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].substring(0, 1).toUpperCase() + splitInput[i].substring(1).toLowerCase();
        }
        return String.join("", splitInput);
    }
}
