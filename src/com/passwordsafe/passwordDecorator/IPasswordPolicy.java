package com.passwordsafe.passwordDecorator;

/***
 * interface for password policy
 * can be used to add new password policy
 * contains method to get strength of password
 */

public interface IPasswordPolicy {
    int getStrength(String password);
}

