package at.altin.passwordsafe.passwordcommand.invoker;

import at.altin.passwordsafe.passwordcommand.command.PasswordFileOperation;

import java.util.ArrayList;
import java.util.List;


/**
 * AbstractInvoker, which can be used for all invokers
 * @param <T> type of operation (Function Interface)
 * @author altin
 * @version 1.0
 */
public abstract class AbstractInvoker<T> {

    /**
     * Operations (Commands) are stored in a list, so that we can manipulate them
     * and also use for undo/redo, think like str+Z button in text editor, which undoes the last operation
     * there is no limit for the number of operations, but it is limited by the memory
     * @see PasswordFileOperation
     */
    private final List<T> operations = new ArrayList<>();

    /**
     * child Class musst provide the logic for executing the operation, that can differ from invoker to invoker
     * @param operation operation (Command) to be executed
     * @throws Exception normally we define specific exceptions, in this case there are a lot of things that can go wrong
     */
    protected abstract void executeConsumer(T operation) throws Exception;

    /**
     * add operation to the list and execute it, most of the time, we will use this method
     * @param operation operation (Command) to be executed
     */
    public void addOperation(T operation) throws Exception {
        this.operations.add(operation);
        executeConsumer(operation);
    }

    public void executeAll() throws Exception {
        for (T operation : operations) {
            executeConsumer(operation);
        }
    }

    public List<T> getOperations() {
        return this.operations;
    }

    public void removeOperations(List<T> operations) {
        this.operations.removeAll(operations);
    }

    public void clear() {
        this.operations.clear();
    }

    public void removeOperation(PasswordFileOperation operation) {
        this.operations.remove(operation);
    }

    public void removeOperation(int index) {
        this.operations.remove(index);
    }
}
