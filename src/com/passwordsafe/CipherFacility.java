package com.passwordsafe;

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
import java.util.List;
import java.util.stream.Collectors;

public class CipherFacility {
    private final String key;
    CipherFacility(String key) {
        this.key = key;
    }
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
    /*
    public byte[] EncryptToBytes(String plain) throws UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(
                Cipher.ENCRYPT_MODE,
                this.GetAESFormattedKeyFrom(this.key));
       return cipher.doFinal(plain.getBytes());
    }
    */
    /*
    public String DecryptBytes(byte[] crypted) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(
                Cipher.DECRYPT_MODE,
                this.GetAESFormattedKeyFrom(this.key));
        byte[] tmp = crypted;
        byte[] plain = cipher.doFinal(crypted);
        return new String(plain);
    }*/
    private SecretKeySpec GetAESFormattedKeyFrom(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] keyBytes = (key).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);
        return new SecretKeySpec(keyBytes, "AES");
    }

    private String GetStringFromByteArray(byte[] source) {
        String tmp = "";
        for (int i = 0; i < source.length; i++) {
            tmp += source[i] + ",";
        }
        return tmp;
    }
    private byte[] GetByteArrayFromString(String source) {
        String[] bytesAsString = source.split(",");
        byte[] bytes = new byte[bytesAsString.length];
        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.parseByte(bytesAsString[i]);
        }
        return bytes;
    }
}
