package at.altin.passwordsafe;;
import at.altin.passwordsafe.datasource.IDataSourceLayer;
import at.altin.passwordsafe.passwordcommand.command.DeletePasswordFileOperation;
import at.altin.passwordsafe.passwordcommand.command.StoreNewPasswordFileOperation;
import at.altin.passwordsafe.passwordcommand.invoker.PasswordFileOperationExecutor;
import at.altin.passwordsafe.passwordcommand.receiver.PasswordFileOperationServiceBuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/***
 * Class for encryption and decryption
 * contains methods to delete, read and write passwords
 */
public class PasswordSafeEngine {
    private IDataSourceLayer dataLayer;

    private final CipherFacility cipherFacility;

    private final PasswordFileOperationExecutor executor = new PasswordFileOperationExecutor();

    public PasswordSafeEngine(CipherFacility cipherFacility,IDataSourceLayer dataLayer) {
        this.cipherFacility = cipherFacility;
        this.dataLayer = dataLayer;
    }

    public String[] getStoredPasswords() throws Exception {
        return dataLayer.getAllNamesOfPasswords();
    }

    public void addNewPassword(PasswordInfo info) throws Exception {
        this.executor.addOperation(new StoreNewPasswordFileOperation(
                new PasswordFileOperationServiceBuilder()
                        .setPassword(info.getName())
                        .setCypher(this.cipherFacility.Encrypt(info.getPlain()))
                        .create()));
    }
    public void deletePassword(String passwordName) throws Exception {
      this.executor.addOperation(new DeletePasswordFileOperation(new PasswordFileOperationServiceBuilder().setPassword(passwordName).create()));
    }

    public String getPassword(String passwordName) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        char[] buffer = this.dataLayer.getPasswordCipher(passwordName, this);
        return this.cipherFacility.Decrypt(new String(buffer));
    }
}
