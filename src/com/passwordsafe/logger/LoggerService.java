package com.passwordsafe.logger;

/***
 * Hier werden die Methoden von LoggerRepo implementiert (Service Layer)
 */

public class LoggerService implements LoggerRepo{
    @Override
    public void infoMessage(String message) {
        System.out.println("info:"+message);
    }

    @Override
    public void debugMessage(String message) {
        System.out.println("debug:"+message);
    }

    @Override
    public void errorMessage(String message) {
        System.out.println("error:"+message);
    }
}
