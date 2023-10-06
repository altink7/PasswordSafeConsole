package at.altin.passwordsafe.passwordDecorator;

/***
 * interface for password policy <br>
 * can be used to add new password policy <br>
 * contains method to get strength of password <br>
 * + Decorator Pattern is used here to add new password policy
 */
public interface IPasswordPolicy {

    /**
     * get the Strength of Password Policy
     * @param password givenPassword
     * @return Strength
     */
    int getStrength(String password);
}

