package at.altin.passwordsafe.passwordDecorator;

/***
 * password policy factory method
 * returns password policy object
 * creates different password policy objects, depending on the input
 */

public class PasswordPolicyFactory{


    public static IPasswordPolicy createPasswordPolicy(boolean strongPolicyMode ) {
        IPasswordPolicy passwordPolicy = new PasswordContainsUpperChar(); //R1
        passwordPolicy = new PasswordLength(passwordPolicy); //R3

        if(strongPolicyMode){
            passwordPolicy = new PasswordContainsSpecialChar(passwordPolicy); //R2
            passwordPolicy = new PasswordContainsNumbers(passwordPolicy); //R4
        }
        return passwordPolicy;
    }
}

