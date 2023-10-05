package at.altin.passwordsafe.passwordcommand.command;

/**
 * interface for password file operation
 * + Command Pattern is used
 * @author altin
 * @version 1.0
 */
@FunctionalInterface
public interface PasswordFileOperation {

    void execute() throws Exception;
}
