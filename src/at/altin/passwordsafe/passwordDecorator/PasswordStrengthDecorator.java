package at.altin.passwordsafe.passwordDecorator;

/***
 * decorator for password strength
 * implements IPasswordPolicy
 */

public abstract class PasswordStrengthDecorator implements IPasswordPolicy {
    private IPasswordPolicy passwordPolicy;

    public PasswordStrengthDecorator(IPasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    @Override
    public int getStrength(String password) {
        return this.getSpecificPolicy(password) + this.passwordPolicy.getStrength(password);
    }

    protected abstract int getSpecificPolicy(String password);
}

