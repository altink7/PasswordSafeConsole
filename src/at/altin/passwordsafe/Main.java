package at.altin.passwordsafe;

import at.altin.passwordsafe.datasource.MultipleFilesDataLayer;
import at.altin.passwordsafe.logger.LoggerFactoryService;
import at.altin.passwordsafe.logger.LoggerRepo;
import at.altin.passwordsafe.passwordDecorator.PasswordPolicyFactory;
import at.altin.passwordsafe.passwordsubscriber.ISubscriber;
import at.altin.passwordsafe.passwordsubscriber.PasswordResetSubscriber;
import at.altin.passwordsafe.passwordsubscriber.WrongPasswordSubscriber;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

/***
 * Main Class
 * contains main method
 * contains methods for user interaction
 */

public class Main {

    private static final MasterPasswordRepository masterRepository = new MasterPasswordRepository("./master.pw");
    private static PasswordSafeEngine passwordSafeEngine = null;
    private static LoggerRepo logger;
    static boolean passwordCheck =false;


    private static final LinkedList<ISubscriber> menuSubscriber = new LinkedList<ISubscriber>(){
        {
            add(new PasswordResetSubscriber());
            add(new WrongPasswordSubscriber());
        }
    };

    public static void main(String[] args) throws Exception {
            System.out.println("Welcome to Passwordsafe");

            logger = LoggerFactoryService.getInstance().loggerRepoInstance();

            boolean abort = false;
            boolean locked = true;
            Scanner read = new Scanner(System.in);
            while (!abort) {
                System.out.println("Enter master (1), show all (2), show single (3), add (4), delete(5), set new master (6), Abort (0)");
                int input = read.nextInt();
                selection(input,passwordCheck);

                switch (input) {
                    case 0 -> {
                        abort = true;
                    }
                    case 1 -> {
                        passwordCheck = false;
                        locked = isLocked(read);
                    }
                    case 2 -> {
                        showAll(locked);
                    }
                    case 3 -> {
                        showSingle(locked, read);
                    }
                    case 4 -> addNew(locked, read);
                    case 5 -> {
                        deletePassword(locked, read);
                    }
                    case 6 -> {
                        locked = true;
                        passwordCheck = false;
                        passwordSafeEngine = null;

                        setNewMasterPassword(read);
                    }
                    default -> System.out.println("Invalid input");
                }
            }

        System.out.println("Good bye!");
    }

   //US_3_S2
    private static void selection(int menu,boolean passwordCheck){
        for(ISubscriber i: menuSubscriber){
            i.selection(menu,passwordCheck);
        }
    }

    private static void setNewMasterPassword(Scanner read) throws Exception {
                String passwordMessage =("""
                        
                        (Password must contain at least one upper case letter,\s
                        eight Letters, one number and one special character)""".indent(1));

                System.out.println("Press 'n' to set weaker Password Policy otherwise press any key for stronger Policy ");
                boolean passwordStrengthMode = !read.next().equals("n");

                System.out.println("Enter new master password ! (Warning you will loose all already stored passwords)"+passwordMessage);
                String masterPw = read.next();
                int passwordStrength = PasswordPolicyFactory.createPasswordPolicy(passwordStrengthMode).getStrength(masterPw);

                while (passwordStrength < (passwordStrengthMode?4:2)) {
                    System.out.println("Password is not strong enough, please enter a new password"+passwordMessage);
                    System.out.println("Strength of Password: " + passwordStrength);
                    masterPw = read.next();
                    passwordStrength = PasswordPolicyFactory.createPasswordPolicy(passwordStrengthMode).getStrength(masterPw);

                }

        System.out.println("Enter your new password again");
        String masterPw2 = read.next();
        if (masterPw.equals(masterPw2)) {
            passwordCheck = true;
            masterRepository.setMasterPasswordPlain(masterPw);
            System.out.print("Password has been successfully changed \n \n");
            logger.infoMessage("Password is the same");

        } else {
            System.out.print("Password has been not changed \n \n");
            logger.errorMessage("Password is not the same");
            return;
        }

        File oldPasswords = new File("./passwords.pw");
        if (oldPasswords.isDirectory()) {
            String[] children = oldPasswords.list();
            IntStream.range(0, children.length).forEachOrdered(i -> (new File(oldPasswords, children[i])).delete());
        }

        oldPasswords.delete();
    }

    private static void deletePassword(boolean locked, Scanner read) throws Exception {
        if (locked) {
            System.out.println("Please unlock first by entering the master password.");
        } else {
            System.out.println("Enter password name");
            String passwordName = read.next();
            try {
                passwordSafeEngine.deletePassword(passwordName);
            }catch (Exception e){
                System.out.println("Deletion not possible, Password may not exist!");
            }
        }
    }

    private static void addNew(boolean locked, Scanner read) throws Exception {
        if (locked) {
            System.out.println("Please unlock first by entering the master password.");
        } else {
            System.out.println("Enter new name of password");
            String passwordName = read.next();
            System.out.println("Enter password");
            String password = read.next();
            passwordSafeEngine.addNewPassword(new PasswordInfo(password, passwordName));
        }
    }

    private static void showSingle(boolean locked, Scanner read) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (locked) {
            System.out.println("Please unlock first by entering the master password.");
        } else {
            System.out.println("Enter password name");
            String passwordName = read.next();
            System.out.println(passwordSafeEngine.getPassword(passwordName));
        }
        return;
    }

    private static void showAll(boolean locked) throws Exception {
        if (locked) {
            System.out.println("Please unlock first by entering the master password.");
        } else {
            Arrays.stream(passwordSafeEngine.getStoredPasswords()).forEach(pw -> System.out.println(pw));
        }
        return;
    }

    private static boolean isLocked(Scanner read) throws Exception {
        boolean locked;
        System.out.println("Enter master password");
        String masterPw = read.next();
        locked = !masterRepository.MasterPasswordIsEqualTo(masterPw);
        if (!locked) {
            passwordSafeEngine = new PasswordSafeEngine(new CipherFacility(masterPw), new MultipleFilesDataLayer("./passwords.pw"));
            System.out.println("unlocked");
        } else {
            passwordCheck =true;
            System.out.println("master password did not match ! Failed to unlock.");
        }
        return locked;
    }
}
