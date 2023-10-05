package at.altin.passwordsafe.passwordcommand.command;

import at.altin.passwordsafe.passwordcommand.receiver.PasswordFileOperationService;

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
        receiver.deletePassword();
    }
}
