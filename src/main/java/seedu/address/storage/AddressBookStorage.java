package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link seedu.address.model.ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws seedu.address.commons.exceptions.DataConversionException if the data in storage is not in the expected format.
     * @throws java.io.IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws java.io.IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(seedu.address.model.ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

}
