package at.altin.passwordsafe.passwordcommand.receiver;

import at.altin.passwordsafe.datasource.IDataSourceLayer;
import at.altin.passwordsafe.datasource.MultipleFilesDataLayer;

import static at.altin.passwordsafe.Main.logger;

/**
 * receiver for password file operation <br>
 * just a wrapper for data layer to be used by command pattern
 * @author altin
 * @version 1.0
 */
public class PasswordFileOperationService {
    private final IDataSourceLayer dataLayer = new MultipleFilesDataLayer("./passwords.pw");
    String password;
    String cypher;


    public PasswordFileOperationService(String password, String cypher) {
        this.password = password;
        this.cypher = cypher;
    }

    public void deletePassword() throws Exception {
      this.dataLayer.deletePassword(password);
    }

    public void storeNewPassword() throws Exception {
      this.dataLayer.storeNewPassword(password, cypher);
    }
}
