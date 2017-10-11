package seedu.address.logic.commands;

import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.SendMessageEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;



/**
 * Sends a message to the contact through email
 */

public class SendCommand extends Command {

    public static final String COMMAND_WORD = "send";
    //public static final String COMMAND_ALIAS = "snd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Opens up third-party communication application with"
                                                            + " the information of the person identified"
                                                            + " by the index number used in the last person listing. ";


    public static final String MESSAGE_OPEN_MAIL_SUCCESS = "Opened Mail App...";
    public static final String MESSAGE_NO_MAIL = "Contact does not have an email address.";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to send message to
     */
    public SendCommand(Index index) {
        this.index = index;
    }


    @Override
    public CommandResult execute() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToCommunicate = lastShownList.get(index.getZeroBased());
        String emailOfPerson = personToCommunicate.getEmail().toString();

        //Open Windows 10 Mail app
        try {
            Process p = Runtime.getRuntime().exec("cmd /c start mailto:" + emailOfPerson);
            p.waitFor();
            System.out.println("Mail launched!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventsCenter.getInstance().post(new SendMessageEvent());
        return new CommandResult(String.format(MESSAGE_OPEN_MAIL_SUCCESS));
    }


}
