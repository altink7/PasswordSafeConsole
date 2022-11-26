package com.passwordsafe.passwordDecorator;

/***
 * password contains upper char
 * implements IPasswordPolicy
 * R1
 */

public class PasswordContainsUpperChar implements IPasswordPolicy {

    @Override
    public int getStrength(String password) {
        return password.matches(".*[A-Z].*") ? 1 : 0;
    }
}

