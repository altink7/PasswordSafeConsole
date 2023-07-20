package at.altin.passwordsafe.passwordDecorator;

/***
 *class for password contains special character
 * implements IPasswordPolicy
 * R2
 */

public class PasswordContainsSpecialChar extends PasswordStrengthDecorator {

    public PasswordContainsSpecialChar(IPasswordPolicy passwordPolicy) {
        super(passwordPolicy);
    }

    @Override
    protected int getSpecificPolicy(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*") ? 1 : 0;
    }
}

