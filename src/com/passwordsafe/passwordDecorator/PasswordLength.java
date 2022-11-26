package com.passwordsafe.passwordDecorator;

/***
 * class for password length policy
 * implements IPasswordPolicy
 * R3
 */

public class PasswordLength extends PasswordStrengthDecorator {

    public PasswordLength(IPasswordPolicy passwordPolicy) {
        super(passwordPolicy);
    }

    @Override
    protected int getSpecificPolicy(String password) {
        return password.length() > 7 ? 1 : 0;
    }
}
