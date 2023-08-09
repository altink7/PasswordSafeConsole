package at.altin.passwordsafe;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/***
 * Class for encryption and decryption
 * contains methods for encryption and decryption
 */

public record CipherFacility(String key) {

    public String Decrypt(String crypted) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(
                Cipher.DECRYPT_MODE,
                this.GetAESFormattedKeyFrom(this.key));
        byte[] tmp = this.GetByteArrayFromString(crypted);
        byte[] plain = cipher.doFinal(this.GetByteArrayFromString(crypted));
        return new String(plain);
    }

    public String Encrypt(String plain) throws UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(
                Cipher.ENCRYPT_MODE,
                this.GetAESFormattedKeyFrom(this.key));
        byte[] encrypted = cipher.doFinal(plain.getBytes());
        return this.GetStringFromByteArray(encrypted);
    }

    private SecretKeySpec GetAESFormattedKeyFrom(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] keyBytes = (key).getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);
        return new SecretKeySpec(keyBytes, "AES");
    }

    private String GetStringFromByteArray(byte[] source) {
        StringBuilder tmp = new StringBuilder();
        for (byte b : source) {
            tmp.append(b).append(",");
        }
        return tmp.toString();
    }

    private byte[] GetByteArrayFromString(String source) {
        String[] bytesAsString = source.split(",");
        byte[] bytes = new byte[bytesAsString.length];
        int i = 0;
        while (i < bytes.length) {
            bytes[i] = Byte.parseByte(bytesAsString[i]);
            i++;
        }
        return bytes;
    }
}
