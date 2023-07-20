package at.altin.passwordsafe.datasource;
import at.altin.passwordsafe.PasswordSafeEngine;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Class for multiple files data source
 * implements IDataSourceLayer
 * contains methods for CRUD operations from different files
 */

public record MultipleFilesDataLayer(String path) implements IDataSourceLayer {

    @Override
    public void deletePassword(String passwordName, PasswordSafeEngine passwordSafeEngine) throws Exception {
        File storage = this.GetFileFromName(passwordName);
        if (!storage.delete()) {
            throw new Exception("Unable to delete password setting under " + storage.getName());
        }
    }

    @Override
    public char[] getPasswordCipher(String passwordName, PasswordSafeEngine passwordSafeEngine) throws IOException {
        File passwordFile = this.GetFileFromName(passwordName);
        return getCipherPassword(passwordFile);
    }

    public static char[] getCipherPassword( File passwordFile) throws IOException {
        char[] buffer = null;
        if (passwordFile.exists()) {
            FileReader reader = null;
            try {
                buffer = new char[(int) passwordFile.length()];
                reader = new FileReader(passwordFile);
                reader.read(buffer);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
        return buffer;
    }


    @Override
    public void storeNewPassword(String passwordName, String cypher) throws Exception {
        File directory = new File(this.path);
        if (!directory.isDirectory() && !directory.mkdir()) {
            throw new Exception("Unable to create directory");
        }
        File storage = (this.GetFileFromName(passwordName));
        if (storage.createNewFile()) {
            this.WriteToFile(storage.getPath(), cypher);
        } else {
            throw new Exception("Password with the same name already existing. Please choose another name of update the existing one.");
        }
    }

    @Override
    public String[] getAllNamesOfPasswords() throws Exception {
        File directory = new File(this.path);
        if (!directory.isDirectory() && !directory.mkdir()) {
            throw new Exception("Unable to create directory");
        }
        List<File> files = Arrays.asList(directory.listFiles());
        return files.stream()
                .filter(s -> s.getName().endsWith(".pw"))
                .map(f -> f.getName().split("\\.")[0])
                .collect(Collectors.toList()).toArray(new String[0]);
    }

    private void WriteToFile(String filename, String crypted) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(crypted);
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException ignore) {
            }
        }
    }

    private File GetFileFromName(String name) {
        return new File(this.path + "/" + name + ".pw");
    }
}