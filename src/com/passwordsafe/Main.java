package com.passwordsafe;

import com.passwordsafe.logger.LoggerFactoryService;
import com.passwordsafe.logger.LoggerRepo;
import com.passwordsafe.logger.LoggerService;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final MasterPasswordRepository masterRepository = new MasterPasswordRepository("./master.pw");
    private static PasswordSafeEngine passwordSafeEngine = null;
    private static LoggerRepo logger;

    public static void main(String[] args) throws Exception {
            System.out.println("Welcome to Passwordsafe");
            logger = LoggerFactoryService.getInstance().loggerRepoInstance();
            boolean abort = false;
            boolean locked = true;
            Scanner read = new Scanner(System.in);
            while (!abort) {
                System.out.println("Enter master (1), show all (2), show single (3), add (4), delete(5), set new master (6), Abort (0)");
                int input = read.nextInt();
                switch (input) {
                    case 0: {
                        abort = true;
                        break;
                    }
                    case 1: {
                        System.out.println("Enter master password");
                        String masterPw = read.next();
                        locked = !masterRepository.MasterPasswordIsEqualTo(masterPw);
                        if (!locked) {
                            passwordSafeEngine = new PasswordSafeEngine("./passwords.pw", new CipherFacility(masterPw));
                            System.out.println("unlocked");
                        } else {
                            System.out.println("master password did not match ! Failed to unlock.");
                        }
                        break;
                    }
                    case 2: {
                        if (locked) {
                            System.out.println("Please unlock first by entering the master password.");
                        } else {
                            Arrays.stream(passwordSafeEngine.GetStoredPasswords()).forEach(pw -> System.out.println(pw));
                        }
                        break;
                    }
                    case 3: {
                        if (locked) {
                            System.out.println("Please unlock first by entering the master password.");
                        } else {
                            System.out.println("Enter password name");
                            String passwordName = read.next();
                            System.out.println(passwordSafeEngine.GetPassword(passwordName));
                        }
                        break;
                    }
                    case 4: {
                        if (locked) {
                            System.out.println("Please unlock first by entering the master password.");
                        } else {
                            System.out.println("Enter new name of password");
                            String passwordName = read.next();
                            System.out.println("Enter password");
                            String password = read.next();
                            passwordSafeEngine.AddNewPassword(new PasswordInfo(password, passwordName));
                        }
                        break;
                    }
                    case 5: {
                        if (locked) {
                            System.out.println("Please unlock first by entering the master password.");
                        } else {
                            System.out.println("Enter password name");
                            String passwordName = read.next();
                            passwordSafeEngine.DeletePassword(passwordName);
                        }
                        break;
                    }

                    case 6: {
                        locked = true;
                        passwordSafeEngine = null;


                        //Hier beginnt die impl. für US_1_S_1
                        System.out.println("Enter new master password ! (Warning you will loose all already stored passwords)");
                        String masterPw = read.next();
                        System.out.println("Enter your new password again");
                        String masterPw2 = read.next();
                        if (masterPw.equals(masterPw2)) {
                            masterRepository.setMasterPasswordPlain(masterPw);
                            System.out.print("Password has been successfully changed \n \n");
                            logger.infoMessage("Password is the same");

                        } else {
                            System.out.print("Password has been not changed \n \n");
                            logger.errorMessage("Password is not the same");
                            break;
                        }
                        //Hier endet die impl. für US_1_S_1


                        // urgent hotfix delete old passwords after changing the master
                        File oldPasswords = new File("./passwords.pw");
                        if (oldPasswords.isDirectory()) {
                            String[] children = oldPasswords.list();
                            for (int i = 0; i < children.length; i++) {
                                (new File(oldPasswords, children[i])).delete();
                            }
                        }
                        // The directory is now empty or this is a file so delete it
                        oldPasswords.delete();
                        break;
                    }
                    default:
                        System.out.println("Invalid input");
                }
            }

        System.out.println("Good by !");
    }
}
