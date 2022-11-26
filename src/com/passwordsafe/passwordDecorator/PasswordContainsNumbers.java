package com.passwordsafe.passwordDecorator;

/***
 * class for password contains numbers
 * implements IPasswordPolicy
 * R4
 */


public class PasswordContainsNumbers extends PasswordStrengthDecorator  {

    public PasswordContainsNumbers(IPasswordPolicy passwordPolicy) {
        super(passwordPolicy);
    }

    @Override
    protected int getSpecificPolicy(String password) {
        return password.matches(".*\\d.*") ? 1 : 0;
    }
}
