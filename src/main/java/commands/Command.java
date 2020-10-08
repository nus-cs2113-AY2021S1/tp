package commands;

import access.Access;
import manager.chapter.CardList;
import storage.Storage;
import ui.Ui;

public abstract class Command {

    public abstract void execute(CardList cards, Ui ui, Access access, Storage storage);

    public abstract boolean isExit();
}
