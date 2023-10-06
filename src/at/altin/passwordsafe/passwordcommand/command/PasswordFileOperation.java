package at.altin.passwordsafe.passwordcommand.command;

/**
 * interface for password file operation
 * + Command Pattern is used
 * @author altin
 * @version 1.0
 */
@FunctionalInterface
public interface PasswordFileOperation {

    /**
     * Die Operation (Command) soll selber keine Logik enthalten, sondern nur die receiver Methode aufrufen
     * in Command Pattern, each command should have a method to take an action
     * @throws Exception normally we define specific exceptions, in this case there are a lot of things that can go wrong
     * since we are dealing the cipher text, files and so on, to keep it simple, we just throw Exception
     */
    void execute() throws Exception;
}
