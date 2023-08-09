package at.altin.passwordsafe.passwordsubscriber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static java.lang.System.out;

/***
 * WrongPasswordSubscriber
 * implements ISubscriber
 * it is a subscriber for wrong password
 */
public class WrongPasswordSubscriber implements ISubscriber{
    record SelectionState(Integer menu, Date timeStamp) {}

    private final LinkedList<SelectionState> selectionState = new LinkedList<>();

    @Override
    public void selection(int menu, boolean passwordCheck) {
        if(menu == 1 && passwordCheck) {
            this.selectionState.add(new SelectionState(menu, new Date()));
        }

        if(menu == 1 && selectionState.size()>0) {
            out.print("\nWrong Password States[Time]:");
            out.print("\n--------");
            selectionState.forEach(i -> out.printf("\nTime: %s\n--------\n", new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(i.timeStamp)));
        }

    }
}
