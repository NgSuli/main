//@@author: giang
package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Favorite a person identified using it's last displayed index from the address book.
 */
public class FavoriteCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "favorite";
    public static final String COMMAND_ALIAS = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favorites the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAVORITE_PERSON_SUCCESS = "Favorited Person: %1$s";

    private final Index targetIndex;

    public FavoriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToFavorite = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.favoritePerson(personToFavorite);
        } catch (PersonNotFoundException pnfe) {
            assert false : "The target person cannot be missing";
        }

        return new CommandResult(String.format(MESSAGE_FAVORITE_PERSON_SUCCESS, personToFavorite));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FavoriteCommand // instanceof handles nulls
                && this.targetIndex.equals(((FavoriteCommand) other).targetIndex)); // state check
    }
}
