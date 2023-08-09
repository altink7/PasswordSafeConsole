package at.altin.passwordsafe.passwordDecorator;

/***
 * interface for password policy
 * can be used to add new password policy
 * contains method to get strength of password
 */

public interface IPasswordPolicy {
    /**
     * get the Strength of Password Policy
     * @param password givenPassword
     * @return Strength
     */
    int getStrength(String password);
}

