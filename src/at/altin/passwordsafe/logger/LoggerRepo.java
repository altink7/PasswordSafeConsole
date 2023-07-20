package at.altin.passwordsafe.logger;

/***
 * Logger Interface
 */
public interface LoggerRepo {
    //US_2_S_2
    void infoMessage(String message);
    void debugMessage(String message);
    void errorMessage(String message);
}
