package commands;

import access.Access;
import exception.InvalidInputException;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public abstract class Command {

    public abstract void execute(CardList cards, Ui ui, Access access, Storage storage) throws InvalidInputException;

    public abstract boolean isExit();
}
