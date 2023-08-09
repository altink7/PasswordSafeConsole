package at.altin.passwordsafe;

import at.altin.passwordsafe.datasource.MultipleFilesDataLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/***
 * MasterPasswordRepository
 * contains methods to read and write master password
 */

public class MasterPasswordRepository {
    private String masterPasswordPath;

    public MasterPasswordRepository(String masterPasswordPath) {
        this.masterPasswordPath = masterPasswordPath;
    }
    public void setMasterPasswordPlain(String masterPassword) throws Exception {
        this.storeMasterPasswordToFile(masterPassword);
    }
    public String getMasterPasswordPlain() throws Exception {
        return this.getMasterPasswordFromFile();
    }
    public boolean MasterPasswordIsEqualTo(String masterPassword) throws Exception {
        return masterPassword.equals(this.getMasterPasswordFromFile());
    }
    private String getMasterPasswordFromFile() throws Exception {
        File passwordFile = new File(this.masterPasswordPath);
        char[] buffer = MultipleFilesDataLayer.getCipherPassword(passwordFile);
        return buffer == null ? null : new String(buffer);
    }
    private void storeMasterPasswordToFile(String masterPassword) throws Exception {
        FileWriter writer = null;
        try {
            writer = new FileWriter(this.masterPasswordPath);
            writer.write(masterPassword);
        } finally {
            if (writer != null) try { writer.close(); } catch (IOException ignore) {}
        }
    }
}
