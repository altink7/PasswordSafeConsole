package com.passwordsafe.datasource;
import com.passwordsafe.PasswordSafeEngine;
import java.io.IOException;

/***
 * Interface for Data Source Layer
 * can be used to add new data source
 * contains method to save and load data
 */

public interface IDataSourceLayer {

    void deletePassword(String passwordName, PasswordSafeEngine passwordSafeEngine) throws Exception;
    char[] getPasswordCipher(String passwordName, PasswordSafeEngine passwordSafeEngine) throws IOException;
    void storeNewPassword(String passwordName, String cypher) throws Exception;
    String[] getAllNamesOfPasswords() throws Exception;

}