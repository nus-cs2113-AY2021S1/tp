package seedu.duke.model;

import seedu.duke.DukeException;
import seedu.duke.common.Messages;
import seedu.duke.model.itemlist.BookList;
import seedu.duke.model.itemlist.ItemList;
import seedu.duke.model.itemlist.LinkList;
import seedu.duke.model.itemlist.ModuleList;
import seedu.duke.model.itemlist.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.EnumMap;
import java.util.Map;

/**
 * An object representing program state stored in memory.
 */
public class Model {
    private final Map<ListType, ItemList<?>> listMap = new EnumMap<>(ListType.class);
    private final Storage storage;

    public Model() {
        storage = new Storage();
        listMap.put(ListType.TASK_LIST, new TaskList());
        listMap.put(ListType.BOOK_LIST, new BookList());
        listMap.put(ListType.MODULE_LIST, new ModuleList());
        listMap.put(ListType.LINK_LIST, new LinkList());
    }

    public ItemList<?> getList(ListType listType) {
        assert listMap.get(listType) != null;
        return listMap.get(listType);
    }

    // @@author MuhammadHoze
    /**
     * Clears all lists in the model.
     *
     * @throws DukeException If lists are already cleared.
     */
    public void clear() throws DukeException {
        TaskList tasks = (TaskList) getList(ListType.TASK_LIST);
        BookList books = (BookList) getList(ListType.BOOK_LIST);
        LinkList links = (LinkList) getList(ListType.LINK_LIST);
        ModuleList modules = (ModuleList) getList(ListType.MODULE_LIST);

        if ((links.size() != 0 || books.size() != 0 || modules.size() != 0)) {
            getList(ListType.TASK_LIST).clearItems();
            getList(ListType.BOOK_LIST).clearItems();
            getList(ListType.LINK_LIST).clearItems();
            getList(ListType.MODULE_LIST).clearItems();
            Ui.dukePrint(Messages.MESSAGE_CLEAR);
        } else {
            throw new DukeException(Messages.MESSAGE_CLEARED);
        }
    }

    // @@author
    public void load() {
        boolean errorMessage = false;
        try {
            listMap.put(ListType.TASK_LIST, new TaskList(storage.loadTask()));
        } catch (DukeException e) {
            errorMessage = true;
            Ui.showError(e);
            Ui.dukePrint(Messages.MESSAGE_NEW_TASK_FILE);
        }
        try {
            listMap.put(ListType.BOOK_LIST, new BookList(storage.loadBook()));
        } catch (DukeException e) {
            if (!errorMessage) {
                Ui.showError(e);
                errorMessage = true;
            }
            Ui.dukePrint(Messages.MESSAGE_NEW_BOOK_FILE);
        }
        try {
            listMap.put(ListType.LINK_LIST, new LinkList(storage.loadLinks()));
        } catch (DukeException e) {
            if (!errorMessage) {
                Ui.showError(e);
            }
            Ui.dukePrint(Messages.MESSAGE_NEW_LINK_FILE);
        }
        try {
            listMap.put(ListType.MODULE_LIST, new ModuleList(storage.loadModule()));
        } catch (DukeException e) {
            if (!errorMessage) {
                Ui.showError(e);
            }
            Ui.dukePrint(Messages.MESSAGE_NEW_MODULE_FILE);
        }
    }

    public void save() throws DukeException {
        storage.save(listMap.get(ListType.TASK_LIST), Storage.TASK_STORAGE_FILEPATH);
        storage.save(listMap.get(ListType.BOOK_LIST), Storage.BOOK_STORAGE_FILEPATH);
        storage.save(listMap.get(ListType.LINK_LIST), Storage.LINK_STORAGE_FILEPATH);
        storage.save(listMap.get(ListType.MODULE_LIST), Storage.MODULE_STORAGE_FILEPATH);
    }
}
