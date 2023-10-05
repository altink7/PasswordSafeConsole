package at.altin.passwordsafe.passwordcommand.invoker;

import at.altin.passwordsafe.logger.LoggerFactoryService;
import at.altin.passwordsafe.logger.LoggerRepo;
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
    private final List<T> operations = new ArrayList<>();

    protected abstract void executeConsumer(T operation) throws Exception;

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
