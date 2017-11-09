//@@author A0156087X
package seedu.address.logic.commands;

/**
 * Command to send mail to a person.
 */
public class MailCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "mail";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Log in to send an email.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_MAIL_MESSAGE = "Opened mail window.";


}
//@@author
