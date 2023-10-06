package at.altin.passwordsafe.passwordcommand.command;

import at.altin.passwordsafe.passwordcommand.receiver.PasswordFileOperationService;

import static at.altin.passwordsafe.Main.logger;

/**
 * command for deleting password file
 * @author altin
 * @version 1.0
 */
public class DeletePasswordFileOperation implements PasswordFileOperation{

    private final PasswordFileOperationService receiver;

    public DeletePasswordFileOperation(PasswordFileOperationService receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws Exception {
        logger.infoMessage("deleting password file");
        receiver.deletePassword();
    }
}
