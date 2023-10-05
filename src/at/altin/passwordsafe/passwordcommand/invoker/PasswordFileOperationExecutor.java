package at.altin.passwordsafe.passwordcommand.invoker;

import at.altin.passwordsafe.passwordcommand.command.PasswordFileOperation;

import static at.altin.passwordsafe.Main.logger;

/**
 * Invoker for PasswordFileOperation
 * @author altin
 * @version 1.0
 */
public class PasswordFileOperationExecutor extends AbstractInvoker<PasswordFileOperation> {

    @Override
    protected void executeConsumer(PasswordFileOperation operation) throws Exception {
        logger.infoMessage("executing operation: " + operation.getClass().getSimpleName() + "...");
        operation.execute();
    }
}
