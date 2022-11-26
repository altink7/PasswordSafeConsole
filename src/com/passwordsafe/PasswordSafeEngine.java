package com.passwordsafe;;
import com.passwordsafe.datasource.IDataSourceLayer;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Class for encryption and decryption
 * contains methods to delete, read and write passwords
 */

public class PasswordSafeEngine {
    private IDataSourceLayer dataLayer;

    private final CipherFacility cipherFaciility;

    public PasswordSafeEngine(CipherFacility cipherFacility,IDataSourceLayer dataLayer) {
        this.cipherFaciility = cipherFacility;
        this.dataLayer = dataLayer;
    }

    public String[] GetStoredPasswords() throws Exception {
        return dataLayer.getAllNamesOfPasswords();
    }

    public void AddNewPassword(PasswordInfo info) throws IOException, Exception {
        this.dataLayer.storeNewPassword(
                info.getName(),
                this.cipherFaciility.Encrypt(info.getPlain()));
    }
    public void DeletePassword(String passwordName) throws Exception, IOException {
      this.dataLayer.deletePassword(passwordName,this);
    }
    public String GetPassword(String passwordName) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        char[] buffer = this.dataLayer.getPasswordCipher(passwordName, this);
        return this.cipherFaciility.Decrypt(new String(buffer));
    }
}
