package at.altin.passwordsafe.passwordDecorator;

/***
 * password policy factory method
 * returns password policy object
 * creates different password policy objects, depending on the input
 */

public class PasswordPolicyFactory{


    /**
     * hier wird das PasswordPolicy Objekt erstellt
     * Die PasswordPolicy werden erstellt und sich selbst als Parameter übergeben <br>
     * damit wird das Decorator Pattern verwendet
     * @param strongPolicyMode soll true sein, wenn das Passwort eine starke Policy haben soll (von 4 Regeln),
     *                        ansonsten false (von 2 Regeln)
     * @return PasswordPolicy Objekt bestehend aus 2 oder 4 Regeln
     */
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

