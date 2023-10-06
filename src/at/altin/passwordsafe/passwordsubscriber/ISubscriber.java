package at.altin.passwordsafe.passwordsubscriber;

/***
 * interface for subscriber <br>
 * can be used to add new subscriber <br>
 * contains method to get selection <br>
 * main function is to check password for fraud <br>
 * + Observer Pattern is used here to add new subscriber
 */
public interface ISubscriber {

    /**
     * get the selection of subscriber
     * @param menu givenMenu
     * @param passwordCheck givenPasswordCheck
     */
    void selection(int menu, boolean passwordCheck);
}
