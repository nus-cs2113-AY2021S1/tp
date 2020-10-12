package commands;

import access.Access;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command {

    public abstract void execute(CardList cards, Ui ui, Access access, Storage storage)
            throws InvalidInputException, IncorrectAccessLevelException, 
            IOException, InvalidFileFormatException;

    public abstract boolean isExit();
}
