package seedu.duke.email.command;

import seedu.duke.Duke;
import seedu.duke.email.entity.EmailList;
import seedu.duke.CommandParser;
import seedu.duke.common.command.Command;

import java.io.IOException;

public class EmailShowCommand extends Command {
    private EmailList emailList;
    private int index;

    public EmailShowCommand(EmailList emailList, int index) {
        this.emailList = emailList;
        this.index = index;
    }

    @Override
    public boolean execute() {
        try {
            responseMsg = emailList.show(index);
            String parsedMsg[] = responseMsg.split(" path: ", 2);
            Duke.getUI().showResponse(parsedMsg[0]);
            Duke.getUI().setEmailPath(parsedMsg[1]);
            return true;
        } catch (CommandParser.UserInputException | IOException e) {
            if (!silent) {
                Duke.getUI().showError(e.toString());
            }
            return false;
        }
    }
}