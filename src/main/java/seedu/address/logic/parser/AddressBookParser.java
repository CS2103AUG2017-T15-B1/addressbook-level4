package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ChangePwdCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.SendCommand;
import seedu.address.logic.commands.UndoCommand;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
        case AddCommand.COMMAND_ALIAS:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
        case EditCommand.COMMAND_ALIAS:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
        case SelectCommand.COMMAND_ALIAS:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_ALIAS:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_ALIAS:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_ALIAS:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
        case ListCommand.COMMAND_ALIAS:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
        case HistoryCommand.COMMAND_ALIAS:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_ALIAS:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_ALIAS:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_ALIAS:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_ALIAS:
            return new RedoCommand();

        case SendCommand.COMMAND_WORD:
            return new SendCommandParser().parse(arguments);

        case ChangePwdCommand.COMMAND_WORD:
        case ChangePwdCommand.COMMAND_ALIAS:
            return new ChangePwdCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Searches for all commands that contains the input text
     * @param text
     * @return ArrayList of command words
     */

    public ArrayList<String> filterCommand(String text) {
        ArrayList<String> result = new ArrayList<String>();
        if (AddCommand.COMMAND_WORD.contains(text)) {
            result.add(AddCommand.COMMAND_WORD);
        }
        if (ChangePwdCommand.COMMAND_WORD.contains(text)) {
            result.add(ChangePwdCommand.COMMAND_WORD);
        }
        if (EditCommand.COMMAND_WORD.contains(text)) {
            result.add(EditCommand.COMMAND_WORD);
        }
        if (DeleteCommand.COMMAND_WORD.contains(text)) {
            result.add(DeleteCommand.COMMAND_WORD);
        }
        if (ClearCommand.COMMAND_WORD.contains(text)) {
            result.add(ClearCommand.COMMAND_WORD);
        }
        if (FindCommand.COMMAND_WORD.contains(text)) {
            result.add(FindCommand.COMMAND_WORD);
        }
        if (ListCommand.COMMAND_WORD.contains(text)) {
            result.add(ListCommand.COMMAND_WORD);
        }
        if (HistoryCommand.COMMAND_WORD.contains(text)) {
            result.add(HistoryCommand.COMMAND_WORD);
        }
        if (HelpCommand.COMMAND_WORD.contains(text)) {
            result.add(HelpCommand.COMMAND_WORD);
        }
        if (UndoCommand.COMMAND_WORD.contains(text)) {
            result.add(UndoCommand.COMMAND_WORD);
        }
        if (ExitCommand.COMMAND_WORD.contains(text)) {
            result.add(ExitCommand.COMMAND_WORD);
        }
        if (SendCommand.COMMAND_WORD.contains(text)) {
            result.add(SendCommand.COMMAND_WORD);
        }
        if (RedoCommand.COMMAND_WORD.contains(text)) {
            result.add(RedoCommand.COMMAND_WORD);
        }
        return result;
    }

}
