package at.altin.passwordsafe.passwordcommand.command;

import at.altin.passwordsafe.passwordcommand.receiver.PasswordFileOperationService;

/**
 * command for saving password file
 * @author altin
 * @version 1.0
 */
public class StoreNewPasswordFileOperation implements PasswordFileOperation {
    private final PasswordFileOperationService receiver;

    public StoreNewPasswordFileOperation(PasswordFileOperationService receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws Exception {
        receiver.storeNewPassword();
    }
}
