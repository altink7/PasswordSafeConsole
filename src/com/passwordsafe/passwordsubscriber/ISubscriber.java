package com.passwordsafe.passwordsubscriber;

/***
 * interface for subscriber
 * can be used to add new subscriber
 * contains method to get selection
 * main function is to check password for fraud
 */
public interface ISubscriber {
    void selection(int menu, boolean passwordCheck);
}
