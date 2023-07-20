package at.altin.passwordsafe.logger;

/***
 * hier werden die Methoden von LoggerRepoService implementiert (Service Layer)
 * + hier wird Singleton Pattern angewendet
 *
 */

public class LoggerFactoryService implements LoggerRepoFactory{
    private static final Object object = new Object();
    private static volatile LoggerFactoryService instance;

    private final LoggerRepo logger;

    private LoggerFactoryService(){
        this.logger = new LoggerService();
    }

    public static LoggerFactoryService getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (object) {
            if (instance == null) {
                instance = new LoggerFactoryService();
            }
            return instance;
        }
    }

    @Override
    public LoggerRepo loggerRepoInstance() {
        return logger;
    }
}
