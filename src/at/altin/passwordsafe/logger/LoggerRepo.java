package at.altin.passwordsafe.logger;

/***
 * Logger Interface
 */
public interface LoggerRepo {

    void infoMessage(String message);

    void debugMessage(String message);

    void errorMessage(String message);
}
